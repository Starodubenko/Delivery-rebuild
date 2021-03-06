package com.epam.star.action.paycard;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

@MappedAction("POST/payment")
public class PaymentAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAction.class);

    ActionResult message = new ActionResult("message");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        daoManager.beginTransaction();
        try {
            PayCardDao payCardDao = daoManager.getPayCardDao();
            PayCardStatusDao statusPayCardDao = daoManager.getPayCardStatusDao();
            ClientDao clientDao = daoManager.getClientDao();
            EmployeeDao employeeDao = daoManager.getEmployeeDao();
            PositionDao userPositionDao = daoManager.getPositionDao();

            String code = request.getParameter("SecretCode");
            PayCard payCard = payCardDao.findBySecretNumber(code);

            StatusPayCard notActivatedStatus = statusPayCardDao.findByStatusName("not activated");
            StatusPayCard currentStatus = payCard.getStatusPayCard();

            if (currentStatus.equals(notActivatedStatus)) {
                Client user = (Client) request.getSession().getAttribute("user");

                BigDecimal userBal = user.getVirtualBalance();
                BigDecimal payBal = payCard.getBalance();
                BigDecimal newBal = userBal.add(payBal);

                payCard.setStatusPayCard(statusPayCardDao.findByStatusName("activated"));
                payCardDao.updateEntity(payCard);

                user.setVirtualBalance(newBal);

                Position userRole = user.getRole();
                Position clientRole = userPositionDao.findByPositionName("Client");

                if (userRole.equals(clientRole)) {
                    clientDao.updateEntity(user);
                }
                if (!userRole.equals(clientRole))
                    employeeDao.updateEntity((Employee) user);
                request.setAttribute("message", "card.activation.successful");
            } else {
                LOGGER.error("The payment card have status: {}", payCard.getStatusPayCard().getStatusName());
                request.setAttribute("message", "card.already.activated");
            }
            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
            request.setAttribute("message", "payment.error");
        } finally {
            daoManager.closeConnection();
        }
        return message;
    }
}

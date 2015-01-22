package com.epam.star.action.cart;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2GoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Order2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("GET/delete-goods")
public class DeleteGoodsFromCart implements Action{
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteGoodsFromCart.class);
    ActionResult shoppingcart = new ActionResult("shoppingCart",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        HttpSession session = request.getSession();

        Order2 shoppingCart = (Order2) session.getAttribute("shoppingCart");
        int goodsId = Integer.valueOf(request.getParameter("id"));

        Goods goods = goodsDao.findById(goodsId);

        shoppingCart.removeGoods(goods);
        session.setAttribute("shoppingCart", shoppingCart);

        daoManager.closeConnection();

        return shoppingcart;
    }
}

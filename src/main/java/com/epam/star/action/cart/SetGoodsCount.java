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
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@MappedAction("GET/set-goods-count")
public class SetGoodsCount implements Action{
    private static final Logger LOGGER = LoggerFactory.getLogger(SetGoodsCount.class);
    ActionResult json = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        HttpSession session = request.getSession();

        Order2 shoppingCart = (Order2) session.getAttribute("shoppingCart");
        int goodsId = Integer.valueOf(request.getParameter("id"));
        int goodsCount = Integer.valueOf(request.getParameter("goods-count"));

        Goods goods = goodsDao.findById(goodsId);

        shoppingCart.setGoodsCount(goods,goodsCount);
        session.setAttribute("shoppingCart", shoppingCart);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cost", shoppingCart.getCostByGoodsId(goodsId));
        jsonObject.put("total", shoppingCart.getTotalSum());
        jsonObject.put("id", goodsId);

        request.setAttribute("json",jsonObject);

        daoManager.closeConnection();
        return json;
    }
}

package com.epam.star.dao.H2dao;

import com.epam.star.dao.GoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2GoodsDao extends AbstractH2Dao implements GoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_GOODS = "INSERT INTO GOODS VALUES (?, ?, ?, ?)";
    private static final String DELETE_GOODS = "DELETE FROM GOODS WHERE ID = ?";
    private static final String UPDATE_GOODS = "UPDATE GOODS SET ID = ?, GOODS_NAME = ?, PRICE = ?, IMAGE = ? WHERE ID = ?";

    private static final String NECESSARY_COLUMNS =
            " GOODS.ID, GOODS.GOODS_NAME, GOODS.PRICE";

    private static final String ADDITIONAL_COLUMNS =
            " GOODS.IMAGE";

    private static final String FIND_BY_PARAMETERS_WITHOUT_COLUMNS =
            " SELECT %s FROM GOODS";

    private static final String ID_FIELD = " GOODS.ID, ";


    protected H2GoodsDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    public List<Goods> getAllGoods() {
        List<Goods> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT * FROM goods");
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Goods findByGoodsName(String name) throws DaoException {
        String sql = "SELECT * FROM goods WHERE goods_name = " + "'" + name + "'";
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                goods = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return goods;
    }

    @Override
    public Goods findById(int ID) throws DaoException {
        String sql = "SELECT * FROM goods WHERE id = " + ID;
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                goods = getEntityFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return goods;
    }

    @Override
    public String insert(Goods goods) throws DaoException {
        String status = "Goods do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_GOODS);
            prstm.setString(1, null);
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.setInt(4, goods.getImage().getId());
            prstm.execute();
            status = "Goods added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteEntity(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateEntity(Goods goods) throws DaoException {
        String status = "Goods do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_GOODS);
            prstm.setInt(1, goods.getId());
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.setInt(4, goods.getId());
            prstm.executeUpdate();
            status = "Goods updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public Goods getEntityFromResultSet(ResultSet resultSet) throws DaoException {

        H2ImageDao imageDao = daoManager.getImageDao();

        Goods goods = new Goods();
        try {
            goods.setId(resultSet.getInt("id"));
            goods.setGoodsName(UTIL_DAO.getString(resultSet.getString("goods_name")));
            goods.setPrice(resultSet.getBigDecimal("price"));

            Image image = imageDao.findById(resultSet.getInt("image"));
            goods.setImage(image);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public String getFindByParameters(Boolean needAditionalColumns) {

        String columns = NECESSARY_COLUMNS;

        if (needAditionalColumns == true){
            columns = columns + ADDITIONAL_COLUMNS;
        }

        String result = String.format(FIND_BY_PARAMETERS_WITHOUT_COLUMNS,columns);

        result = String.format(result+"%s", LIMIT_OFFSET);

        return result;
    }

    @Override
    public String getFindByParametersWithoutColumns() {
        return FIND_BY_PARAMETERS_WITHOUT_COLUMNS;
    }

    @Override
    public String getNecessaryColumns() {
        return NECESSARY_COLUMNS;
    }

    @Override
    public String getAdditionalColumns() {
        return ADDITIONAL_COLUMNS;
    }

    @Override
    public String getIdField() {
        return ID_FIELD;
    }
}

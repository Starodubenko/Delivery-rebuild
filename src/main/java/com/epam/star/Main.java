package com.epam.star;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Random;

public class Main {

    private static Random rnd = new Random();


    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
//        DaoManager daoManager = new DaoManager();
//---------Client-and-Employee-----------------------------------------------------------

//        ClientDao clientDao = new H2ClientDao(entityManager, Client.class);
////        CartDao cartDao = new H2CartDao(entityManager, Cart.class);
//        OrderDao orderDao = new H2OrderDao(entityManager, Order2.class);
//        GoodsDao goodsDao = new H2GoodsDao(entityManager, Goods.class);
//        OrderedGoodsDao orderedGoodsDao = new H2OrderedGoodsDao(entityManager, OrderedGoods.class);
//        PayCardDao payCardDao = new H2PayCardDao(entityManager, PayCard.class);
//        PayCartStatusDao payCardStatusDao = new H2PayCardStatusDao(entityManager, StatusPayCard.class);
//
//        PeriodDao periodDao = new H2PeriodDao(entityManager, Period.class);
//        PositionDao positionDao = new H2PositionDao(entityManager, Position.class);
//        StatusDao statusDao = new H2StatusDao(entityManager, Status.class);
//
//        Client client = clientDao.findById(8);
//
//        List<Order2> orders1 = orderDao.findByClient(client, true);
//        List<Order2> orders2 = orderDao.findByClient(client, false);
//
//        Order2 order = new Order2();
//
//        Position position = new Position();
//        position.setPositionName("qwerty");
//
//        List<Position> all = positionDao.findAll();
//
//        position = positionDao.findByPositionName("qwerty");
//        positionDao.delete(position);
//
//        all = positionDao.findAll();


        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}

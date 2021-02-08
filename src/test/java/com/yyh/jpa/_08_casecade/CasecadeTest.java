package com.yyh.jpa._08_casecade;

import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CasecadeTest {

    @Before
    public void testSave(){
        OrderBill orderBill = new OrderBill();
        orderBill.setSn("SN");
        OrderItem item = new OrderItem();
        item.setProductName("商品名称1");
        OrderItem item2 = new OrderItem();
        item2.setProductName("商品名称2");
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(item);
        orderItemList.add(item2);
        orderBill.setOrderItemList(orderItemList);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //只保存主对象，从对象(关联对象)自动保存
        em.persist(orderBill);
//        em.persist(item);
//        em.persist(item2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testDelete(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //删除1号订单时，自动删除订单明细的集合(orderItemList)
        OrderBill orderBill = em.find(OrderBill.class, 1L);
        em.remove(orderBill);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testUpdate(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        OrderBill orderBill = em.find(OrderBill.class,1L);
        List<OrderItem> orderItemList = orderBill.getOrderItemList();
        //以下三种情况都是更新订单明细  通通使用em.merge(orderBill);
        //添加一个订单明细
        OrderItem newItem = new OrderItem();
        newItem.setProductName("新的商品名称");
        orderItemList.add(newItem);
        //修改一个订单明细
        OrderItem item = orderItemList.get(0);
        item.setProductName("修改商品名称");
        //删除一个订单明细
        orderItemList.remove(1);//默认只会删除中间表里面的关系，不会删除订单明细里面的该记录  要删除的话，需要在@OneToMany加orphanRemoval = true，即删除孤儿
        em.merge(orderBill);
        em.getTransaction().commit();
        em.close();
    }
}

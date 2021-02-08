package com.yyh.jpa._01_crudtest;

import com.yyh.jpa._01_crud.User;
import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CRUDTest {

    //保存20条数据  事实上有下面那个@Before方法，我存了21条数据，哈哈
    @Test
    public void testSave20User(){
        List<User> userList = new ArrayList<>();
        for (int i = 0 ;i < 20 ;i++){
            User user = new User();
            user.setName("sansan"+i);
            user.setAge(i);
            user.setHireDate(new Date());
            userList.add(user);
        }


        //调用EntityManager完成保存
        EntityManager em = JPAUtil.getEntityManager();
        //开启事务
        em.getTransaction().begin();
        //执行保存
        for (int i = 0 ;i < 20 ;i++){
            User user = userList.get(i);
            em.persist(user);
        }
        //提交事务
        em.getTransaction().commit();
        //释放资源
        em.close();
    }

    //保存
    @Before
    public void testSave(){
        User user = new User();
        user.setName("sansan");
        user.setAge(10);
        user.setHireDate(new Date());

        //调用EntityManager完成保存
        EntityManager em = JPAUtil.getEntityManager();
        //开启事务
        em.getTransaction().begin();
        //执行保存
        em.persist(user);
        //提交事务
        em.getTransaction().commit();
        //释放资源
        em.close();
    }

    //删除
    @Test
    public void testDelete(){
        //调用EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        //开启事务
        em.getTransaction().begin();
        //执行删除
        //先查询到要删除的数据
        User user = em.find(User.class, 1L);
        em.remove(user);
        //提交事务
        em.getTransaction().commit();
        //释放资源
        em.close();
    }

    //更新
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(2L);
        user.setName("pangpang");
        user.setAge(12);
        user.setHireDate(new Date());

        //调用EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        //开启事务
        em.getTransaction().begin();
        //执行更新
        em.merge(user);
        //提交事务
        em.getTransaction().commit();
        //释放资源
        em.close();
    }

    //查询单条数据
    @Test
    public void testGet(){
        //调用EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        User user = em.find(User.class, 2L);//参数1：结果封装成什么类型的对象；参数2：指定查询的主键
        System.out.println(user);
        em.close();
    }

    //查询多条数据
    @Test
    public void testList(){
        //调用EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        //参数1：指定执行的jpql；参数2：查询的结果封装类型
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        //结果放在list集合里面
        List<User> list = query.getResultList();
        System.out.println(list);
        em.close();
    }
    @Test
    public void testCache(){//两次查询只有一条sql
        EntityManager em = JPAUtil.getEntityManager();
        User user = em.find(User.class, 1L);
        System.out.println(user);
        User user2 = em.find(User.class, 1L);
        System.out.println(user2);
        em.close();
    }
    @Test
    public void testObjectState(){
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, 1L);
        user.setName("pangpang");
        em.getTransaction().commit();//此处会自动执行更新语句
        em.close();
    }
}

package com.yyh.jpa._10_lock_test;

import com.yyh.jpa._09_query.User;
import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.Date;

public class LockTest {

    @Before
    public void init() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        User u = new User(100, "pangpang", new Date());
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testPessimistic() {//悲观锁测试
        EntityManager em = JPAUtil.getEntityManager();
        EntityManager em2 = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em2.getTransaction().begin();

        User u = em.find(User.class, 1L, LockModeType.PESSIMISTIC_WRITE);//加锁
        u.setName("name1");
        User u2 = em2.find(User.class, 1L, LockModeType.PESSIMISTIC_WRITE);//程序会在此处阻塞(等待)，因为em.getTransaction().commit();在此行下面，必须要等第一个事务提交后，才能执行此行
        u2.setName("name2");

        em.getTransaction().commit();
        em2.getTransaction().commit();
        em.close();
        em2.close();
    }

    @Test
    public void testOptimistic() {//乐观锁测试
        EntityManager em = JPAUtil.getEntityManager();
        EntityManager em2 = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em2.getTransaction().begin();

        try {
            User u = em.find(User.class, 1L, LockModeType.OPTIMISTIC);//加锁
            u.setName("name1");
            User u2 = em2.find(User.class, 1L, LockModeType.OPTIMISTIC);//程序
            u2.setName("name2");
            em.getTransaction().commit();
            em2.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统繁忙");
        }
        em.close();
        em2.close();
    }
}

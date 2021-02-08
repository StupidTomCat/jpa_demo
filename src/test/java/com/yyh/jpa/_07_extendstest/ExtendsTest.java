package com.yyh.jpa._07_extendstest;

import com.yyh.jpa._07_extends.Book;
import com.yyh.jpa._07_extends.Cloth;
import com.yyh.jpa._07_extends.Product;
import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ExtendsTest {

    @Before
    public void testSave(){
        Product p = new Product();
        p.setName("p");

        Book b = new Book();
        b.setName("b");
        b.setISBN("123");

        Cloth c = new Cloth();
        c.setName("c");
        c.setColor("red");

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(p);
        em.persist(b);
        em.persist(c);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGet(){
        EntityManager em = JPAUtil.getEntityManager();
        Product book = em.find(Product.class, 2L);
        System.out.println(book);
        em.close();
    }
}

package com.yyh.jpa._06_component;

import com.yyh.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ComponentTest {

    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("yyh_company");

        //营业地址
        Address a = new Address();
        a.setProvince("江苏");
        a.setCity("苏州");
        a.setStreet("xx街道");
        //注册地址
        Address a2 = new Address();
        a2.setProvince("四川");
        a2.setCity("广安");
        a2.setStreet("双河街道");

        company.setAddress(a);
        company.setRegAddress(a2);
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        em.close();
    }
}

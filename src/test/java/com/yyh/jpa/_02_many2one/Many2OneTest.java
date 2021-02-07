package com.yyh.jpa._02_many2one;

import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class Many2OneTest {

    @Before
    public void testSave(){
        Employee e = new Employee();
        e.setName("sansan");
        Employee e2 = new Employee();
        e2.setName("pangpang");
        Dept d = new Dept();
        d.setName("沙雕部");
        e.setDept(d);
        e2.setDept(d);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //先保存one方(部门)
        em.persist(d);
        //在保存many方(员工)
        em.persist(e);
        em.persist(e2);
        em.getTransaction().commit();
        em.close();
    }
    @Test
    public void testGet(){
        EntityManager em = JPAUtil.getEntityManager();
        Employee e = em.find(Employee.class, 1L);
        //jpa牛逼，sql如下，居然自动连接查询，因为默认这样查询
        //select employee0_.id as id1_1_1_, employee0_.dept_id as dept_id3_1_1_, employee0_.name as name2_1_1_, dept1_.id as id1_0_0_, dept1_.name as name2_0_0_ from Employee employee0_ left outer join Dept dept1_ on employee0_.dept_id=dept1_.id where employee0_.id=?
        Dept d = e.getDept();
        System.out.println(e);
        System.out.println(d);
        em.close();
    }
}

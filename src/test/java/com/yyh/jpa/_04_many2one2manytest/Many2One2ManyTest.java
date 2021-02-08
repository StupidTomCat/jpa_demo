package com.yyh.jpa._04_many2one2manytest;

import com.yyh.jpa._04_many2one2many.Dept;
import com.yyh.jpa._04_many2one2many.Employee;
import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Many2One2ManyTest {

    @Before
    public void testSave(){
        Employee e = new Employee();
        e.setName("sansan");
        Employee e2 = new Employee();
        e2.setName("pangpang");
        Dept d = new Dept();
        d.setName("沙雕部");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e);
        employeeList.add(e2);
        d.setEmployeeList(employeeList);
        e.setDept(d);
        e2.setDept(d);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //有顺序的问题，因为有多对一
        em.persist(d);
        em.persist(e);
        em.persist(e2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGet(){//与单向多对一一致
        EntityManager em = JPAUtil.getEntityManager();
        Employee e = em.find(Employee.class, 1L);
        Dept d = e.getDept();
        System.out.println(e);
        System.out.println(d);
        em.close();
    }
}

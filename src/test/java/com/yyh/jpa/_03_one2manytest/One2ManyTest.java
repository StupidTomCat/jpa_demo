package com.yyh.jpa._03_one2manytest;


import com.yyh.jpa._03_one2many.Dept;
import com.yyh.jpa._03_one2many.Employee;
import com.yyh.util.JPAUtil;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class One2ManyTest {

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

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //此处就没有先后之分了，one方(部门)或者many方(员工)哪个先都一样，因为是中间表维护
        em.persist(d);
        em.persist(e);
        em.persist(e2);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGet(){
        EntityManager em = JPAUtil.getEntityManager();
        Dept d = em.find(Dept.class, 1L);
        System.out.println(d);
        List<Employee> employeeList = d.getEmployeeList();
        //默认使用延迟加载，在真正使用`employeeList`时才会去发送第二条连接查询的sql
        System.out.println(employeeList);
        em.close();
    }
}

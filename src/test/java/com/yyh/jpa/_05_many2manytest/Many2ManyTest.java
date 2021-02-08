package com.yyh.jpa._05_many2manytest;

import com.yyh.jpa._05_many2many.Student;
import com.yyh.jpa._05_many2many.Teacher;
import com.yyh.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Many2ManyTest {

    @Test
    public void testSave(){
        Teacher t = new Teacher();
        t.setName("xxx");
        Teacher t2 = new Teacher();
        t2.setName("yyy");
        Student s = new Student();
        s.setName("aaa");
        Student s2 = new Student();
        s2.setName("bbb");
        List<Student> studentList = new ArrayList<>();
        studentList.add(s);
        studentList.add(s2);

        t.setStudentList(studentList);
        t2.setStudentList(studentList);

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        //没有顺序问题，因为跟单向一对多很像
        em.persist(t);
        em.persist(t2);
        em.persist(s);
        em.persist(s2);
        em.getTransaction().commit();
        em.close();
    }
}

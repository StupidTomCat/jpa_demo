package com.yyh.jpa._09_querytest;

import com.yyh.jpa._09_query.User;
import com.yyh.util.JPAUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class QueryTest {
    @Test
    public void testQuery() {
        EntityManager em = JPAUtil.getEntityManager();
        //查询出所有用户信息  jpql是面向对象的，所以User指的是对象，select后不支持*，后面也必须给一个别名u
//        Query query = em.createQuery("select u from User u");
//        List<User> userList = query.getResultList();
//        System.out.println(userList);

        //查询出年龄大于10的用户信息
//        Query query = em.createQuery("select u from User u where u.age>10");
//        List<User> userList = query.getResultList();
//        System.out.println(userList);
//        Query query = em.createQuery("select u from User u where u.age>?1");
//        query.setParameter(1,10);
//        List<User> userList = query.getResultList();
//        System.out.println(userList);
//        Query query = em.createQuery("select u from User u where u.age>:namedage");//不使用占位符，而是取个名字namedage
//        query.setParameter("namedage",10);
//        List<User> userList = query.getResultList();
//        System.out.println(userList);

        //查询出年龄大于10的用户的年龄、姓名、雇佣日期(此处的用意是部分信息)
//        Query query = em.createQuery("select u.age,u.name,u.hireDate from User u where u.age>:namedage");//查出来的是一个个Object[]
//        query.setParameter("namedage",10);
//        List<Object[]> userList = query.getResultList();
//        for(Object[] u : userList){
//            System.out.println(Arrays.asList(u));
//        }
//        Query query = em.createQuery("select new User(u.age,u.name,u.hireDate) from User u where u.age>:namedage");//使用构造器将查询的部分数据封装到类型中，就可以直接用User接收了
//        query.setParameter("namedage",10);
//        List<User> userList = query.getResultList();
//        System.out.println(userList);

        //从第3个用户开始，查询5个用户（分页查询）
//        Query query = em.createQuery("select new User(u.age,u.name,u.hireDate) from User u");
//        query.setFirstResult(2).setMaxResults(5);
//        List<User> userList = query.getResultList();
//        System.out.println(userList);

        //查询出所有用户信息，按照年龄从大到小排序
        Query query = em.createQuery("select new User(u.age,u.name,u.hireDate) from User u order by u.age desc");
        List<User> userList = query.getResultList();
        System.out.println(userList);

        em.close();
    }

    @Test
    public void testNamedQuery(){//命名查询
        EntityManager em = JPAUtil.getEntityManager();
        //创建指定名称的查询
        Query query = em.createNamedQuery("findByAge");
        query.setParameter(1,10);
        List<User> userList = query.getResultList();
        System.out.println(userList);
        em.close();
    }

    @Test
    public void testNativeQuery(){//使用原生sql语句
        EntityManager em = JPAUtil.getEntityManager();
        //查询全部属性
//        List<User> userList = em.createNativeQuery("select * from user",User.class).getResultList();
//        System.out.println(userList);

        //查询部分属性
        Query query = em.createNativeQuery("select name,age from user");
        org.hibernate.Query hQuery = query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(User.class));//设置查询结果转换器，将结果转换成指定对象
        List<User> userList = hQuery.list();
        System.out.println(userList);

        em.close();
    }
}

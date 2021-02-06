package com.yyh.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;//这个应用中只需要创建一个该对象，所以放在静态代码块里面

    static {
        emf = Persistence.createEntityManagerFactory("myPersistence");//参数是持久单元名字
    }

    private JPAUtil(){}//工具类构造器私有化，保证单例

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();//创建EntityManager对象，即连接对象
    }
}

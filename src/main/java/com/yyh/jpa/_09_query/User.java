package com.yyh.jpa._09_query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity//扫描该实体类
@Getter//Lombok的注解
@Setter//Lombok的注解
@ToString//Lombok的注解
@NamedQueries({
        @NamedQuery(name = "findAll",query = "select u from User u"),//name：为jpql命名，query：指定jpql
        @NamedQuery(name = "findByAge",query = "select u from User u where u.age>?1")//name：为jpql命名，query：指定jpql
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @Column(name = "hiredate")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Version//对应乐观锁的列
    private Integer version;
    public User() {
    }

    public User(Integer age, String name, Date hireDate) {
        this.name = name;
        this.age = age;
        this.hireDate = hireDate;
    }
}

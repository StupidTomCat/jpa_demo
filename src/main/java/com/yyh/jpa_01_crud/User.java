package com.yyh.jpa_01_crud;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity//扫描该实体类
@Getter//Lombok的注解
@Setter//Lombok的注解
@ToString//Lombok的注解
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @Column(name = "hiredate")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
}

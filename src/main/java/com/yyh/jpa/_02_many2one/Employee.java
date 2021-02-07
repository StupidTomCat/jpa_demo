package com.yyh.jpa._02_many2one;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //单向多对一
    @ManyToOne(fetch = FetchType.LAZY)//不需要自己再去建有外键的表了，jpa会自动在员工表中创建外键列
    private Dept dept;
}

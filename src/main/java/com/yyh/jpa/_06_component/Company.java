package com.yyh.jpa._06_component;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //修改这个注册地址各个属性在表中的列名
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "province",column = @Column(name = "reg_province")),
                    @AttributeOverride(name = "city",column = @Column(name = "reg_city")),
                    @AttributeOverride(name = "street",column = @Column(name = "reg_street"))
            }
    )
    private Address regAddress;//公司注册地址
    private Address address;//公司营业地址
}

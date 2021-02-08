package com.yyh.jpa._06_component;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable//是一个组件，可嵌入到其他对象中
@Getter
@Setter
public class Address {

    private String province;
    private String city;
    private String street;
}

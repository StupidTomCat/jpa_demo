package com.yyh.jpa._07_extends;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Cloth extends Product{

    private String color;
}

package com.yyh.jpa._08_casecade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class OrderItem {//订单明细

    @Id
    @GeneratedValue
    private Long id;
    private String productName;
}

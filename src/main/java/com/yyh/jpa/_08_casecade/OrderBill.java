package com.yyh.jpa._08_casecade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class OrderBill {

    @Id
    @GeneratedValue
    private Long id;
    private String sn;//编号

    //只保存主对象(orderBill)，从对象(关联对象:item、item2)自动保存
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItemList;
}

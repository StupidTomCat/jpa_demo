package com.yyh.jpa._03_one2many;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    //关联many方数据
    @OneToMany//单向一对多默认使用延迟加载
    //按员工的姓名排序，默认升序
    @OrderBy("name")
    List<Employee> employeeList;//不能使用ArrayList，因为hibernate实现了List叫PersistentBag。
}

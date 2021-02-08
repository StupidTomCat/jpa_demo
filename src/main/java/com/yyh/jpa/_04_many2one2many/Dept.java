package com.yyh.jpa._04_many2one2many;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "dept")//让one方放弃关系的维护，即不会创建中间表，括号中的值意思是指定many方中的dept属性映射，以此维护
    List<Employee> employeeList;//不能使用ArrayList，因为hibernate实现了List叫PersistentBag。

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

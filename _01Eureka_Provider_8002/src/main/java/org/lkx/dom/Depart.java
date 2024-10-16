package org.lkx.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
@Data
@Entity //使用自动创建表
@Table(name = "cat_Depart")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
public class Depart {
    @Id //表示当前属性为自动建表的主建
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}

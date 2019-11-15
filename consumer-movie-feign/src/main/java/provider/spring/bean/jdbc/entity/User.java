package provider.spring.bean.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author JunWu
 * 实体类
 */
@Data
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private int age;

    private String sex;

}

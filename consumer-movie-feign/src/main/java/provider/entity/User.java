package provider.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: QiuJunWu
 * @Date: 2019/10/15 0015 13:03
 */
@Data
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;

}

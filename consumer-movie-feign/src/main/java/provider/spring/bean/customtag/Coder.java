package provider.spring.bean.customtag;

import lombok.Data;
import lombok.ToString;

/**
 * @author JunWu
 */
@Data
@ToString
public class Coder {

    private String id;

    private int age;

    private String name;

    public Coder() {
    }

    public Coder(int age, String name) {
        this.age = age;
        this.name = name;
    }

}

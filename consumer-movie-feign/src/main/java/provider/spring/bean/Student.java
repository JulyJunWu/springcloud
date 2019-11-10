package provider.spring.bean;

import lombok.Data;

/**
 * @author JunWu
 */
@Data
public class Student {

    private int age;

    public Student() {
    }

    public Student(int age) {
        this.age = age;
    }
}

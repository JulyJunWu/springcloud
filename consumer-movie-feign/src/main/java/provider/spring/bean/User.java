package provider.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

/**
 * @author JunWu
 */
@Slf4j
public class User implements DisposableBean {

    private String name;

    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    @Override
    public void destroy() {
        System.out.println("销毁...");
    }

    public void initMethod() {
        System.out.println("init...");
    }
}

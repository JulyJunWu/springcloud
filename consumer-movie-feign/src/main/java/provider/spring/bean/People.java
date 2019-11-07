package provider.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author JunWu
 */
@Slf4j
public class People implements BeanNameAware {

    private String name ;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String s) {
        this.name = s;
    }

    public void initMethod(){
        log.info(this.getClass().getName() + " init");
    }

    public void destroyMethod(){
        log.info(this.getClass().getName() + " destroy!");
    }
}

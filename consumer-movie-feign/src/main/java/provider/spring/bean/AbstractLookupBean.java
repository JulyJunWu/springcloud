package provider.spring.bean;

/**
 * @author JunWu
 *  用于获取器注入, 普通类也可以
 */
public abstract class AbstractLookupBean {

    public void show(){
        this.getBean().show();
    }

    /**
     * 获取bean
     * @return
     */
    public abstract LookupHuman getBean();

}

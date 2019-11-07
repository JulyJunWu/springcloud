package provider.spring.bean;

/**
 * @author JunWu
 * 静态工厂
 */
public class FactoryStudent {

    public static Student create() {
        Student student = new Student();
        student.setAge(88);
        return student;
    }

}

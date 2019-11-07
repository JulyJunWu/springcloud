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

    /**
     * 非静态创建
     */
    public Student createInstance(){
        Student student = new Student();
        student.setAge(99);
        return student;
    }

}

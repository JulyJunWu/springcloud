package provider.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author JunWu
 */
public class StudentFactory implements FactoryBean<Student> {

    @Override
    public Student getObject() {
        Student student = new Student();
        student.setAge(18);
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

package provider.spring;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import provider.spring.bean.People;
import provider.spring.bean.Student;
import provider.spring.bean.StudentFactory;
import provider.spring.bean.User;

/**
 * @author JunWu
 *
 *  XmlBeanFactory:               是按需加载
 *  ClassPathApplicationContext : 是一次性singleton加载
 *
 */
public class TestMain {


    public static void main(String[] args) throws Exception {
        //  使用classpath下路径
        ClassPathResource resource = new ClassPathResource("test/hello.xml");
        // 使用绝对路径
        //FileSystemResource systemResource = new FileSystemResource("D:\\workspace\\govdatamidground\\govdatamidground-micro\\target\\classes\\hello.xml");
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);

        User user = xmlBeanFactory.getBean(User.class);
        user.sleep();

        People people = user.getPeople();

        Student student = xmlBeanFactory.getBean(Student.class);

        Student student2 = (Student) xmlBeanFactory.getBean("student");
        System.out.println(student2);

        StudentFactory studentFactory = (StudentFactory) xmlBeanFactory.getBean("&student");
        System.out.println(studentFactory.isSingleton());
    }

    /**
     *  手动注册bean
     * @param xmlBeanFactory
     */
    public static void register(XmlBeanFactory xmlBeanFactory) {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(TestMain.class);
        definition.setBeanClassName(TestMain.class.getName());
        definition.setAttribute("age", "18");

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "Hello Id");
        definition.setPropertyValues(propertyValues);
        definition.setDestroyMethodName("destroy");
        definition.setInitMethodName("initMethod");
        //  definition.setScope("prototype");
        //  设置为主bean
        definition.setPrimary(true);
        //  注册bean, 指定bean的名称
        xmlBeanFactory.registerBeanDefinition("testUser", definition);

        User user = xmlBeanFactory.getBean("testUser", User.class);
        System.out.println(user.getName());
        //  xmlBeanFactory.destroyBean(areaNode);
        xmlBeanFactory.destroySingleton("testUser");
    }


}

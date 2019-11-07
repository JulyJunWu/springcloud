package provider.spring;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import provider.spring.bean.FactoryStudent;
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

        Student student = xmlBeanFactory.getBean(Student.class);

        Student student2 = (Student) xmlBeanFactory.getBean("student");
        Student student3 = (Student) xmlBeanFactory.getBean("student2");
        //  获取FactoryBean对象
        StudentFactory studentFactory = (StudentFactory) xmlBeanFactory.getBean("&student");
        register2(xmlBeanFactory);
    }

    /**
     *  手动注册bean
     * @param xmlBeanFactory
     */
    public static void register(XmlBeanFactory xmlBeanFactory) {
        //  xml解析底层也是使用这个GenericBeanDefinition类装载bean的各个属性
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

    /**
     * 自定义使用BeanDefinition 注册静态工厂创建bean
     * 注意: factoryMethod 必须是静态的
     * 此方式注册bean就相当于 <bean id="factoryStudent" class="provider.spring.bean.FactoryStudent" factory-method="create">
     * @param beanFactory
     */
    public static void register2(XmlBeanFactory beanFactory){

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(FactoryStudent.class.getName());
        //  create是静态方法
        beanDefinition.setFactoryMethodName("create");

        beanFactory.registerBeanDefinition("factoryStudent",beanDefinition);

        Student people = (Student) beanFactory.getBean("factoryStudent");

        System.out.println(people.getAge());
    }


}

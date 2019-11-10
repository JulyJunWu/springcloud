package provider.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Element;
import provider.spring.bean.*;
import provider.spring.bean.customtag.Coder;

/**
 * @author JunWu
 * <p>
 * XmlBeanFactory:               是按需加载
 * ClassPathApplicationContext : 是一次性singleton加载
 */
@Slf4j
public class TestMain {

    public static DefaultListableBeanFactory beanFactory;

    @Before
    public void prepare() throws Exception {
        //  使用classpath下路径
        ClassPathResource resource = new ClassPathResource("test/application.xml");
        // 使用绝对路径
        //FileSystemResource systemResource = new FileSystemResource("D:\\workspace\\govdatamidground\\govdatamidground-micro\\target\\classes\\application.xml");
        beanFactory = new XmlBeanFactory(resource);

        Student student = beanFactory.getBean(Student.class);
        Student student2 = (Student) beanFactory.getBean("student");
        Student student3 = (Student) beanFactory.getBean("student2");
        //  获取FactoryBean对象
        StudentFactory studentFactory = (StudentFactory) beanFactory.getBean("&student");
    }

    /**
     * 手动注册bean
     */
    @Test
    public void register() {
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
        beanFactory.registerBeanDefinition("testUser", definition);

        User user = beanFactory.getBean("testUser", User.class);
        System.out.println(user.getName());
        //  beanFactory.destroyBean(areaNode);
        beanFactory.destroySingleton("testUser");
    }

    /**
     * 自定义使用BeanDefinition 注册静态工厂创建bean
     * 注意: factoryMethod 必须是静态的
     * 此方式注册bean就相当于
     * <bean id="factoryStudent" class="provider.spring.bean.FactoryStudent" factory-method="create">
     */
    @Test
    public void register2() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(FactoryStudent.class.getName());
        //  create是静态方法
        beanDefinition.setFactoryMethodName("create");

        beanFactory.registerBeanDefinition("factoryStudent", beanDefinition);

        Student people = (Student) beanFactory.getBean("factoryStudent");

        System.out.println(people.getAge());
    }

    /**
     * 使用 BeanDefinition 注册
     * factoryBean 配合factoryMethod使用
     * 相当于如下:
     * <bean id="base" class="provider.spring.bean.FactoryStudent"></bean>
     * <bean id="testFactoryBean" factory-bean="base" factory-method="createInstance"></bean>
     * 注意: createInstance必须是实例方法
     */
    @Test
    public void register3() {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClassName(FactoryStudent.class.getName());
        //  前提需要的bean对象
        beanFactory.registerBeanDefinition("base", definition);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        //  指定factoryMethod , 实例方法
        beanDefinition.setFactoryMethodName("createInstance");
        //  指定factory-bean
        beanDefinition.setFactoryBeanName("base");
        //  注册
        beanFactory.registerBeanDefinition("testFactoryBean", beanDefinition);

        Student bean = (Student) beanFactory.getBean("testFactoryBean");
        System.out.println(bean.getAge());
    }

    /**
     * 使用 RootBeanDefinition 注册
     */
    @Test
    public void register4() {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestMain.class);
        beanFactory.registerBeanDefinition("Hello", rootBeanDefinition);
        Object hello = beanFactory.getBean("Hello");
    }

    /**
     * lookupMethod 获取器注入
     */
    @Test
    public void register5() {
        beanFactory.getBean("lookupTeacher");
        AbstractLookupBean lookupBean = (AbstractLookupBean) beanFactory.getBean("lookupBean");
        lookupBean.show();
    }

    /**
     * 使用ClassPathXmlApplicationContext创建 beanFactory
     */
    @Test
    public void newInstance() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/application.xml");
        Object lookupTeacher = applicationContext.getBean("lookupTeacher");
        System.out.println(applicationContext);
    }


    /**
     * replaced-method
     */
    @Test
    public void replacedMethod() {
        ReplaceMethodBean bean = beanFactory.getBean(ReplaceMethodBean.class);
        bean.dance();
    }

    /**
     * 有参构造器注入
     * 手动代码注册有参构造器((通过参数名称注入)
     *
     * <bean id="constructorInjectionByIndex" class="provider.spring.bean.Student">
     *      <constructor-arg name="age" value="66"></constructor-arg>
     * </bean>
     *
     * <bean id="constructorInjectionByIndex" class="provider.spring.bean.Student">
     *      <constructor-arg type="int" value="66"></constructor-arg>
     * </bean>
     */
    @Test
    public void constructorInjectionByName() {
        Student student = (Student) beanFactory.getBean("constructorStudent");
        System.out.println(student.getAge());

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(Student.class.getName());

        ConstructorArgumentValues value = new ConstructorArgumentValues();
        // 构造参数值
        TypedStringValue stringValue = new TypedStringValue("88");
        ConstructorArgumentValues.ValueHolder newValue = new ConstructorArgumentValues.ValueHolder(stringValue);
        // 通过参数名称注入
        //  newValue.setName("age");
        // 通过参数类型注入
        newValue.setType("int");
        value.addGenericArgumentValue(newValue);
        beanDefinition.setConstructorArgumentValues(value);

        beanFactory.registerBeanDefinition("manualStudent", beanDefinition);

        Student manualStudent = (Student) beanFactory.getBean("manualStudent");
        System.out.println(manualStudent);
    }

    /**
     * 通过指定索引 构造器注入
     * <bean id="constructorInjectionByIndex" class="provider.spring.bean.Student">
     *      <constructor-arg index="0" value="66"></constructor-arg>
     * </bean>
     */
    @Test
    public void constructorInjectionByIndex() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(Student.class.getName());

        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
        TypedStringValue stringValue = new TypedStringValue("66");
        ConstructorArgumentValues.ValueHolder holder = new ConstructorArgumentValues.ValueHolder(stringValue);
        //  指定索引以及ValueHolder
        argumentValues.addIndexedArgumentValue(0, holder);
        //  关联到BeanDefinition
        beanDefinition.setConstructorArgumentValues(argumentValues);

        beanFactory.registerBeanDefinition("constructorInjectionByIndex", beanDefinition);

        Student byIndex = beanFactory.getBean("constructorInjectionByIndex", Student.class);
        System.out.println(byIndex.getAge());
    }


    /**
     * 手动代码注册 实例属性注入
     * 相当于如下:
     * <bean id="manualInjectionProperty" class="provider.spring.bean.User">
     *      <property name="name" value="ws"></property>
     * </bean>
     */
    @Test
    public void manualInjectionProperty() {

        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClassName(User.class.getName());

        //  构造存储Property属性的集合
        MutablePropertyValues propertyList = new MutablePropertyValues();
        PropertyValue propertyValue = new PropertyValue("name", "ws");
        propertyList.addPropertyValue(propertyValue);
        //  设置属性集合
        definition.setPropertyValues(propertyList);

        beanFactory.registerBeanDefinition("manualInjectionProperty", definition);

        User manualInjectionProperty = beanFactory.getBean("manualInjectionProperty", User.class);
        System.out.println(manualInjectionProperty.getName());
    }

    /**
     * 构造器参数为Map类型 注入
     * 等同于如下:
     * <bean id="constructorInjectionByMap" class="provider.spring.bean.Human">
     *     <constructor-arg name="map">
     *         <map>
     *             <entry key="name" value="ws"></entry>
     *             <entry key="age" value="8866"></entry>
     *         </map>
     *     </constructor-arg>
     * </bean>
     *
     * 详情解析代码请看(包括解析list/map/array/set/props等等)
     * @see org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#parsePropertySubElement(Element, BeanDefinition, String)
     */
    @Test
    public void constructorInjectionByMap() {

        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClassName(Human.class.getName());

        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        //  存放map属性kv
        ManagedMap<Object, Object> params = new ManagedMap<>();
        params.put("name", "ws");
        params.put("age", "19");
        ConstructorArgumentValues.ValueHolder valueHolder = new ConstructorArgumentValues.ValueHolder(params);
        valueHolder.setName("map");
        constructorArgumentValues.addGenericArgumentValue(valueHolder);

        definition.setConstructorArgumentValues(constructorArgumentValues);

        beanFactory.registerBeanDefinition("constructorInjectionByMap", definition);

        Human human = beanFactory.getBean("constructorInjectionByMap", Human.class);

        log.info("{}",human.getMap().size());

        human = beanFactory.getBean("human", Human.class);

        log.info("{}",human.getList().size());
    }

    @Test
    public void testDependOn(){
        Object bean = beanFactory.getBean("human");
    }

    /**
     * 测试spring自定义标签
     */
    @Test
    public void customTag(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/customTag.xml");
        Coder coder = applicationContext.getBean("customTagBean",Coder.class);
        log.info("{}",coder);
    }
}

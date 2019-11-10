DefaultSingletonBeanRegistry : 主要是用来保存singleton的信息,如 实例, 实例名称,以及解决循环引用的问题等等;
    函数registerDisposableBean: 注册指定了销毁方法的实例 或者 实现了DisposableBean接口
    Map<String, Object> disposableBeans : 存放拥有销毁方法的singleton实例
    Map<String, Object> singletonObjects: 存放实例化完毕的singleton实例
    Set<String> singletonsCurrentlyInCreation: 存档正在创建的bean的名称
    Set<String> registeredSingletons: 存放已注册的实例名称
    Map<String, BeanDefinition> beanDefinitionMap;
    
AbstractBeanFactory:    
    Set<String> alreadyCreated  : 存放已创建的bean名称
    
SimpleAliasRegistry(默认工厂的父类继承了该类): 
    Map<String, String> aliasMap = new ConcurrentHashMap(16); 存放beanName的别名
    
FactoryBeanRegistrySupport: 存放实现FactoryBean接口的bean , 而FactoryBean实现类则是保存在DefaultSingletonBeanRegistry的singletonObjects变量中
    Map<String, Object> factoryBeanObjectCache : 存放实现FactoryBean接口的bean所创建的对象(getObject()获得的对象)
    
AbstractAutowireCapableBeanFactory:
    Map<String, BeanWrapper> factoryBeanInstanceCache;
    
获取FactoryBean接口实现类所产生的bean对象流程(getObject()):
    假定  <bean id="student" class="com.ws.StudentFactory(实现了FactoryBean接口)"></bean>
    StudentFactory bean 真正产生的bean其实是getObject()的对象
    获取原生FactoryBean实现类过程("&student"):
        1.通过 name = "&student" 查找
        2.通过转换name得到 beanName = "student"
        3.根据beanName从DefaultSingletonBeanRegistry.singletonObjects缓存map中获取对象(该对象是StudentFactory)
        4.将StudentFactory对象返回,流程结束!
    获取真正bean的流程("student"):
        1.通过name="student" 查找
        2.通过转换name得到 beanName = "student"
        3.根据beanName从DefaultSingletonBeanRegistry.singletonObjects缓存map中获取对象(该对象是StudentFactory)
        4.由于获取的对象(StudentFactory)实现了FactoryBean接口,并且name并非以"&"前缀开头,所以真正的name实例从
        FactoryBeanRegistrySupport.factoryBeanObjectCache缓存中获取并返回,流程结束!
        
BeanDefinitionParserDelegate.parseBeanDefinitionAttributes : 解析xml配置的各个bean的属性,如id,singleton,name等

工厂创建bean的三种方式:    
    1.实现FactoryBean接口的形式,通过getObject获取真实bean对象
         <bean id="student" class="provider.spring.bean.StudentFactory"></bean>
         StudentFactory对象实现了FactoryBean接口
    2.使用静态工厂方式创建
        <bean id="factoryStudent" class="provider.spring.bean.FactoryStudent" factory-method="create">
        create方法必须是静态方法
    3.使用factory-bean + factory-method实现
         <bean id="base" class="provider.spring.bean.FactoryStudent"></bean>
         <bean id="testFactoryBean" factory-bean="base" factory-method="createInstance"></bean>
         注意: createInstance必须是实例方法

spring默认标签:
    import , bean , beans , alias ,其他的都是自定义标签 , 自定义标签需要提供自定义的解析流程
    import : 此标签主要用于导入其他spring配置
    bean : 定义一个实例交给IOC
    beans: 主要是方便生产/开发环境,就是一个profile , 可以自由切换
    alias: 可以为bean取多个别名(效果等同于bean标签中的name属性)

获取器注入: lookup-method;

 GenericBeanDefinition属性(装载bean属性的实体类,主要属性都是存放在抽象类AbstractBeanDefinition中),下面是比较少见的属性:
    //  允许访问非公开的构造器和方法，程序设置
    private boolean nonPublicAccessAllowed = true; 
    //  用来表示个bean的实例化依靠另一个bean先实例化
    private String[] dependsOn;
    //  autowire-candidate属性设置为false,这样容器在查找自动装配对象时，将不考虑该bean,即它不会被考虑作为其他bean自动装配的候选者，
    //  但是该bean本身还是可以使用自动装配来注人其他bean的。
    private boolean autowireCandidate = true;
    //  记录构造函数注人属性，对应bean属性constructor-arg
    private ConstructorArgumentValues constructorArgumentValues;
    //  普通属性集合,就是存放<property name="age" value="18"/>此类标签数据
    private MutablePropertyValues propertyValues;
    //  方法重写的持有者，记录lookup-method、replaced-method 元素
    private MethodOverrides methodOverrides = new MethodOverrides();
    //  是否执行init-method,程序设置
    private boolean enforceInitMethod = true;
    //  是否执行destroy-method,程序设置
    private boolean enforceDestroyMethod = true;
    //  是否是用户定义的而不是应用程序本身定义的，创建AOP时候为true,程序设置
    private boolean synthetic = false; 

spring解析自定义标签:
    




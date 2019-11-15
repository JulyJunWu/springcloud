DefaultSingletonBeanRegistry : 主要是用来保存singleton的信息,如 实例, 实例名称,以及解决循环引用的问题等等;
    函数registerDisposableBean: 注册指定了销毁方法的实例 或者 实现了DisposableBean接口
    Map<String, Object> disposableBeans : 存放拥有销毁方法的singleton实例
    Map<String, Object> singletonObjects: 存放实例化完毕的singleton实例
    Set<String> singletonsCurrentlyInCreation: 存放正在创建的bean的名称
    Set<String> registeredSingletons: 存放已注册的实例名称
    Map<String, BeanDefinition> beanDefinitionMap;
    
AbstractBeanFactory:    
    Set<String> alreadyCreated  : 存放已创建的bean名称
    // BeanPostProcessors to apply in createBean
    private final List<BeanPostProcessor> beanPostProcessors
    //  存放解析特殊Property属性,比如Date无法进行注入,在此添加属性解析对value解析转换为对应的类型
    private final Map<Class<?>, Class<? extends PropertyEditor>> customEditors;
    
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
    1.创建一个需要扩展的组件.
    2.定义一个XSD文件描述组件内容。
    3.创建一个文件，实现BeanDefinitionParser接口，用来解析XSD文件中的定义和组件定义。
    4.创建一个Handler文件，扩展自NamespaceHandlerSupport,目的是将组件注册到Spring容器。
    5.编写Spring.handlers和Spring.schemas文件。默认位置是在工程的META-INF/文件夹下
    
创建bean的主要实现代码:    
    AbstractAutowireCapableBeanFactory.createBean(String, RootBeanDefinition, Object[])
    AbstractAutowireCapableBeanFactory.doCreateBean : 创建包装bean,并进行属性注入,以及进行bean的生命周期方法,如initMethod,以及spring的接口,如InitializingBean等等,各种*Aware
        主要函数:   addSingletonFactory : 主要是解决循环依赖提前暴露
                    populateBean  : 属性的注入
                    initializeBean : bean的生命周期开始
                    registerDisposableBeanIfNecessary: 注册销毁方法

循环依赖:在Spring中将循环依赖的处理分成了3种情况。
     1.构造器循环依赖
     表示通过构造器注人构成的循环依赖，此依赖是无法解决的，只能抛出BeanCurrentlyInCreationException异常表示循环依赖。
     2. setter 循环依赖
     表示通过setter注人方式构成的循环依赖。对于setter注人造成的依赖是通过Spring容器提前暴露刚完成构造器注人但未完成其他
     步骤(如setter注人)的bean来完成的，而且只能解决单例作用域的bean循环依赖。通过提前暴露一个单例工厂 方法，从而使其他
     bean能引用到;
     3. prototype 范围的依赖处理
     对于“prototype"作用域bean, Spring 容器无法完成依赖注人，因为Spring容器不进行缓存“prototype" 作用域的bean,因此无法
     提前暴露一个创建中的bean。

bean的生命周期:
    AbstractBeanFactory.getBean(String)
        AbstractBeanFactory.doGetBean(final String, final Class<T>, final Object[], boolean)
            DefaultSingletonBeanRegistry.getSingleton(String,ObjectFactory<?>)
                bstractBeanFactory.createBean(String, RootBeanDefinition, Object[]) ::注意此处其实是singletonFactory.getObject()的过程
                   // 是否有lookup-method和replace-method,并且验证是否有多个重写函数
                   mbdToUse.prepareMethodOverrides();
                   AbstractAutowireCapableBeanFactory.doCreateBean(final String, final RootBeanDefinition, final Object[])
                       // 创建实例  
                       BeanWrapper instanceWrapper = createBeanInstance(beanName, mbd, args);
                       //解决单例循环依赖(属性注入)
                       DefaultSingletonBeanRegistry.addSingletonFactory(String, ObjectFactory<?>)
                       //  属性注入
                       AbstractAutowireCapableBeanFactory.populateBean(String, RootBeanDefinition, BeanWrapper)
                       //  bean的生命周期开始,spring提供一些接口或函数对实例化完毕bean的操作
                       AbstractAutowireCapableBeanFactory.initializeBean(String,Object,RootBeanDefinition)
                            // 执行各种*Aware接口的实现,注入属性
                           AbstractAutowireCapableBeanFactory.invokeAwareMethods(final String, final Object)
                           //  执行实现BeanPostProcessor接口的before函数
                           AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(Object, String)
                           //  执行InitializingBean接口以及自定义的init-method函数
                           AbstractAutowireCapableBeanFactory.invokeInitMethods(String , final Object, RootBeanDefinition)
                               // 执行InitializingBean的afterPropertiesSet函数
                               ((InitializingBean) bean).afterPropertiesSet();
                               //  执行initMethod
                               invokeCustomInitMethod(beanName, bean, mbd);
                           //  执行BeanPostProcessor接口的after函数
                           AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(Object, String)
                       //  注册销毁函数(只有单例才会生效)
                       AbstractBeanFactory.registerDisposableBeanIfNecessary(String , Object, RootBeanDefinition)
                DefaultSingletonBeanRegistry.addSingleton(String, Object)
            // 获取真正的bean,如果name是以"&"开头,则获取FactoryBean实例,否则返回原bean
            AbstractBeanFactory.getObjectForBeanInstance

ApplicationEventMulticaster的初始化:
    AbstractApplicationContext.refresh
        AbstractApplicationContext.initApplicationEventMulticaster:
            1.如果beanFactory有指定beanName为"applicationEventMulticaster"则直接使用;
            2.否则使用默认的SimpleApplicationEventMulticaster并注册单例到beanFactory中;
注册监听器的时机:
    AbstractApplicationContext.refresh
        AbstractApplicationContext.registerListeners
        
SimpleApplicationEventMulticaster
    存放实现ApplicationListener的beanName以及bean
    AbstractApplicationEventMulticaster.ListenerRetriever defaultRetriever

ConversionService(属性转换服务):
    之前使用自定义类型转换器(PropertyEditor)从String转换为Date的方式，在Spring中还提供了另一种转换方式:使用Converter;

AOP: AnnotationAwareAspectJAutoProxyCreator
AbstractAutoProxyCreator.createProxy : 不管是jdk动态代理还是cglib代理都将走此方法创建对应的代理

JDK与Cglib方式的总结:
    如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP.
    如果目标对象实现了接口，可以强制使用CGLIB实现AOP
    如果目标对象没有实现了接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换。

如何强制使用CGLIB实现AOP:
    (1)添加CGLIB依赖
    (2)在Spring配置文件中加人<aop:aspectj-autoproxy proxy target-class="true"/>
JDK动态代理和CGLIB字节码生成的区别:
    1.JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
    2.CGLIB是针对类实现代理，主要是对指定的类生成一个子类,覆盖其中的方法,因为是继承，所以该类或方法最好不要声明成final.

org.springframework.aop.framework.JdkDynamicAopProxy.invoke

JDBC:
   Connection                               //获取链接
   PreparedStatement                        //创建PreparedStatement
   StatementCallback.doInStatement          //调用回调  
    PreparedStatementSetter.setValues       //设置属性
    PreparedStatement.executeSql            //执行SQL
    ResultSetExtractor.extractData          //结果处理
   releaseResource                          //资源回收
   
mybatis:
    MapperProxyFactory
    MapperProxy
    MapperMethod
    SqlSessionFactory
    MappedStatement                     一个标签对应一个 <select> -> MappedStatement 
    Executor
    ResultHandler

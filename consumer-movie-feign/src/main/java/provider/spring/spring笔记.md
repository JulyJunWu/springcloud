DefaultSingletonBeanRegistry : 主要是用来保存singleton的信息,如 实例, 实例名称,以及解决循环引用的问题等等;
    函数registerDisposableBean: 注册指定了销毁方法的实例 或者 实现了DisposableBean接口
    Map<String, Object> disposableBeans : 存放拥有销毁方法的singleton实例
    Map<String, Object> singletonObjects: 存放实例化完毕的singleton实例
    Set<String> singletonsCurrentlyInCreation: 存档正在创建的bean的名称
    Set<String> registeredSingletons: 存放已注册的实例名称
AbstractBeanFactory:    
    Set<String> alreadyCreated  : 存放已创建的bean名称
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
        
    
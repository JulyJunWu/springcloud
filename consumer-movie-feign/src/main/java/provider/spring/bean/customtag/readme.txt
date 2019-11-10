自定义spring配置标签

扩展Spring自定义标签配置大致需要以下几个步骤(前提是要把Spring的Core包加入项目中)。
    1.创建一个需要扩展的组件.
    2.定义一个XSD文件描述组件内容。
    3.创建一个文件，实现BeanDefinitionParser接口，用来解析XSD文件中的定义和组件定义。
    4.创建一个Handler文件，扩展自NamespaceHandlerSupport,目的是将组件注册到Spring容器。
    5.编写Spring.handlers和Spring.schemas文件。默认位置是在工程的META-INF/文件夹下



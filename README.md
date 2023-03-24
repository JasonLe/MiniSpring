# MiniSpring
A MiniSpring

极客时间课程《手把手带你写一个 MiniSpring》

# Day1
## 1. 原始版本IOC实现
使用ClassPathXmlApplicationContext扫描包下的xml文件，获取文件中的id和class，创建BeanDefinition，添加到singletons中，之后便从singletons中获取Bean

### readXML方法
readXML，这也是我们解析 Bean 的核心方法，因为配置在 XML 内的 Bean 信息都是文本信息，需要解析之后变成内存结构才能注入到容器中。该方法最开始创建了 SAXReader 对象，这个对象是 dom4j 包内提供的。随后，它通过传入的 fileName，也就是定义的 XML 名字，获取根元素，也就是 XML 里最外层的标签。然后它循环遍历标签中的属性，通过 element.attributeValue("id") 和 element.attributeValue("class") 拿到配置信息，接着用这些配置信息构建 BeanDefinition 对象，然后把 BeanDefinition 对象加入到 BeanDefinitions 列表中，这个地方就保存了所有 Bean 的定义。

### instanceBeans方法
实例化一个 Bean。因为 BeanDefinitions 存储的 BeanDefinition 的 class 只是一个类的全名，所以我们现在需要将这个名字转换成一个具体的类。我们可以通过 Java 里的反射机制，也就是 Class.forName 将一个类的名字转化成一个实际存在的类，转成这个类之后，我们把它放到 singletons 这个 Map 里，构建 ID 与实际类的映射关系。

### 解耦 ClassPathXmlApplicationContext
但是我们也可以看到，这时的 ClassPathXmlApplicationContext 承担了太多的功能，这并不符合我们常说的对象单一功能的原则。因此，我们需要做的优化扩展工作也就呼之欲出了：分解这个类，主要工作就是两个部分，一是提出一个最基础的核心容器，二是把 XML 这些外部配置信息的访问单独剥离出去，现在我们只有 XML 这一种方式，但是之后还有可能配置到 Web 或数据库文件里，拆解出去之后也便于扩展。为了看起来更像 Spring，我们以 Spring 的目录结构为范本，重新构造一下我们的项目代码结构。

package com.minis.core;

/**
 * @author whl
 * @Description
 * @date 2023/3/24 17:40
 */

public class ClassPathXmlApplicationContext implements BeanFactory {
    BeanFactory beanFactory;

    /**
     * context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
     * @param fileName
     */
    public ClassPathXmlApplicationContext(String fileName) {
        // 解析 XML 文件中的内容
        Resource resource = new ClassPathXmlResource(fileName);

        // 加载解析的内容，构建 BeanDefinition
        BeanFactory beanFactory = new SimpleBeanFactory();

        // 读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    /**
     * context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
     * @param beanName
     * @return
     * @throws BeanException
     */
    @Override
    public Object getBean(String beanName) throws BeanException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}


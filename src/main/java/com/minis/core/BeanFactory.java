package com.minis.core;

/**
 * @author whl
 * @Description
 * @date 2023/3/24 17:59
 */
public interface BeanFactory {

    /**
     * 是获取一个 Bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object getBean(String beanName) throws BeanException;

    /**
     * 注册一个 BeanDefinition
     * @param beanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);
}

package com.minis.test;

import com.minis.core.BeanException;
import com.minis.core.ClassPathXmlApplicationContext;

/**
 * @author whl
 * @Description
 * @date 2023/3/24 17:46
 */
public class TestA {
    public static void main(String[] args) throws BeanException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService) classPathXmlApplicationContext.getBean("com.minis.test.AServiceImpl");
        aService.sayHello();
    }
}

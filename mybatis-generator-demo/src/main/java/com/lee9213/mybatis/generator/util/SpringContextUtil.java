package com.lee9213.mybatis.generator.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-23 0:21
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过注解获取带注解的类
     *
     * @param annotationType 注解类型
     * @return
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return getApplicationContext().getBeansWithAnnotation(annotationType);
    }

    /**
     * 通过bean name获取bean
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }


}

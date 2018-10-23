package com.lee9213.mybatis.generator.config.handler;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>扩展配置注解</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-23 0:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExtendHandler {
}

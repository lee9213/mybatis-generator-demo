package com.lee9213.mybatis.generator.template.generator;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>扩展生成器注解</p>
 *
 * @author libo
 * @date 2018/10/23 11:33
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExtendGenerator {
}

package com.lee9213.mybatis.generator;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 10:16
 */
@SpringBootApplication
public class Generator {

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {

        new SpringApplicationBuilder(Generator.class).web(WebApplicationType.NONE).bannerMode(Banner.Mode.OFF)
            .run(args);

        AutoGenerator mpg = new AutoGenerator();
        mpg.execute();
    }
}

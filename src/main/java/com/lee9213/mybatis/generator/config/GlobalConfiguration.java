package com.lee9213.mybatis.generator.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 全局配置
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "global")
@Data
public class GlobalConfiguration {

    /**
     * 生成文件的输出目录
     */
    private String outputDir = System.getProperty("user.dir");

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = true;

    /**
     * 是否打开输出目录
     */
    private boolean open = true;

    /**
     * 开发人员
     */
    private String author = System.getProperty("user.name");

    /**
     * 开启 swagger2 模式
     */
    private boolean swagger2 = false;

//    /**
//     * 时间类型对应策略
//     */
//    private DateType dateType = DateType.TIME_PACK;

    /**
     * 各层文件名称方式，例如： %sAction 生成 UserAction
     * %s 为占位符
     */
    private String entityName = "%sEntity";
    private String mapperName = "%sMapper";
    private String xmlName = "%sMapper";
    private String serviceName = "%sService";
    private String serviceImplName = "%sServiceImpl";
    private String controllerName = "%sController";
}

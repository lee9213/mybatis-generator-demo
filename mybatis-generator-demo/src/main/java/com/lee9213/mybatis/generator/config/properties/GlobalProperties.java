package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.config.sql.enums.DateType;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

/**
 * <p>全局配置信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "global")
@Data
public class GlobalProperties {

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

    /**
     * 是否在xml中添加二级缓存配置
     */
    private boolean enableCache = false;

    /**
     * 时间类型对应策略
     */
    private DateType dateType = DateType.ONLY_DATE;

    /**
     * 各层文件名称方式，例如： %sAction 生成 UserAction
     * %s 为占位符
     */
    private String entityName = "%sEntity";
    private String voName = "%sVO";
    private String mapperName = "%sMapper";
    private String mapperXmlName = "%sMapper";
    private String extendMapperXmlName = "%sExtendMapper";
    private String serviceName = "%sService";
    private String serviceImplName = "%sServiceImpl";
    private String controllerName = "%sController";
    private String testName = "%sTest";
    private String convertName = "%sConvert";
    private String clientName = "%sClient";
    private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String exceptionName;
    private String exceptionPackage;
    private String exceptionCodeName;
    private String exceptionCodePackage;

    /**
     * 逻辑删除
     */
    private boolean logicDelete;
}

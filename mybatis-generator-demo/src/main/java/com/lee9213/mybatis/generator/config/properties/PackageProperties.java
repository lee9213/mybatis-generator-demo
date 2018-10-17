package com.lee9213.mybatis.generator.config.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 21:05
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "package")
@Data
public class PackageProperties {

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    private String parent = "com.lee9213";

    /**
     * 父包模块名。
     */
    private String moduleName = null;

    /**
     * Entity包名
     */
    private String entity = "entity";

    /**
     * Service包名
     */
    private String service = "service";

    /**
     * Service Impl包名
     */
    private String serviceImpl = "service.impl";
    /**
     * Mapper包名
     */
    private String mapper = "mapper";

    /**
     * Mapper XML包名
     */
    private String mapperXml = "mybatis";

    /**
     * Controller包名
     */
    private String controller = "controller";
}
package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.util.Constant;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 21:09
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "templates")
@Data
@Accessors(chain = true)
public class TemplateProperties {

    private String generator = Constant.GENERATOR_CONFIG;

    private String entity = Constant.TEMPLATE_ENTITY_JAVA;

    private String vo = Constant.TEMPLATE_VO_JAVA;

    private String service = Constant.TEMPLATE_SERVICE;

    private String serviceImpl = Constant.TEMPLATE_SERVICE_IMPL;

    private String mapper = Constant.TEMPLATE_MAPPER;

    private String mapperXml = Constant.TEMPLATE_MAPPER_XML;

    private String extendMapperXml = Constant.TEMPLATE_EXTEND_MAPPER_XML;

    private String controller = Constant.TEMPLATE_CONTROLLER;
}

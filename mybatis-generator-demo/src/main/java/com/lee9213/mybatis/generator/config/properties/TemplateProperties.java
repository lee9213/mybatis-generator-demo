package com.lee9213.mybatis.generator.config.properties;

import com.lee9213.mybatis.generator.util.Constant;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>模板配置信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 21:09
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "templates")
@Data
@Accessors(chain = true)
public class TemplateProperties {

    private String generator = Constant.TEMPLATE_GENERATOR_CONFIG;

    private String entity = Constant.TEMPLATE_ENTITY_JAVA;


    private String service = Constant.TEMPLATE_SERVICE;

    private String serviceImpl = Constant.TEMPLATE_SERVICE_IMPL;

    private String mapper = Constant.TEMPLATE_MAPPER;

    private String mapperXml = Constant.TEMPLATE_MAPPER_XML;

    private String vo;
    private String extendMapperXml;
    private String controller;
    private String test;
    private String convert;
}

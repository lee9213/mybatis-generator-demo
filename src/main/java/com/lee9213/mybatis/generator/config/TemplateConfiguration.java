package com.lee9213.mybatis.generator.config;

import com.lee9213.mybatis.generator.util.Constant;
import lombok.Data;
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
public class TemplateConfiguration extends AbstractConfiguration {

    private String generator = Constant.GENERATOR_CONFIG;

    private String entity = Constant.TEMPLATE_ENTITY_JAVA;

    private String entityKt = Constant.TEMPLATE_ENTITY_KT;

    private String service = Constant.TEMPLATE_SERVICE;

    private String serviceImpl = Constant.TEMPLATE_SERVICE_IMPL;

    private String mapper = Constant.TEMPLATE_MAPPER;

    private String xml = Constant.TEMPLATE_XML;

    private String controller = Constant.TEMPLATE_CONTROLLER;

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getEntity() {
        return entity;
    }

    public TemplateConfiguration setEntityKt(String entityKt) {
        this.entityKt = entityKt;
        return this;
    }

    public TemplateConfiguration setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getService() {
        return service;
    }

    public TemplateConfiguration setService(String service) {
        this.service = service;
        return this;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public TemplateConfiguration setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
        return this;
    }

    public String getMapper() {
        return mapper;
    }

    public TemplateConfiguration setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public String getXml() {
        return xml;
    }

    public TemplateConfiguration setXml(String xml) {
        this.xml = xml;
        return this;
    }

    public String getController() {
        return controller;
    }

    public TemplateConfiguration setController(String controller) {
        this.controller = controller;
        return this;
    }

}

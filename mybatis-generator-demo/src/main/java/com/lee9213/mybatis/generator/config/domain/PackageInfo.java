package com.lee9213.mybatis.generator.config.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>包名的具体信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018/10/16 15:29
 */
@Data
@Accessors(chain = true)
public class PackageInfo {

    private String generator = "";
    private String moduleName;
    private String entity;
    private String vo;
    private String mapper;
    private String mapperXml;
    private String extendMapperXml;
    private String service;
    private String serviceImpl;
    private String controller;
}

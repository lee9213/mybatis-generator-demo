package com.lee9213.mybatis.generator.config.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>路径信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018/10/16 15:29
 */
@Data
@Accessors(chain = true)
public class PathInfo {

    private String generatorPath;
    private String voPath;
    private String entityPath;
    private String mapperPath;
    private String mapperXmlPath;
    private String extendMapperXmlPath;
    private String servicePath;
    private String serviceImplPath;
    private String controllerPath;


}

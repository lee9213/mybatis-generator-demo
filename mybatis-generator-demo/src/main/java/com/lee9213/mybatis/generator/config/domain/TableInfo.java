package com.lee9213.mybatis.generator.config.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>表信息，关联到当前字段信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
@Data
@Accessors(chain = true)
public class TableInfo {

    /**
     * 表名是否被转换
     */
    private boolean convert;
    /**
     * 表名
     */
    private String name;
    /**
     * 表注释
     */
    private String comment;
    /**
     * 实体名字
     */
    private String entityName;
    /**
     * VO名字
     */
    private String voName;
    /**
     * mapper名字
     */
    private String mapperName;
    /**
     * Mapper.xml名字
     */
    private String mapperXmlName;
    /**
     * ExtendMapper.xml名字
     */
    private String extendMapperXmlName;
    /**
     * Service名字
     */
    private String serviceName;
    /**
     * ServiceImpl名字
     */
    private String serviceImplName;
    /**
     * Controller名字
     */
    private String controllerName;

    /**
     * Test名字
     */
    private String testName;
    /**
     * 表的字段列表
     */
    private List<TableField> fields;
    /**
     * 公共字段
     */
    private List<TableField> commonFields;
    /**
     * 需要导入的包
     */
    private Set<String> importPackages = new HashSet<>();

    /**
     * 是否是逻辑删除
     */
    private Boolean isLogicDelete = false;

    /**
     * 父类
     */
    private String superEntityClass;

    public void setEntityName(String entityName) {
        this.entityName = entityName;
        this.convert = !entityName.equalsIgnoreCase(name);
    }
}

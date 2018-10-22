package com.lee9213.mybatis.generator.config.domain;

import com.lee9213.mybatis.generator.config.sql.rules.IColumnType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>表字段信息</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
@Data
@Accessors(chain = true)
public class TableField {

    /**
     * 字段名是否被转换过
     */
    private boolean convert;
    /**
     * 是否为主键
     */
    private boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private boolean keyIdentityFlag;
    /**
     * 字段名称
     */
    private String name;
    /**
     * 字段在表中类型
     */
    private String type;
    /**
     * 字段在do、vo的属性名字
     */
    private String propertyName;
    /**
     * 字段在do、vo的类型
     */
    private IColumnType columnType;
    /**
     * 字段注释
     */
    private String comment;
    /**
     * 是否为关键字
     */
    private boolean keywordFlag;

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        this.convert = !propertyName.equalsIgnoreCase(name);
    }
}

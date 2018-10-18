package com.lee9213.mybatis.generator.config.domain;

import com.lee9213.mybatis.generator.config.properties.StrategyProperties;
import com.lee9213.mybatis.generator.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 表信息，关联到当前字段信息
 * </p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 14:04
 */
public class TableInfo {

    private boolean convert;
    private String name;
    private String comment;

    private String entityName;
    private String mapperName;
    private String xmlName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    private List<TableField> fields;
    /**
     * 公共字段
     */
    private List<TableField> commonFields;
    private Set<String> importPackages = new HashSet<>();
    private String fieldNames;

    public boolean isConvert() {
        return convert;
    }

    protected void setConvert(StrategyProperties strategyConfig) {
        if (strategyConfig.containsTablePrefix(name)) {
            // 包含前缀
            this.convert = true;
        } else if (strategyConfig.isCapitalModeNaming(name)) {
            // 包含
            this.convert = false;
        } else {
            // 转换字段
//            if (NamingStrategy.underline_to_camel == strategyConfig.getColumnNaming()) {
//                // 包含大写处理
//                if (StringUtils.containsUpperCase(name)) {
//                    this.convert = true;
//                }
//            } else if (!entityName.equalsIgnoreCase(name)) {
//                this.convert = true;
//            }
        }
    }

    public void setConvert(boolean convert) {
        this.convert = convert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntityPath() {
        StringBuilder ep = new StringBuilder();
        ep.append(entityName.substring(0, 1).toLowerCase());
        ep.append(entityName.substring(1));
        return ep.toString();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setEntityName(StrategyProperties strategyConfig, String entityName) {
        this.entityName = entityName;
        this.setConvert(strategyConfig);
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            this.fields = fields;
            // 收集导入包信息
//            for (TableField field : fields) {
//                if (null != field.getColumnType() && null != field.getColumnType().getPkg()) {
//                    importPackages.add(field.getColumnType().getPkg());
//                }
//                if (field.isKeyFlag()) {
//                    // 主键
//                    if (field.isConvert() || field.isKeyIdentityFlag()) {
//                        importPackages.add(com.baomidou.mybatisplus.annotation.TableId.class.getCanonicalName());
//                    }
//                    // 自增
//                    if (field.isKeyIdentityFlag()) {
//                        importPackages.add(com.baomidou.mybatisplus.annotation.IdType.class.getCanonicalName());
//                    }
//                } else if (field.isConvert()) {
//                    // 普通字段
//                    importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
//                }
//                if (null != field.getFill()) {
//                    // 填充字段
//                    importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
//                    importPackages.add(com.baomidou.mybatisplus.annotation.FieldFill.class.getCanonicalName());
//                }
//            }
        }
    }

    public List<TableField> getCommonFields() {
        return commonFields;
    }

    public void setCommonFields(List<TableField> commonFields) {
        this.commonFields = commonFields;
    }

    public Set<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(String pkg) {
        importPackages.add(pkg);
    }

//    /**
//     * 逻辑删除
//     */
//    public boolean isLogicDelete(String logicDeletePropertyName) {
//        return fields.stream().anyMatch(tf -> tf.getName().equals(logicDeletePropertyName));
//    }

//    /**
//     * 转换filed实体为xmlmapper中的basecolumn字符串信息
//     *
//     * @return
//     */
//    public String getFieldNames() {
//        if (StringUtils.isEmpty(fieldNames)) {
//            StringBuilder names = new StringBuilder();
//            IntStream.range(0, fields.size()).forEach(i -> {
//                TableField fd = fields.get(i);
//                if (i == fields.size() - 1) {
//                    names.append(fd.getName());
//                } else {
//                    names.append(fd.getName()).append(", ");
//                }
//            });
//            fieldNames = names.toString();
//        }
//        return fieldNames;
//    }

}

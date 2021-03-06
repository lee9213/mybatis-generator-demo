package ${package.entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>

import java.io.Serializable;
<#if global.swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if strategy.entityLombokModel>
import lombok.Data;
import lombok.experimental.Accessors;
</#if>

/**
 * <p>${table.comment!}</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.entityLombokModel>
@Data
@Accessors(chain = true)
</#if>
<#--<#if global.swagger2>-->
<#--@ApiModel(value="${table.voName}对象", description="${table.comment!}")-->
<#--</#if>-->
<#if table.superEntityClass??>
public class ${table.entityName} extends ${table.superEntityClass?substring(table.superEntityClass?last_index_of(".")+1)} implements Serializable {

<#else>
public class ${table.entityName} implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    <#--<#if global.swagger2>-->
    <#--@ApiModelProperty(value = "${field.comment}")-->
    <#--<#else>-->
    /**
     * ${field.comment}
     */
    <#--</#if>-->
    </#if>
    private ${field.columnType.type} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !strategy.entityLombokModel>
    <#list table.fields as field>
    public ${field.columnType.type} get${field.propertyName?cap_first}() {
        return ${field.propertyName};
    }

        <#if strategy.entityBuilderModel>
    public ${table.entityName} set${field.propertyName?cap_first}(${field.columnType.type} ${field.propertyName}) {
        <#else>
    public void set${field.propertyName?cap_first}(${field.columnType.type} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if strategy.entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>
}

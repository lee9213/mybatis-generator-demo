package ${package.vo};

import java.io.Serializable;
<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if global.swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if strategy.entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>

/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.entityLombokModel>
@Data
    <#if strategy.superEntityClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if global.swagger2>
@ApiModel(value="${table.voName}对象", description="${table.comment!}")
</#if>
<#if strategy.superEntityClass??>
public class ${table.voName} extends ${strategy.superEntityClass}<#if global.activeRecord??><${table.entityName}></#if> {
<#elseif global.activeRecord??>
public class ${table.voName} extends Model<${table.entityName}> {
<#else>
public class ${table.voName} implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    <#if global.swagger2>
    @ApiModelProperty(value = "${field.comment}")
    <#else>
    /**
     * ${field.comment}
     */
    </#if>
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
    public ${table.entity} set${field.propertyName?cap_first}(${field.columnType.type} ${field.propertyName}) {
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

<#if global.activeRecord??>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !strategy.entityLombokModel>
    @Override
    public String toString() {
        return "${table.voName}{" +
    <#list table.fields as field>
        <#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
        <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}

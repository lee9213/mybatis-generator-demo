package ${package.entity};

<#if global.logicDelete??>
import com.baomidou.mybatisplus.annotation.TableLogic;
</#if>
<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#list table.entityImportPackages as pkg>
import ${pkg};
</#list>
<#if strategy.superEntityClass??>
<#else>
import java.io.Serializable;
</#if>
<#if strategy.entityLombokModel>
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
</#if>

/**
 * <p>${table.comment!}</p>
 *
 * @author ${global.author}
 * @date ${global.date}
 */
<#if strategy.entityLombokModel>
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
</#if>
<#if strategy.superEntityClass??>
public class ${table.entityName} extends ${strategy.superEntityClass?substring(strategy.superEntityClass?last_index_of(".")+1)} {
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
    <#if global.logicDelete??>
       <#if field.propertyName == 'isDelete'>
    @TableLogic
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

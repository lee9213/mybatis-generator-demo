package ${package.convert};


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.lee9213.als.core.base.PageInfo;
import ${package.vo}.${table.voName};
import ${package.entity}.${table.entityName};
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author libo
 * @date 2019/2/19 18:41
 */
public final class ${table.convertName} {

    /**
     * 从${table.voName}转换到${table.entityName}
     *
     * @param ${table.voName?uncap_first} ${table.comment!}VO
     * @return ${table.comment!}
     */
    public static ${table.entityName} convert(${table.voName} ${table.voName?uncap_first}) {
        if (Objects.isNull(${table.voName?uncap_first})) {
            return ${table.entityName}.builder().build();
        }

        return <#list table.fields as field><#if field_index == 0>${table.entityName}.builder().${field.propertyName}(${table.voName?uncap_first}.get${field.propertyName?cap_first}())<#else>.${field.propertyName}(${table.voName?uncap_first}.get${field.propertyName?cap_first}())</#if><#if !field_has_next>.build();</#if></#list>
    }

    /**
     * 从${table.entityName}转换到${table.voName}
     *
     * @param ${table.entityName?uncap_first} ${table.comment!}
     * @return ${table.comment!}VO
     */
    public static ${table.voName} convert(${table.entityName} ${table.entityName?uncap_first}) {
        if (Objects.isNull(${table.entityName?uncap_first})) {
            return ${table.voName}.builder().build();
        }
        return <#list table.fields as field><#if field_index == 0>${table.voName}.builder().${field.propertyName}(${table.entityName?uncap_first}.get${field.propertyName?cap_first}())<#else>.${field.propertyName}(${table.entityName?uncap_first}.get${field.propertyName?cap_first}())</#if><#if !field_has_next>.build();</#if></#list>
        <#--BeanUtils.copyProperties(${table.entityName?uncap_first}, ${table.voName?uncap_first});-->
    }

    /**
     * 从 PageInfo<${table.entityName}>	转换到 	PageInfo<${table.voName}>
     *
     * @param pageInfo 分页信息
     * @return 分页信息VO
     */
    public static PageInfo<${table.voName}> convertPageInfo(PageInfo<${table.entityName}> pageInfo) {
        PageInfo<${table.voName}> page = new PageInfo<>();
        List<${table.voName}> ${table.voName?uncap_first}List = Lists.newArrayList();
        if (Objects.isNull(pageInfo) || CollectionUtils.isEmpty(pageInfo.getRecords())) {
            return page;
        }
        for (${table.entityName} ${table.entityName?uncap_first} : pageInfo.getRecords()) {
            ${table.voName?uncap_first}List.add(convert(${table.entityName?uncap_first}));
        }
        page.setList(${table.voName?uncap_first}List);
        return page;
    }
}

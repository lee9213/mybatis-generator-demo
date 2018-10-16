package ${package.service};

import ${package.entity}.${table.entityName};
<#if strategy.superServiceClass?default("")?length gt 1>
import ${strategy.superServiceClass};
</#if>

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.superServiceClass?default("")?length gt 1>
public interface ${table.serviceName} extends ${strategy.superServiceClass}<${table.entityName}> {
<#else>
public interface ${table.serviceName} {
</#if>

}

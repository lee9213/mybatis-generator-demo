package ${package.service};

<#if strategy.superServiceClass?default("")?length gt 1>
import ${strategy.superServiceClass};
</#if>
import ${package.entity}.${table.entityName};

/**
 * <p>${table.comment!} 服务类</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.superServiceClass?default("")?length gt 1>
public interface ${table.serviceName} extends ${strategy.superServiceClass?substring(strategy.superServiceClass?last_index_of(".")+1)}<${table.entityName}> {
<#else>
public interface ${table.serviceName} {
</#if>

}

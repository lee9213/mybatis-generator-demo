package ${package.serviceImpl};

<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>
import ${package.entity}.${table.entityName};
import ${package.mapper}.${table.mapperName};
import ${package.service}.${table.serviceName};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Slf4j
@Service
<#if strategy.superServiceImplClass?default("")?length gt 1>
public class ${table.serviceImplName} extends ${strategy.superServiceImplClass?substring(strategy.superServiceImplClass?last_index_of(".")+1)}<${table.mapperName}, ${table.entityName}> implements ${table.serviceName} {
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
</#if>

}
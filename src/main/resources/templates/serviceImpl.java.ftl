package ${package.ServiceImpl};

import ${package.Entity}.${table.entityName};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
<#if strategy.superServiceImplClass?default("")?length gt 1>
import ${strategy.superServiceImplClass};
</#if>
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
@Service
<#if strategy.superServiceImplClass?default("")?length gt 1>
public class ${table.serviceImplName} extends ${strategy.superServiceImplClass}<${table.mapperName}, ${table.entityName}> implements ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {

}
</#if>

package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if superServiceClass??>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
<#else>
public interface ${table.serviceName} {

}
</#if>

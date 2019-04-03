package ${package.controller};

import ${global.exceptionPackage};
import com.lee9213.als.common.vo.Result;
import ${package.client}.${table.clientName};
import ${package.convert}.${table.convertName};
import ${package.entity}.${table.entityName};
import ${package.vo}.${table.voName};
import ${package.service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
<#if strategy.restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if strategy.superControllerClass?default("")?length gt 1>
import ${strategy.superControllerClass};
</#if>

/**
 * <p>${table.comment!}Controller</p>
 *
 * @author ${global.author}
 * @since ${global.date}
 */
<#if strategy.restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if strategy.superControllerClass?default("")?length gt 1>
public class ${table.controllerName} extends ${strategy.superControllerClass} implements ${table.clientName} {
<#else>
public class ${table.controllerName} implements ${table.clientName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @Override
    public Result save(${table.voName} ${table.voName?uncap_first}) throws BusinessException {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convert(${table.voName?uncap_first});
        return Result.success(${table.serviceName?uncap_first}.save(${table.entityName?uncap_first}));
    }

    @Override
    public Result detail(Long id) throws BusinessException {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return Result.success(${table.convertName}.convert(${table.entityName?uncap_first}));
    }


    @Override
    public Result update(${table.voName} ${table.voName?uncap_first}) throws BusinessException {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convert(${table.voName?uncap_first});
        return Result.success(${table.serviceName?uncap_first}.updateById(${table.entityName?uncap_first}));
    }


    @Override
    public Result delete(Long id) throws BusinessException {
        return Result.success(${table.serviceName?uncap_first}.removeById(id));
    }


    @Override
    public Result list(PageCondition pageCondition) throws BusinessException {
        return ${table.serviceName?uncap_first}.list(pageCondition);
    }

}


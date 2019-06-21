package ${package.controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${global.exceptionCodePackage};
import ${global.exceptionPackage};
import com.lee9213.als.common.vo.Result;
import com.lee9213.als.core.base.vo.request.PageRequest;
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
import com.lee9213.als.utility.other.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
<#if global.swagger2>
@Api(value = "/${table.name?replace("_","/")}", description = "${table.comment!}")</#if>
<#if strategy.superControllerClass?default("")?length gt 1>
public class ${table.controllerName} extends ${strategy.superControllerClass} implements ${table.clientName} {
<#else>
public class ${table.controllerName} implements ${table.clientName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    <#if global.swagger2>
    @ApiOperation(value = "创建${table.comment!}")</#if>
    @Override
    public Result save(${table.voName} ${table.voName?uncap_first}) {
        ObjectUtil.requireNonNull(${table.voName?uncap_first},
            ${global.exceptionName}.error(${global.exceptionCodeName}.PARAMETER_NOT_NULL));
        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convertVO(${table.voName?uncap_first});
        return Result.success(${table.serviceName?uncap_first}.save(${table.entityName?uncap_first}));
    }

    <#if global.swagger2>
    @ApiOperation(value = "获取${table.comment!}详情", response = ${table.voName}.class)</#if>
    @Override
    public Result detail(Long id) {
        ${table.entityName} ${table.entityName?uncap_first} = ${table.serviceName?uncap_first}.getById(id);
        return Result.success(${table.convertName}.convertDO(${table.entityName?uncap_first}));
    }

    <#if global.swagger2>
    @ApiOperation(value = "修改${table.comment!}")</#if>
    @Override
    public Result update(${table.voName} ${table.voName?uncap_first}) {
        ObjectUtil.requireNonNull(${table.voName?uncap_first},
            ${global.exceptionName}.error(${global.exceptionCodeName}.PARAMETER_NOT_NULL));
        ${table.entityName} ${table.entityName?uncap_first} = ${table.convertName}.convertVO(${table.voName?uncap_first});
        return Result.success(${table.serviceName?uncap_first}.updateById(${table.entityName?uncap_first}));
    }

    <#if global.swagger2>
    @ApiOperation(value = "删除${table.comment!}")</#if>
    @Override
    public Result delete(Long id) {
        return Result.success(${table.serviceName?uncap_first}.removeById(id));
    }

    <#if global.swagger2>
    @ApiOperation(value = "分页查询${table.comment!}", response = ${table.voName}.class, responseContainer = "List")</#if>
    @Override
    public Result page(PageRequest pageRequest) {
        return Result.success(${table.serviceName?uncap_first}.page(new Page<>(pageRequest.getPageNum(), pageRequest.getPageNum())));
    }
}


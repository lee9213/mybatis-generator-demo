package ${package.controller};

import ${package.service}.${table.serviceName};
import ${package.vo}.${table.voName};
<#if global.swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
<#if strategy.restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<#if !strategy.restControllerStyle>
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/${table.name?replace("_","/")}")
<#if global.swagger2>
@Api(value = "/${table.name?replace("_","/")}", description = "${table.comment!}")
</#if>
<#if strategy.superControllerClass?default("")?length gt 1>
public class ${table.controllerName} extends ${strategy.superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 创建${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    <#if global.swagger2>
    @ApiOperation(value = "创建${table.comment!}")</#if>
    public ${table.voName} create(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.add(${table.voName?uncap_first}, loginEmployee);
    }

    /**
     * 获取${table.comment!}详情
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    <#if global.swagger2>
    @ApiOperation(value = "获取${table.comment!}详情",response = ${table.voName}.class)</#if>
    public ${table.voName} detail(@PathVariable Integer id, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.detail(id, loginEmployee);
    }


    /**
     * 修改${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    <#if global.swagger2>
    @ApiOperation(value = "修改${table.comment!}")</#if>
    public void update(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.update(${table.voName?uncap_first}, loginEmployee);
    }


    /**
     * 删除${table.comment!}
     *
     * @param id
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    <#if global.swagger2>
    @ApiOperation(value = "删除${table.comment!}")</#if>
    public void delete(@PathVariable Integer id, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.delete(id, loginEmployee);
    }


    /**
     * 获取${table.comment!}列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    <#if global.swagger2>
    @ApiOperation(value = "分页查询${table.comment!}",response = ${table.voName}.class, responseContainer = "List")</#if>
    public Page<${table.voName}> list(@RequestBody PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.list(pageCondition, loginEmployee);
    }

}


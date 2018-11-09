package ${package.controller};


import com.giants.common.exception.BusinessException;
import com.giants.common.tools.Page;
import com.giants.common.tools.PageCondition;
import com.juma.auth.employee.domain.LoginEmployee;
import ${package.entity}.${table.entityName};
import ${package.service}.${table.serviceName};
import ${package.vo}.${table.voName};
import org.springframework.beans.factory.annotation.Autowired;
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
<#if strategy.superControllerClass?default("")?length gt 1>
public class ${table.controllerName} extends ${strategy.superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 新增${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    <#if !strategy.restControllerStyle>
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ${table.voName} add(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.add(${table.voName?uncap_first}, loginEmployee);
    }
    <#else>
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ${table.voName} add(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.add(${table.voName?uncap_first}, loginEmployee);
    }
    </#if>

    /**
     * 获取${table.comment!}详情
     *
     * @param id
     */
    <#if !strategy.restControllerStyle>
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ${table.voName} detail(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.detail(id, loginEmployee);
    }
    <#else>
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ${table.voName} detail(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.detail(id, loginEmployee);
    }
    </#if>


    /**
     * 修改${table.comment!}
     *
     * @param ${table.voName?uncap_first}
     */
    <#if !strategy.restControllerStyle>
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    <#else>
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.update(${table.voName?uncap_first}, loginEmployee);
    }
    </#if>


    /**
     * 删除${table.comment!}
     *
     * @param id
     */
    <#if !strategy.restControllerStyle>
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.delete(id, loginEmployee);
    }
    <#else>
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.delete(id, loginEmployee);
    }
    </#if>



    /**
     * 获取${table.comment!}列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    <#if !strategy.restControllerStyle>
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<${table.voName}> list(@RequestBody PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.list(pageCondition, loginEmployee);
    }
    <#else>
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<${table.voName}> list(@RequestBody PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.list(pageCondition, loginEmployee);
    }
    </#if>

}


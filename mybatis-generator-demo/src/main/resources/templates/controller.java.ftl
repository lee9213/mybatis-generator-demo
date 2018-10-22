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
import org.springframework.web.bind.annotation.ResponseBody;
<#if strategy.superControllerClass?default("")?length gt 1>
import ${strategy.superControllerClass};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
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
     * add 新增接口
     *
     * @param ${table.voName?uncap_first}
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public ${table.entityName} add(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.add(${table.voName?uncap_first}, loginEmployee);
    }

    /**
     * detail 详情接口
     *
     * @param id
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public ${table.voName} detail(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        return ${table.serviceName?uncap_first}.detail(id, loginEmployee);
    }

    /**
     * update 更新接口
     *
     * @param ${table.voName?uncap_first}
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public void update(@RequestBody ${table.voName} ${table.voName?uncap_first}, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.update(${table.voName?uncap_first}, loginEmployee);
    }


    /**
     * delete  删除接口
     *
     * @param id
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public void delete(Integer id, LoginEmployee loginEmployee) throws BusinessException {
        ${table.serviceName?uncap_first}.delete(id, loginEmployee);
    }


    /**
     * 列表
     *
     * @param pageCondition
     * @param loginEmployee
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Page<${table.voName}> list(@RequestBody PageCondition pageCondition, LoginEmployee loginEmployee) throws BusinessException {
    return ${table.serviceName?uncap_first}.list(pageCondition, loginEmployee);
    }
}


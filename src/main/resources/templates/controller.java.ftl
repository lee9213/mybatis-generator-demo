package ${package.Controller};


<#if strategy.restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("<#if package.ModuleName?default("")?length gt 1>/${package.ModuleName}</#if>/<#if strategy.controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if strategy.superControllerClass?default("")?length gt 1>
public class ${table.controllerName} extends ${strategy.superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
}


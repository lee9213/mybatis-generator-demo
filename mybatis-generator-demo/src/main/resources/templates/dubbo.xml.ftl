
<#list tables as table>
<dubbo:service interface="${package.service}.${table.serviceName}" ref="${table.serviceImplName?uncap_first}"/>
</#list>



<#list tables as table>
<dubbo:reference id="${table.serviceName?uncap_first}" interface="${package.service}.${table.serviceName}" version="${r'${dubbo.scm.reference.version}'}"/>
</#list>



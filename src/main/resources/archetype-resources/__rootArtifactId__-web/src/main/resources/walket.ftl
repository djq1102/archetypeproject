<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring/>

<#macro showError path separator classOrStyle="">
<@spring.bind path/>
<@spring.showErrors separator classOrStyle/>	
</#macro>
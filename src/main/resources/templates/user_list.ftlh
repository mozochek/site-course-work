<#import "parts/html_default.ftlh" as html>

<@html.page "Список всех пользователей">
    <#list users as u>
        <div>${u.username} - <#list u.roles as role> ${role} </#list></div>
    </#list>
</@html.page>
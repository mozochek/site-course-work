<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Список пользователей" pageHeaderText="Список пользователей">
  <!-- Список всех зарегестрированных пользователей -->
  <#list users?sort_by("id") as u>
    <div class="row justify-content-center">
      <div class="col-6">
        <div class="form-group">
          <div class="text-left">${(u.username)!"<имя пользователя?>"} - <#list u.roles as role> ${(role)!"<роль?>"}<#sep>, </#sep></#list></div>
        </div>
      </div>
    </div>
  </#list>
</@content.zone>
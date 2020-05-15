<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Начальная страница" pageHeaderText="Начальная страница">
  <#include "parts/security.ftlh">
  <#if !user??>
    <h3>
      Приветствуем Вас!<br>
      Чтобы получить доступ к сайту необходимо пройти <a href="/registration">регистрацию</a>
      или <a href="/login">авторизацию</a>
    </h3>
  <#else>
    <h3>
      Приветствуем Вас, ${user.username}!
    </h3>
  </#if>
</@content.zone>

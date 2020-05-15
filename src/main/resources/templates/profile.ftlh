<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Профиль" isHeaderNeeded=false>
<#include "parts/security.ftlh">
  <div class="text-center my-md-4">
    <h3>${username}<#if user.isAdmin()><span class="badge badge-success">Администратор</span></#if></h3>
  </div>
</@content.zone>
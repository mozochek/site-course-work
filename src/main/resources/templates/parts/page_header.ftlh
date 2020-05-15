<#macro header pageHeaderText="Стандартный заголовок" isHeaderNeeded=true>
<#if isHeaderNeeded == true>
  <!-- Заголовок страницы -->
  <div class="text-center my-4">
    <h1>${pageHeaderText}</h1>
  </div>
</#if>
</#macro>
<#import "html_page.ftlh" as html>
<#import "page_header.ftlh" as page>

<#macro zone pageTitleText="Стандартный заголовок" pageHeaderText="Стандартный заголовок" isHeaderNeeded=true>
  <@html.page pageTitleText>
    <div class="d-flex justify-content-center">
      <div class="col-10">
        <@page.header pageHeaderText isHeaderNeeded/>
        <!-- Контент -->
        <div class="text-center">
          <#nested>
        </div>
      </div>
    </div>
  </@html.page>
</#macro>
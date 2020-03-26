<#import "parts/html_default.ftlh" as html>

<@html.page "Главная страница">
    <div class="text-center">
        <p><h1>Это главная страница</h1></p>
    </div>
    <#if tournaments??>
        <table class="table table-hover table-dark">
        <caption>Список турниров</caption>
          <thead>
            <tr>
                <th scope="col">Название турнира</th>
                <th scope="col">Дисциплина</th>
                <th scope="col">Место проведения</th>
                <th scope="col">Формат</th>
            </tr>
          </thead>
          <tbody>
            <#list tournaments as t>
                <tr>
                  <th scope="row"><a href="/tournament/${t.id}">${t.name}</a></th>
                  <td>${t.sportDiscipline.name}</td>
                  <td>${t.city}</td>
                  <td>${t.format}</td>
                </tr>
            </#list>
          </tbody>
        </table>
    <#else>
        Нет турниров!
    </#if>
</@html.page>
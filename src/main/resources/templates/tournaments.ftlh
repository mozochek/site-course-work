<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Список турниров" pageHeaderText="Список турниров">
  <#if tournaments??>
    <!-- Список всех турниров -->
    <table class="table table-responsive table-hover table-dark">
      <thead>
        <tr>
          <th scope="col-2">Название турнира</th>
          <th scope="col">Дисциплина</th>
          <th scope="col">Место проведения</th>
          <th scope="col">Дата проведения</th>
          <th scope="col">Ворастная группа</th>
          <th scope="col">Пол игроков</th>
        </tr>
      </thead>
      <tbody>
        <#list tournaments?sort_by("dateFrom")?reverse as t>
          <tr>
            <th scope="row"><a href="/tournament/${(t.id)!"-1"}">${(t.name)!"<название?>"}</a></th>
            <td>${(t.sportDiscipline.getDescription())!"<вид спорт - дисциплина?>"}</td>
            <td>${(t.city)!"<город?>"}</td>
            <td>${(t.dateFrom)!"<дата с?>"}-${(t.dateTill)!"<дата до?>"}</td>
            <td>${(t.ageGroup)!"<возростная группа?>"}</td>
            <td>${(t.gender.getName())!"<пол игроков?>"}</td>
          </tr>
        <#else>
          <tr><th scope="row"><нет турниров?></th></tr>
        </#list>
      </tbody>
    </table>
  <#else>
    <span>Турниров нет!</span>
  </#if>
</@content.zone>
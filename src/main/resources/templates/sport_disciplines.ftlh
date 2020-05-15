<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Список спортивных дисциплин" pageHeaderText="Список спортивных дисциплин">
  <div class="col-10 text-left">
    <ul>
      <#list sportDisciplines?sort_by("name") as sportDiscipline>
        <li>
          ${(sportDiscipline.code)!"<код?>"} | ${(sportDiscipline.name)!"<название?>"}
          <a href="/api/sport_discipline/${(sportDiscipline.id)!"-1"}/edit">Изменить</a>
          <#--<a href="/api/sport_discipline/${(sportDiscipline.id)!"-1"}/delete">Удалить</a>-->
        </li>
      <#else>
        <li>
          <спортивные дисциплины ещё не добавлены>
          <a href="/api/sport_discipline/add">Добавить</a>
        </li>
      </#list>
    </ul>
  </div>
</@content.zone>
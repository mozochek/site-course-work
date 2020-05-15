<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Список видов спорта" pageHeaderText="Список видов спорта">
  <div class="col-10 text-left">
    <ul>
      <#list sportKinds?sort_by("name") as sportKind>
        <li>
          <span class="font-weight-bold">
            ${(sportKind.code)!"<код?>"} | ${(sportKind.name)!"<название?>"}
          </span>
          <a href="/api/sport_kind/${(sportKind.id)!"-1"}/edit">Изменить</a>
          <#--<a href="/api/sport_kind/${(sportKind.id)!"-1"}/delete">Удалить</a>-->
        </li>
        <ul>
          <#list (sportKind.sportDisciplines)?sort_by("name") as sportDiscipline>
            <li>
              ${(sportDiscipline.code)!"<код?>"} | ${(sportDiscipline.name)!"<название?>"}
              <a href="/api/sport_discipline/${(sportDiscipline.id)!"-1"}/edit">Изменить</a>
              <#--<a href="/api/sport_discipline/${(sportDiscipline.id)!"-1"}/delete">Удалить</a>-->
            </li>
          <#else>
            <li>
              <спортивные дисциплины ещё не добавлены>
              <a href="/api/sport_kind/add">Добавить</a>
            </li>
          </#list>
        </ul>
      <#else>
        <li>
          <виды спорта еще не добавлены>
          <a href="/admin/sport_kind/add">Добавить</a>
        </li>
      </#list>
    </ul>
  </div>
</@content.zone>
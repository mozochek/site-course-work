<#import "parts/content.ftlh" as content>

<@content.zone pageTitleText="Список турниров" pageHeaderText="Список турниров">
  <div class="col-10 text-left">
    <ul>
      <#list people?sort_by("surname") as human>
        <li>
          ${(human.surname)!"<фамилия?>"} ${(human.name)!"<имя?>"}<#if (human.patronymic)??> ${(human.patronymic)}</#if><#if (human.city)??>, ${human.city}</#if> (${human.printBirthDate()})
          <a href="/api/human/${(human.id)!"-1"}/edit">Изменить</a>
          <#--<a href="/api/human/${human.id}/delete">Удалить</a>-->
        </li>
      <#else>
        <li><люди ещё не добавлены> <a href="/api/human/add">Добавить</a></li>
      </#list>
    </ul>
  </div>
</@content.zone>
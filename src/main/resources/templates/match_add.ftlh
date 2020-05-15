<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText="Добавить матч" pageHeaderText="Добавить матч для турнира: ${tournament.name}">
  <form method="post">
    <!-- Выбор первой команды -->
    <@elem.rowAlignment 8>
      <div class="text-left">
        <label for="firstTeamSelector">Первая команда</label>
        <select class="custom-select" id="firstTeamSelector" name="firstTeam" required>
          <option value="-1"><не выбрано></option>
          <#-- Список команд турнира, потенциальных участниц этого конкретного матча -->
          <#list (tournament.teams)?sort_by("name") as team>
            <#-- Список игроков команды -->
            <option value="${(team.id)!"-1"}">${(team.name)!"<название?>"} | <#list (team.people)?sort_by("surname") as human>${(human.surname)!"<фамилия?>"} ${(human.name)!"<имя?>"}<#sep>, </#sep><#else><нет игроков></#list></option>
          </#list>
        </select>
      </div>
    </@elem.rowAlignment>
    <!-- Выбор второй команды -->
    <@elem.rowAlignment 8>
      <div class="text-left">
        <label for="secondTeamSelector">Вторая команда</label>
        <select class="custom-select" id="secondTeamSelector" name="secondTeam" required>
          <option value="-1"><не выбрано></option>
          <#-- Список команд турнира, потенциальных участниц этого конкретного матча -->
          <#list (tournament.teams)?sort_by("name") as team>
            <#-- Список игроков команды -->
            <option value="${(team.id)!"-1"}">${(team.name)!"<название?>"} | <#list (team.people)?sort_by("surname") as human>${(human.surname)!"<фамилия>?"} ${(human.name)!"<имя?>"}<#sep>, </#sep><#else><нет игроков></#list></option>
          </#list>
        </select>
      </div>
    </@elem.rowAlignment>
    <!-- Поле ввода кол-ва игр матча -->
    <@elem.rowAlignment 8>
      <div class="text-left">
        <label for="gamesCount">Кол-во игр матча</label>
        <input type="number" min="1" max="64" class="form-control" id="gamesCount" name="gamesCount" placeholder="Кол-во игр в матче" required>
      </div>
    </@elem.rowAlignment>
    <@button.submit "Добавить">
      <#if teamError??>
        <input class="form-control is-invalid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="invalid-feedback" for="submitFeedback">
          ${teamError}
        </div>
      </#if>
      <#if saveSuccessful??>
        <input class="form-control is-valid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="valid-feedback" for="submitFeedback">
          ${saveSuccessful}
        </div>
      </#if>
    </@button.submit>
  </form>
</@content.zone>
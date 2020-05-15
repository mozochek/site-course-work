<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText=(tournament.name)!"Турнир" pageHeaderText=(tournament.name)!"Турнир">
  <form method="post">
    <!-- Выбор команды -->
    <@elem.rowAlignment 6>
      <label for="teamSelector">Команда</label>
      <select class="custom-select" id="teamSelector" name="team" required>
        <option selected value="-1"><не выбрано></option>
        <#list (tournament.teams)?sort_by("name") as team>
          <option value="${(team.id)!"-1"}">${(team.name)!"<название?>"}</option>
        <#else>
          <option><нет команд?></option>
        </#list>
      </select>
    </@elem.rowAlignment>
    <!-- Выбор участника -->
    <span>Участники</span>
    <#list 1..(tournament.teamCapacity) as count>
      <@elem.rowAlignment 6>
        <select class="custom-select" id="humanSelector" name="human${count}" required>
          <option value="-1"><не выбрано></option>
          <#-- Список людей -->
          <#list humans?sort_by("surname") as human>
            <option value="${(human.id)!"-1"}">${(human.surname)!"<фамилия?>"} ${(human.name)!"<имя?>"}<#if (human.patronymic)??> ${(human.patronymic)}</#if><#if (human.city)??>, ${(human.city)}</#if></option>
          <#else>
            <option><нет людей?></option>
          </#list>
        </select>
      </@elem.rowAlignment>
    <#else>
      <span><кол-во людей в команде?></span>
    </#list>
    <@button.submit "Добавить">
      <#if teamError?? || humanError??>
        <input class="form-control is-invalid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="invalid-feedback" for="submitFeedback">
          <#if teamError??>${teamError}<br></#if>
          <#if humanError??>${humanError}</#if>
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
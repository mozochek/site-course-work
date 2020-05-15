<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText="Добавить судью" pageHeaderText="Добавить судью">
  <form method="post">
    <!-- Выбор судьи -->
    <@elem.rowAlignment 6>
      <label for="humanSelector">Судья</label>
      <select class="custom-select" id="humanSelector" name="human" required>
        <option selected value="-1"><не выбрано></option>
        <#-- Список людей, потенциальных судей -->
        <#list humans?sort_by("surname") as human>
          <option value="${(human.id)!"-1"}">${(human.surname)!"<имя?>"} ${(human.name)!"<фамилия?>"}<#if (human.patronymic)??> ${(human.patronymic)}</#if><#if (human.city)??>, ${(human.city)}</#if></option>
        </#list>
      </select>
    </@elem.rowAlignment>
    <@button.submit "Добавить">
      <#if saveSuccessful??>
        <input class="form-control is-valid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="valid-feedback" for="submitFeedback">
          ${saveSuccessful}
        </div>
      </#if>
    </@button.submit>
  </form>
</@content.zone>
<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText="Регистрация нового турнира" pageHeaderText="Регистрация нового турнира">
  <form method="post">
    <!-- Выбор вида спорта -->
    <@elem.rowAlignment 6>
      <label for="sportKindSelector">Выберите вид спорта</label>
      <select class="custom-select" id="sportKindSelector" name="sportKind" required>
        <#list sportKinds?sort_by("code") as sportKind>
          <option value="${(sportKind.id)!"-1"}"<#if prevSelectedSportKind?? && sportKind.id == prevSelectedSportKind> selected</#if>>${(sportKind.code)!"<код?>"} | ${(sportKind.name)!"<название?>"}</option>
        <#else>
          <option value="-1"><нет видов спорта?></option>
        </#list>
      </select>
    </@elem.rowAlignment>
    <@button.submit "Зарегестрировать">
      <#if sportDisciplinesError??>
        <input class="form-control is-invalid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="invalid-feedback" for="submitFeedback">
          ${sportDisciplinesError}
          <a href="/api/sport_discipline/add">Добавить</a>
        </div>
      </#if>
    </@button.submit>
  </form>
</@content.zone>
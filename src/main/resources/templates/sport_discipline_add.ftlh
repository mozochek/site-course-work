<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<#assign title="Добавить спортивную дисциплину">
<#if (sportDiscipline.id)??>
  <#assign title="Изменить спортивную дисциплину">
</#if>
<@content.zone pageTitleText=title pageHeaderText=title>
  <form method="post">
    <!-- Выбор вида спорта -->
    <@elem.rowAlignment 6>
      <label for="sportKindSelector">Выберите вид спорта</label>
      <select class="custom-select" id="sportKindSelector" name="sportKind" required>
        <#-- Список всех видов спорта -->
        <#list sportKinds?sort_by("code") as sportKind>
          <option value="${(sportKind.id)!"-1"}"<#if (sportDiscipline.sportKind)?? && sportKind.id == (sportDiscipline.sportKind).id> selected</#if>>${(sportKind.code)!"<код?>"} | ${(sportKind.name)!"<название?>"}</option>
        <#else>
          <option value="-1"><отсутствуют виды спорта?></option>
        </#list>
      </select>
      <input class="form-control<#if sportKindError??> is-invalid</#if>" type="hidden" id="sportKindFeedback" name="sportKindFeedback">
      <#if sportKindError??>
        <div class="invalid-feedback" for="sportKindFeedback">
          ${sportKindError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода названия спортивной дисциплины -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.SPORT_DISCIPLINE_NAME_LENGTH)!"50"}" <#if (sportDiscipline.name)??>value="${sportDiscipline.name}"</#if> class="form-control <#if nameError??> is-invalid</#if>" id="sportDisciplineName" name="name" placeholder="Название спортивной дисциплины" required>
      <#if nameError??>
        <div class="invalid-feedback" for"sportDisciplineName">
          ${nameError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода кода спортивной дисциплины -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.CODE_LENGTH)!"11"}" <#if (sportDiscipline.code)??>value="${sportDiscipline.code}"</#if> class="form-control <#if codeError??> is-invalid</#if>" id="sportDisciplineCode" name="code" aria-describedby="codeHelp" placeholder="Код дисциплины" required>
      <small id="codeHelp" class="form-text text-muted">Код вида спорта из реестра, например: 0120002611Я</small>
      <#if codeError??>
        <div class="invalid-feedback" for"sportDisciplineCode">
          ${codeError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <#assign buttonTitle="Добавить">
    <#if (sportDiscipline.id)??>
      <#assign buttonTitle="Изменить">
    </#if>
    <@button.submit buttonTitle>
      <#if sportDisciplinesEmpty??>
        <input class="form-control is-invalid" type="hidden" id="sportDisciplinesFeedback" name="sportDisciplinesFeedback">
        <div class="invalid-feedback" for="sportDisciplinesFeedback">
          ${sportDisciplinesEmpty}
        </div>
      </#if>
      <#if objectExist??>
        <input class="form-control is-invalid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="invalid-feedback" for="submitFeedback">
          ${objectExist}
        </div>
      <#elseif saveSuccessful??>
        <input class="form-control is-valid" type="hidden" id="submitFeedback" name="submitFeedback">
        <div class="valid-feedback" for="submitFeedback">
          ${saveSuccessful}
        </div>
      </#if>
    </@button.submit>
  </form>
</@content.zone>
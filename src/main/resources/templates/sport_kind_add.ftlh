<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<#assign title="Добавить вид спорта">
<#if (sportKind.id)??>
  <#assign title="Изменить вид спорта">
</#if>
<@content.zone pageTitleText=title pageHeaderText=title>
  <form method="post">
    <!-- Поле ввода названия вида спорта -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.SPORT_KIND_NAME_LENGTH)!"50"}" <#if (sportKind.name)??>value="${sportKind.name}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="sportKindName" name="name" placeholder="Название вида спорта" required>
      <#if nameError??>
        <div class="invalid-feedback" for"sportKindName">
          ${nameError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода кода вида спорта -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.CODE_LENGTH)!"11"}" <#if (sportKind.code)??>value="${sportKind.code}"</#if> class="form-control<#if codeError??> is-invalid</#if>" id="sportKindCode" name="code" aria-describedby="codeHelp" placeholder="Код вида спорта" required>
      <small id="codeHelp" class="form-text text-muted">Код спортивной дисциплины из реестра, например: 0120002611Я</small>
      <#if codeError??>
        <div class="invalid-feedback" for="sportKindCode">
          ${codeError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <#assign buttonTitle="Добавить">
    <#if (sportKind.id)??>
      <#assign buttonTitle="Изменить">
    </#if>
    <@button.submit buttonTitle>
      <#if sportKindsEmpty??>
        <input class="form-control is-invalid" type="hidden" id="sportKindsFeedback" name="sportKindsFeedback">
        <div class="invalid-feedback" for="sportKindsFeedback">
          ${sportKindsEmpty}
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
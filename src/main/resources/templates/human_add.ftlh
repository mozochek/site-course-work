<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<#assign title="Добавить человека в базу данных">
<#if (human.id)??>
  <#assign title="Изменить данные о человеке">
</#if>
<@content.zone pageTitleText=title pageHeaderText=title>
  <form method="post">
    <!-- Поле ввода имени -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.HUMAN_NAME_LENGTH)!"30"}" <#if (human.name)??>value="${human.name}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="humanName" name="name" placeholder="Имя" required>
      <#if nameError??>
        <div class="invalid-feedback" for"humanName">
          ${nameError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода фамилии -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.HUMAN_SURNAME_LENGTH)!"30"}" <#if (human.surname)??>value="${human.surname}"</#if> class="form-control<#if surnameError??> is-invalid</#if>" id="humanSurname" name="surname" placeholder="Фамилия" required>
      <#if surnameError??>
        <div class="invalid-feedback" for"humanSurname">
          ${surnameError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода отчества -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.HUMAN_PATRONYMIC_LENGTH)!"30"}" <#if (human.patronymic)??>value="${human.patronymic}"</#if> class="form-control<#if patronymicError??> is-invalid</#if>" id="humanPatronymic" name="patronymic" placeholder="Отчество(опционально)">
      <#if patronymicError??>
        <div class="invalid-feedback" for"humanPatronymic">
          ${patronymicError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода дня рождения -->
    <@elem.rowAlignment 6>
      <input type="input" maxlength="${(fieldLength.DATE_LENGTH)!"10"}" <#if (human.birthDate)??>value="${human.printBirthDate()}"</#if> class="form-control<#if birthDateError??> is-invalid</#if>" id="humanBirthDate" name="birthDate" placeholder="Дата рождения(дд.мм.гггг)" required>
      <#if birthDateError??>
        <div class="invalid-feedback" for"humanBirthDate">
          ${birthDateError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <#if human??>
      <@elem.genderSelector width=6 gender=(human.gender)/>
    <#else>
      <@elem.genderSelector width=6 />
    </#if>
    <!-- Поле ввода города -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.CITY_LENGTH)!"35"}" <#if (human.city)??>value="${human.city}"</#if> class="form-control<#if cityError??> is-invalid</#if>" id="humanCity" name="city" placeholder="Город(опционально)">
      <#if cityError??>
        <div class="invalid-feedback" for"humanCity">
          ${cityError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <#assign buttonTitle="Добавить">
    <#if (human.id)??>
      <#assign buttonTitle="Изменить">
    </#if>
    <@button.submit buttonTitle>
      <#if peopleEmpty??>
        <input class="form-control is-invalid" type="hidden" id="peopleFeedback" name="peopleFeedback">
        <div class="invalid-feedback" for="peopleFeedback">
          ${peopleEmpty}
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
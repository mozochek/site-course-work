<#macro rowAlignment width=8>
  <div class="row justify-content-center">
    <div class="col-${width}">
      <div class="form-group">
        <#nested>
      </div>
    </div>
  </div>
</#macro>

<#macro genderSelector labelText="Пол" width=8 gender="MALE">
  <!-- Выбор пола -->
  <@rowAlignment width>
    <label for="genderSelector">${labelText}</label>
    <select class="custom-select" id="genderSelector" name="genderId" required>
      <#--
      <option value="мужской">Мужской</option>
      <option value="женский">Женский</option>
      -->
      <option <#if gender == "MALE">selected</#if> value="0">Мужской</option>
      <option <#if gender == "FEMALE">selected</#if> value="1">Женский</option>
    </select>
    <#if genderError??>
      <input class="form-control is-invalid" type="hidden" id="genderFeedback">
      <div class="invalid-feedback" for="genderFeedback">
        ${genderError}
      </div>
    </#if>
  </@rowAlignment>
</#macro>
<#macro submit text="Стандартный текст">
  <!-- Кнопка подтверждения -->
  <div class="row justify-content-center">
    <div class="col-3">
      <div class="form-group">
        <button type="submit" class="btn btn-block btn-outline-success">${text}</button>
      </div>
      <#nested>
    </div>
  </div>
</#macro>
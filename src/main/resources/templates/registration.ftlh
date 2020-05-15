<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText="Регистрация" pageHeaderText="Регистрация">
  <#include "parts/security.ftlh">
  <form class="py-4" method="post">
    <!-- Поле ввода логина -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.USERNAME_LENGTH)!"30"}" class="form-control <#if usernameError?? || saveError??> is-invalid</#if>" id="username" name="username" placeholder="Имя пользователя" required>
      <#if usernameError??>
        <div class="invalid-feedback" for"username">
          ${usernameError}
        </div>
      <#elseif saveError??>
        <div class="invalid-feedback" for="username">
          ${saveError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Поле ввода пароля -->
    <@elem.rowAlignment 6>
      <input type="password" maxlength="${(fieldLength.PASSWORD_LENGTH)!"30"}" class="form-control  <#if passwordError??> is-invalid</#if>" id="password" name="password" placeholder="Пароль" required>
      <#if passwordError??>
        <div class="invalid-feedback" for"password">
          ${passwordError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <#if user?? && user.isDeveloper()>
      <@elem.rowAlignment 6>
        <label for="roleSelector">Выберите роль</label>
        <select class="custom-select" id="roleSelector" name="role">
          <option value="USER">Пользователь</option>
          <option value="MODERATOR">Модератор</option>
          <option value="ADMIN">Администратор</option>
          <option value="DEVELOPER">Разработчик</option>
        </select>
      </@elem.rowAlignment>
    </#if>
    <#if user?? && user.isDeveloper()>
      <@button.submit "Добавить" />
    <#else>
      <@button.submit "Зарегестрироваться">
        <#if saveSuccessful??>
          <input class="form-control is-valid" type="hidden" id="registrationFeedback" name="registrationFeedback">
          <div class="valid-feedback" for="registrationFeedback">
            ${saveSuccessful}
          </div>
        </#if>
        <div class="form-group">
          <span>Уже есть аккаунт?</span><br>
          <a href="/login">Авторизация</a>
        </div>
      </@button.submit>
    </#if>
  </form>
</@content.zone>
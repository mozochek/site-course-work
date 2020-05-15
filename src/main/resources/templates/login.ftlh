<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText="Авторизация" pageHeaderText="Авторизация">
  <form class="py-4" method="post">
    <!-- Поле ввода логина -->
    <@elem.rowAlignment 6>
      <input type="text" maxlength="${(fieldLength.USERNAME_LENGTH)!"30"}" class="form-control" id="username" name="username" placeholder="Имя пользователя" required>
    </@elem.rowAlignment>
    <!-- Поле ввода пароля -->
    <@elem.rowAlignment 6>
      <input type="password" maxlength="${(fieldLength.PASSWORD_LENGTH)!"30"}" class="form-control" id="password" name="password" placeholder="Пароль" required>
    </@elem.rowAlignment>
    <@button.submit "Войти">
      <div class="form-group text-center">
        <span>Еще нет аккаунта?</span><br>
        <a href="/registration">Регистрация</a>
      </div>
    </@button.submit>
  </form>
</@content.zone>
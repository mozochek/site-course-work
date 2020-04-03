<#import "parts/html_default.ftlh" as html>

<@html.page "Авторизация">
<div class="d-flex justify-content-center">
    <div class="col-6">
        <form action="/login" method="post">
            <div class="form-group">
                <div class="text-center my-4">
                    <h1>Авторизация</h1>
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-form-label">Имя пользователя</label>
                <input type="text" maxlength="${username!"30"}" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password" class="col-form-label">Пароль</label>
                <input type="password" maxlength="${passwordLength!"30"}" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group form-inline float-right mt-4">
                <a class="mr-md-4" href="/registration">Регистрация</a>
                <button type="submit" class="btn btn-lg btn-outline-success">Войти</button>
                <#if loginError??>
                    <input class="form-control is-invalid" type="hidden" id="loginFeedback" name="loginFeedback">
                    <div class="invalid-feedback" for="loginFeedback">
                        ${loginError}
                    </div>
                </#if>
            </div>
        </form>
    </div>
</div>
</@html.page>
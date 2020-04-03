<#import "parts/html_default.ftlh" as html>

<@html.page "Регистрация">
<div class="d-flex justify-content-center">
    <div class="col-6">
        <form action="/registration" method="post">
            <div class="form-group">
                <div class="text-center my-4">
                    <h1>Регистрация</h1>
                </div>
            </div>
            <div class="form-group">
                <label for="username" class="col-form-label">Имя пользователя</label>
                <input type="text" maxlength="${username!"30"}" class="form-control <#if usernameError?? || saveError??> is-invalid</#if>" id="username" name="username" required>
                <#if usernameError??>
                    <div class="invalid-feedback" for"username">
                        ${usernameError}
                    </div>
                <#elseif saveError??>
                    <div class="invalid-feedback" for="username">
                        ${saveError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="password" class="col-form-label">Пароль</label>
                <input type="password" maxlength="${passwordLength!"30"}" class="form-control  <#if passwordError??> is-invalid</#if>" id="password" name="password" required>
                <#if passwordError??>
                    <div class="invalid-feedback" for"password">
                        ${passwordError}
                    </div>
                </#if>
            </div>
            <div class="form-group form-inline float-right">
                <div class="mx-1">Уже есть аккаунт?<a href="/login"> Авторизуйтесь</a></div>
                <div class="ml-5">
                    <button type="submit" class="btn btn-lg btn-outline-success">Зарегистрироваться</button>
                </div>
            </div>
        </form>
    </div>
</div>
</@html.page>
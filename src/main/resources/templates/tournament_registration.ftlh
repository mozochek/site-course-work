<#import "parts/html_default.ftlh" as html>

<@html.page "Регистрация нового турнира">
    <div class="text-center mt-lg-2">
        <h2>Регистрация нового турнира</h2>
    </div>
    <form action="/admin/tournament/registration" method="post">
        <div class="col-lg-5">
            <div class="form-group mt-lg-3">
                <label for="sportKindSelector">Выберите вид спорта</label>
                <select class="custom-select" id="sportKindSelector" name="sportKind" required>
                    <#list sportKinds as sportKind>
                        <option value="${sportKind.id}"<#if prevSelectedSportKind??><#if sportKind.id == prevSelectedSportKind> selected</#if></#if>>${sportKind.code} | ${sportKind.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Продолжить</button>
                <#if sportDisciplinesError??>
                    <input class="form-control is-invalid" type="hidden" id="submitFeedback" name="submitFeedback">
                    <div class="invalid-feedback" for="submitFeedback">
                        ${sportDisciplinesError}
                         <a href="/admin/sport_discipline/add">Добавить</a>
                    </div>
                </#if>
            </div>
        </div>
    </form>
</@html.page>
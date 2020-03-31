<#import "parts/html_default.ftlh" as html>

<@html.page "Добавить спортивную дисциплину">
    <div class="text-center mt-lg-2">
        <h2>Добавить спортивную дисциплину</h2>
    </div>

    <form action="/sport_discipline/add" method="post">
        <div class="col-lg-5">
            <div class="form-group">
                <label for="sportKindSelector">Выберите вид спорта</label>
                <select class="custom-select" id="sportKindSelector" name="sportKind" required>
                    <#list sportKinds as sportKind>
                        <option value="${sportKind.id}"<#if prevSelectedSportKind??><#if sportKind.id == prevSelectedSportKind> selected</#if></#if>>${sportKind.code} | ${sportKind.name}</option>
                    </#list>
                </select>
                <input class="form-control<#if sportKindError??> is-invalid</#if>" type="hidden" id="sportKindFeedback" name="sportKindFeedback">
                <#if sportKindError??>
                    <div class="invalid-feedback" for="sportKindFeedback">
                        ${sportKindError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="sportDisciplineName">Название спортивной дисциплины*</label>
                <input type="text" maxlength="${sportKindNameLength!"50"}" <#if prevName??>value="${prevName}"</#if> class="form-control <#if nameError??> is-invalid</#if>" id="sportDisciplineName" name="sportDisciplineName" required>
                <#if nameError??>
                    <div class="invalid-feedback" for"sportDisciplineName">
                        ${nameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="sportDisciplineCode">Код дисциплины*</label>
                <input type="text" maxlength="${codeLength!"11"}" <#if prevCode??>value="${prevCode}"</#if> class="form-control <#if codeError??> is-invalid</#if>" id="sportDisciplineCode" name="sportDisciplineCode" aria-describedby="codeHelp" required>
                <small id="codeHelp" class="form-text text-muted">Код вида спорта из реестра, например: 0120002611Я</small>
                <#if codeError??>
                    <div class="invalid-feedback" for"sportDisciplineCode">
                        ${codeError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Добавить</button>
                <input class="form-control<#if objectExist??> is-invalid <#elseif dataIsValid??> is-valid</#if>" type="hidden" id="submitFeedback" name="submitFeedback">
                <#if objectExist??>
                    <div class="invalid-feedback" fro="submitFeedback">
                        ${objectExist}
                    </div>
                <#elseif dataIsValid??>
                    <div class="valid-feedback" fro="submitFeedback">
                        ${dataIsValid}
                    </div>
                </#if>
            </div>
        </div>
    </form>
</@html.page>
<#import "parts/html_default.ftlh" as html>

<@html.page "Добавить вид спорта">
    <div class="text-center mt-lg-2">
        <h2>Добавить вид спорта</h2>
    </div>
    <form action="/admin/sport_kind/add" method="post">
        <div class="col-lg-5">
            <div class="form-group">
                <label for="sportKindName">Название вида спорта*</label>
                <input type="text" maxlength="${sportKindNameLength!"50"}" <#if prevName??>value="${prevName}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="sportKindName" name="sportKindName" required>
                <#if nameError??>
                    <div class="invalid-feedback" for"sportKindName">
                        ${nameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="sportKindCode">Код вида спорта*</label>
                <input type="text" maxlength="${codeLength!"11"}" <#if prevCode??>value="${prevCode}"</#if> class="form-control<#if codeError??> is-invalid</#if>" id="sportKindCode" name="sportKindCode" aria-describedby="codeHelp" required>
                <small id="codeHelp" class="form-text text-muted">Код спортивной дисциплины из реестра, например: 0120002611Я</small>
                <#if codeError??>
                    <div class="invalid-feedback" for="sportKindCode">
                        ${codeError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Добавить</button>
                <input class="form-control<#if objectExist??> is-invalid <#elseif saveSuccessful??> is-valid</#if>" type="hidden" id="submitFeedback" name="submitFeedback">
                <#if objectExist??>
                    <div class="invalid-feedback" for="submitFeedback">
                        ${objectExist}
                    </div>
                <#elseif saveSuccessful??>
                    <div class="valid-feedback" for="submitFeedback">
                        ${saveSuccessful}
                    </div>
                </#if>
            </div>
        </div>
    </form>
</@html.page>
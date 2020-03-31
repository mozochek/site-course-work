<#import "parts/html_default.ftlh" as html>

<@html.page "Добавить человека">
    <div class="text-center mt-lg-2">
        <h2>Добавить человека</h2>
    </div>
    <form action="/human/add" method="post">
        <div class="col-lg-5">
            <div class="form-group">
                <label for="humanName">Имя*</label>
                <input type="text" maxlength="${humanNameLength!"30"}" <#if prevName??>value="${prevName}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="humanName" name="humanName" required>
                <#if nameError??>
                    <div class="invalid-feedback" for"humanName">
                        ${nameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="humanSurname">Фамилия*</label>
                <input type="text" maxlength="${humanSurnameLength!"30"}" <#if prevSurname??>value="${prevSurname}"</#if> class="form-control<#if surnameError??> is-invalid</#if>" id="humanSurname" name="humanSurname" required>
                <#if surnameError??>
                    <div class="invalid-feedback" for"humanSurname">
                        ${surnameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="humanPatronymic">Отчество</label>
                <input type="text" maxlength="${humanPatronymicLength!"30"}" <#if prevPatronymic??>value="${prevPatronymic}"</#if> class="form-control<#if patronymicError??> is-invalid</#if>" id="humanPatronymic" name="humanPatronymic">
                <#if patronymicError??>
                    <div class="invalid-feedback" for"humanPatronymic">
                        ${patronymicError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label for="humanBirthDate">Дата рождения*</label>
                <input type="input" maxlength="${dateLength!"10"}" <#if prevBirthDate??>value="${prevBirthDate}"</#if> class="form-control<#if birthDateError??> is-invalid</#if>" id="humanBirthDate" name="humanBirthDate" required>
                <#if birthDateError??>
                    <div class="invalid-feedback" for"humanBirthDate">
                        ${birthDateError}
                    </div>
                <#else>
                    <small id="codeHelp" class="form-text text-muted">Дата рождения в формате: дд.мм.гггг</small>
                </#if>
            </div>
            <div class="form-group">
                <label for="humanCity">Город</label>
                <input type="text" maxlength="${cityLength!"35"}" <#if prevCity??>value="${prevCity}"</#if> class="form-control<#if cityError??> is-invalid</#if>" id="humanCity" name="humanCity">
                <#if cityError??>
                    <div class="invalid-feedback" for"humanCity">
                        ${cityError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Добавить</button>
                <input class="form-control<#if objectExist??> is-invalid <#elseif dataIsValid??> is-valid</#if>" type="hidden" id="submitFeedback" name="submitFeedback">
                <#if objectExist??>
                    <div class="invalid-feedback" for="submitFeedback">
                        ${objectExist}
                    </div>
                <#elseif dataIsValid??>
                    <div class="valid-feedback" for="submitFeedback">
                        ${dataIsValid}
                    </div>
                </#if>
            </div>
        </div>
    </form>
</@html.page>
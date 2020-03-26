<#import "parts/html_default.ftlh" as html>

<@html.page "Добавить вид спорта">
    <div class="text-center mt-lg-2">
        <h2>Добавить вид спорта</h2>
    </div>
    <form action="/sport_kind/add" method="post">
        <div class="col-lg-5">
            <div class="form-group mt-lg-3">
                <label for="inputTournamentName">Название вида спорта</label>
                <input type="text" class="form-control<#if textError??> is-invalid</#if>" id="inputSportKindName" name="inputSportKindName">
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <button type="submit" class="btn btn-outline-success">Добавить</button>
            <#if textAccept??>
                <div class="">
                    ${textAccept}
                </div>
            </#if>
        </div>
    </form>
</@html.page>
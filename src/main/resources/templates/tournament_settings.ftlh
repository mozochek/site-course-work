<#import "parts/html_default.ftlh" as html>

<@html.page "Настройки турнира">
    <div class="text-center mt-lg-2">
        <h2><label name="tournamentName">${(tournament.name)!"Турнир"}</label></h2>
    </div>
    <form action="/tournament/settings" method="post">
    <div class="container">
        <div class="col-lg-8">
            <div class="form-group>
                <label for="tournamentName">Название турнира</label>
                <input type="text" maxlength="${tournamentNameLength!"30"}" <#if prevName??>value="${prevName}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="tournamentName" name="tournamentName" required>
                <#if nameError??>
                    <div class="invalid-feedback" for="tournamentName">
                        ${nameError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="sportDisciplineSelector">Выберите спортивную дисциплину</label>
                <select class="custom-select" id="sportDisciplineSelector" name="tournamentSportDiscipline" required>
                    <#if sportDisciplines??>
                    <#list sportDisciplines as sportDiscipline>
                        <option value="${sportDiscipline.id}">${sportDiscipline.code} | ${sportDiscipline.name}</option>
                    </#list>
                    </#if>
                </select>
            </div>

            <div class="form-row form-group">
                <div class="col">
                    <label for="tournamentCity">Место проведения</label>
                    <input type="text" maxlength="${cityLength!"35"}" <#if prevCity??>value="${prevCity}"</#if> class="form-control<#if cityError??> is-invalid</#if>" id="tournamentCity" name="tournamentCity" required>
                    <#if cityError??>
                        <div class="invalid-feedback" for="tournamentCity">
                            ${cityError}
                        </div>
                    </#if>
                </div>

                <div class="col">
                    <label for="tournamentDateFrom">Дата начала турнира</label>
                    <input type="text" maxlength="${tournamentDateLength!"10"}" <#if prevDateFrom??>value="${prevDateFrom}"</#if> class="form-control<#if dateFromError??> is-invalid</#if>" id="tournamentDateFrom" name="tournamentDateFrom" required>
                    <#if dateFromError??>
                        <div class="invalid-feedback" for="tournamentDateFrom">
                            ${dateFromError}
                        </div>
                    </#if>
                </div>

                <div class="col">
                    <label for="tournamentDateTill">Дата окончания турнира</label>
                    <input type="text" maxlength="${tournamentDateLength!"10"}" <#if prevDateTill??>value="${prevDateTill}"</#if> class="form-control<#if dateTillError??> is-invalid</#if>" id="tournamentDateTill" name="tournamentDateTill" required>
                    <#if dateTillError??>
                        <div class="invalid-feedback" for="tournamentDateTill">
                            ${dateTillError}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group">
                <label for="tournamentFormat">Формат турнира</label>
                <input type="text" maxlength="${tournamentFormatLength!"20"}" <#if prevFormat??>value="${prevFormat}"</#if> class="form-control<#if formatError??> is-invalid</#if>" id="tournamentFormat" name="tournamentFormat" required>
                <#if formatError??>
                    <div class="invalid-feedback" for="tournamentFormat">
                        ${formatError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="tournamentGender">Пол игроков</label>
                <input type="text" maxlength="${tournamentGenderLength!"15"}" <#if prevGender??>value="${prevGender}"</#if> class="form-control<#if genderError??> is-invalid</#if>" id="tournamentGender" name="tournamentGender" required>
                <#if genderError??>
                    <div class="invalid-feedback" for="tournamentGender">
                        ${genderError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="tournamentAgeGroup">Возрастная группа</label>
                <input type="text" maxlength="${tournamentAgeGroupLength!"20"}" <#if prevAgeGroup??>value="${prevAgeGroup}"</#if> class="form-control<#if ageGroupError??> is-invalid</#if>" id="tournamentAgeGroup" name="tournamentAgeGroup" required>
                <#if ageGroupError??>
                    <div class="invalid-feedback" for="tournamentAgeGroup">
                        ${ageGroupError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="tournamentCategory">Категория</label>
                <input type="text" maxlength="${tournamentCategoryLength!"10"}" <#if prevCategory??>value="${prevCategory}"</#if> class="form-control<#if categoryError??> is-invalid</#if>" id="tournamentCategory" name="tournamentCategory" required>
                <#if categoryError??>
                    <div class="invalid-feedback" for="tournamentCategory">
                        ${categoryError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <label for="tournamentClass">Класс</label>
                <input type="text" maxlength="${tournamentClassLength!"10"}" <#if prevTournamentClass??>value="${prevTournamentClass}"</#if> class="form-control<#if tournamentClassError??> is-invalid</#if>" id="tournamentClass" name="tournamentClass" required>
                <#if tournamentClassError??>
                    <div class="invalid-feedback" for="tournamentClass">
                        ${tournamentClassError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-outline-success">Продолжить</button>
            </div>
        </div>
    </div>
    </form>
</@html.page>
<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>
<#import "parts/page_elems.ftlh" as elem>

<@content.zone pageTitleText=(tournament.name)!"Регистрация нового турнира" pageHeaderText=(tournament.name)!"Регистрация нового турнира">
  <form method="post">
    <!-- Поле ввода названия турнира -->
    <@elem.rowAlignment 8>
      <input type="text" maxlength="${(fieldLength.TOURNAMENT_NAME_LENGTH)!"30"}" <#if (tournament.name)??>value="${tournament.name}"</#if> class="form-control<#if nameError??> is-invalid</#if>" id="tournamentName" name="name" placeholder="Название турнира" required>
      <#if nameError??>
        <div class="invalid-feedback" for="tournamentName">
          ${nameError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <!-- Выбор спортивной дисциплины -->
    <@elem.rowAlignment 8>
      <label for="sportDisciplineSelector">Выберите спортивную дисциплину</label>
      <select class="custom-select" id="sportDisciplineSelector" name="sportDiscipline" required>
        <#-- Список всех доступных спортивных дисциплин -->
        <#list sportDisciplines?sort_by("code") as sportDiscipline>
          <option value="${sportDiscipline.id}">${(sportDiscipline.code)!"<код?>"} | ${(sportDiscipline.name)!"<название?>"}</option>
        <#else>
          <option value="-1"><нет спортивных дисциплин?></option>
        </#list>
      </select>
      <#if sportDisciplineError??>
        <input class="form-control is-invalid" type="hidden" id="sportDisciplineFeedback">
        <div class="invalid-feedback" for="sportDisciplineFeedback">
          ${sportDisciplineError}
        </div>
      </#if>
    </@elem.rowAlignment>
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-row form-group">
          <!-- Поле ввода города -->
          <div class="col">
            <input type="text" maxlength="${(fieldLength.CITY_LENGTH)!"35"}" <#if (tournament.city)??>value="${tournament.city}"</#if> class="form-control<#if cityError??> is-invalid</#if>" id="tournamentCity" name="city" placeholder="Место проведения" required>
            <#if cityError??>
              <div class="invalid-feedback" for="tournamentCity">
                ${cityError}
              </div>
            </#if>
          </div>
          <!-- Поле ввода даты начала турнира -->
          <div class="col">
            <input type="text" maxlength="${(fieldLength.DATE_LENGTH)!"10"}" <#if (tournament.dateFrom)??>value="${tournament.printDateFrom()}"</#if><#--<#if prevDateFrom??>value="${prevDateFrom}"</#if>--> class="form-control<#if dateFromError??> is-invalid</#if>" id="tournamentDateFrom" name="dateFrom" placeholder="Дата начала турнира" required>
            <#if dateFromError??>
              <div class="invalid-feedback" for="tournamentDateFrom">
                ${dateFromError}
              </div>
            </#if>
          </div>
          <!-- Поле ввода даты окончания турнира -->
          <div class="col">
            <input type="text" maxlength="${(fieldLength.DATE_LENGTH)!"10"}" <#if (tournament.dateTill)??>value="${tournament.printDateTill()}"</#if> class="form-control<#if dateTillError??> is-invalid</#if>" id="tournamentDateTill" name="dateTill" placeholder="Дата окончания турнира" required>
            <#if dateTillError??>
              <div class="invalid-feedback" for="tournamentDateTill">
                ${dateTillError}
              </div>
            </#if>
          </div>
        </div>
      </div>
    </div>
    <#if tournament??>
      <@elem.genderSelector labelText="Пол участников" gender=(tournament.gender)/>
    <#else>
      <@elem.genderSelector labelText="Пол участников" />
    </#if>
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-row form-group">
          <!-- Поле ввода возростной группы участников турнира -->
          <div class="col">
            <input type="text" maxlength="${(fieldLength.TOURNAMENT_AGE_GROUP_LENGTH)!"20"}" <#if (tournament.ageGroup)??>value="${tournament.ageGroup}"</#if> class="form-control<#if ageGroupError??> is-invalid</#if>" id="tournamentAgeGroup" name="ageGroup" placeholder="Возрастная группа" required>
            <#if ageGroupError??>
              <div class="invalid-feedback" for="tournamentAgeGroup">
                ${ageGroupError}
              </div>
            </#if>
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-row form-group">
          <!-- Поле ввода кол-ва команд-участниц турнира -->
          <div class="col">
            <input type="number" min="1" max="${(fieldLength.TOURNAMENT_TEAM_AMOUNT_LENGTH)!"128"}" <#if (tournament.teamAmount)??>value="${tournament.teamAmount}"</#if> class="form-control<#if teamAmountError??> is-invalid</#if>" id="tournamentTeamAmount" name="teamAmount" placeholder="Кол-во команд-участниц" required>
          </div>
          <!-- Поле ввода кол-ва игроков команд -->
          <div class="col">
            <input type="number" min="1" max="${(fieldLength.TOURNAMENT_TEAM_CAPACITY_LENGTH)!"64"}" <#if (tournament.teamCapacity)??>value="${tournament.teamCapacity}"</#if> class="form-control<#if teamCapacityError??> is-invalid</#if>" id="tournamentTeamCapacity" name="teamCapacity" placeholder="Кол-во игроков в команде" required>
          </div>
        </div>
      </div>
    </div>
    <!-- Поле ввода формата турнира -->
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-group">
          <input type="text" maxlength="${(fieldLength.TOURNAMENT_FORMAT_LENGTH)!"20"}" <#if (tournament.format)??>value="${tournament.format}"</#if> class="form-control<#if formatError??> is-invalid</#if>" id="tournamentFormat" name="format" placeholder="Формат турнира" required>
          <#if formatError??>
            <div class="invalid-feedback" for="tournamentFormat">
              ${formatError}
            </div>
          </#if>
        </div>
      </div>
    </div>
    <!-- Поле ввода категории турнира -->
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-group">
          <input type="text" maxlength="${(fieldLength.TOURNAMENT_CATEGORY_LENGTH)!"10"}" <#if (tournament.category)??>value="${tournament.category}"</#if> class="form-control<#if categoryError??> is-invalid</#if>" id="tournamentCategory" name="category" placeholder="Категория" required>
          <#if categoryError??>
            <div class="invalid-feedback" for="tournamentCategory">
              ${categoryError}
            </div>
          </#if>
        </div>
      </div>
    </div>
    <!-- Поле ввода класса турнира -->
    <div class="row justify-content-center">
      <div class="col-8">
        <div class="form-group">
          <input type="text" maxlength="${(fieldLength.TOURNAMENT_CLASS_LENGTH)!"10"}" <#if (tournament.tournamentClass)??>value="${tournament.tournamentClass}"</#if> class="form-control<#if tournamentClassError??> is-invalid</#if>" id="tournamentClass" name="tournamentClass" placeholder="Класс" required>
          <#if tournamentClassError??>
            <div class="invalid-feedback" for="tournamentClass">
              ${tournamentClassError}
            </div>
          </#if>
        </div>
      </div>
    </div>
    <#if (tournament.teams)??>
      <div class="row justify-content-center">
        <div class="col-6">
          <div class="form-group">
            <label for="sportKindSelector">Выберите команду-победительницу</label>
            <select class="custom-select" id="winnerTeamSelector" name="winnerTeam" required>
              <option value="-1"><не выбрано></option>
              <#-- Список всех команд -->
              <#list tournament.teams?sort_by("name") as team>
                <option <#if tournament.winnerTeam?? && (tournament.winnerTeam).id == team.id>selected</#if> value="${(team.id)}">${(team.name)!"<название?>"}</option>
              </#list>
            </select>
          </div>
        </div>
      </div>
      <#if teamError??>
        <input class="form-control is-invalid" type="hidden" id="winnerTeamFeedback">
        <div class="invalid-feedback" for="winnerTeamFeedback">
          ${teamError}
        </div>
      </#if>
    </#if>
    <#if edit??><@button.submit "Изменить"/><#else><@button.submit "Зарегестрировать"/></#if>
  </form>
</@content.zone>
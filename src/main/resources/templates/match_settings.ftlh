<#import "parts/content.ftlh" as content>
<#import "parts/buttons.ftlh" as button>

<@content.zone pageTitleText="Статистика матча №${match.id}" pageHeaderText="Статистика матча №${match.id}">
  <form method="post">
    <!-- Заголовок неявной таблицы -->
    <div class="form-group">
      <div class="row">
        <span class="col-1 text-center">№ игры</span>
        <span class="col text-center">${(match.getFirstTeam()).name}</span>
        <span class="col text-center">Победитель</span>
        <span class="col text-center">${(match.getSecondTeam()).name}</span>
      </div>
    </div>
    <!-- Список игр матча -->
    <#list (match.games)?sort_by("id") as game>
      <!-- Игра ${game?counter} -->
      <div class="form-group">
        <div class="row">
          <#-- Столбец с номером игры -->
          <div class="col-1">
            <span>Игра ${game?counter}</span>
          </div>
          <#-- Поле ввода счета первой команды -->
          <div class="col">
            <input type="number" min="0" max="32000" value="${(game.firstTeamScore)!"0"}" class="form-control" id="firstTeamScoreGame${(game.id)!"-1"}" name="firstTeamScoreGame${(game.id)!"-1"}">
          </div>
          <#-- Выбор победителя игры -->
          <div class="col">
            <select class="custom-select" id="game${(game.id)!"-1"}Winner" name="game${(game.id)!"-1"}Winner" required>
              <option value="-1"><не выбрано></option>
              <#-- Список команд-участниц матча -->
              <#list (match.teams)?sort_by("id") as team>
                <option <#if (game.winnerTeam)?? && (game.winnerTeam).id == (team.id)>selected</#if> value="${(team.id)!"-1"}">${(team.name)!"<название?>"}</option>
              </#list>
            </select>
          </div>
          <#-- Поле ввода счета второй команды -->
          <div class="col">
            <input type="number" min="0" max="32000" value="${(game.secondTeamScore)!"0"}" class="form-control" id="secondTeamScoreGame${(game.id)!"-1"}" name="secondTeamScoreGame${(game.id)!"-1"}">
          </div>
        </div>
      </div>
    <#else>
      <span><отсутствуют игры матча?></span>
    </#list>
    <hr>
    <!-- Выбор победителя матча -->
    <div class="row justify-content-center">
      <div class="col-6">
        <label for="matchWinnerSelector">Победитель матча</label>
        <div class="form-group">
          <select class="custom-select" id="matchWinnerSelector" name="matchWinner" required>
            <option value="-1"><не выбрано></option>
            <#-- Список команд-участниц матча -->
            <#list (match.teams)?sort_by("id") as team>
              <option <#if (match.winnerTeam)?? && (match.winnerTeam).id == (team.id)>selected</#if> value="${(team.id)!"-1"}">${(team.name)!"<название?>"}</option>
            <#else>
              <option><отсутствуют команды-участницы матча?></option>
            </#list>
          </select>
        </div>
      </div>
    </div>
    <@button.submit "Сохранить изменения" />
  </form>
</@content.zone>
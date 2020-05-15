<#import "parts/html_page.ftlh" as html>

<@html.page (tournament.name)!"<название турнира?>">
  <div class="row mt-4">
    <#include "parts/security.ftlh">
    <div class="col-3">
      <br><br><br>
      <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <a class="nav-link active" id="v-pills-info-tab" data-toggle="pill" href="#v-pills-info" role="tab" aria-controls="v-pills-info" aria-selected="true">Основная информация</a>
        <a class="nav-link" id="v-pills-judges-tab" data-toggle="pill" href="#v-pills-judges" role="tab" aria-controls="v-pills-judges" aria-selected="true">Судьи турнира</a>
        <a class="nav-link" id="v-pills-matches-tab" data-toggle="pill" href="#v-pills-matches" role="tab" aria-controls="v-pills-matches" aria-selected="false">Матчи турнира</a>
        <a class="nav-link" id="v-pills-teams-tab" data-toggle="pill" href="#v-pills-teams" role="tab" aria-controls="v-pills-teams" aria-selected="false">Команды</a>
        <#if user.isAdmin()>
          <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab" aria-controls="v-pills-settings" aria-selected="false">Настройки</a>
        </#if>
      </div>
    </div>
    <div class="col-9">
      <div class="text-center">
        <h2>${(tournament.name)!"<название турнира?>"}</h2>
      </div><br>
      <div class="tab-content" id="v-pills-tabContent">
        <!-- Информация о турнире -->
        <div class="tab-pane fade show active" id="v-pills-info" role="tabpanel" aria-labelledby="v-pills-home-tab">
          <p><span>Дисциплина: ${((tournament.sportDiscipline).getDescription())!"<описание?>"}</span></p>
          <p><span>Место проведения: ${(tournament.city)!"<город?>"}</span></p>
          <p><span>Формат: ${(tournament.format)!"<формат?>"}</span></p>
          <p><span>Дата проведения: ${(tournament.dateFrom)!"<дата с?>"}-${(tournament.dateTill)!"<дата до?>"}</span></p>
          <p><span>Ворастная группа: ${(tournament.ageGroup)!"<возрастная группа?>"}</span></p>
          <p><span>Пол игроков: ${(tournament.gender.getName())!"<пол игроков?>"}</span></p>
          <p><span>Категория: ${(tournament.category)!"<категория?>"}</span></p>
          <p><span>Класс: ${(tournament.tournamentClass)!"<класс?>"}</span></p>
          <p><span>Победитель: ${((tournament.winnerTeam).name)!"Победителя еще нет"}</span></p>
        </div>
        <!-- Список судей турнира -->
        <div class="tab-pane fade" id="v-pills-judges" role="tabpanel" aria-labelledby="v-pills-judges-tab">
          <ul>
            <#list (tournament.judges)?sort_by("surname") as judge>
              <li>${(judge.surname)!"<фамилия?>"} ${(judge.name)!"<имя?>"}<#if (judge.patronymic)??> ${judge.patronymic}</#if><#if (judge.city)??>, ${judge.city}</#if></li>
            <#else>
              <li>
                <судьи ещё не назначены>
                <#if user.isAdmin()>
                  <a href="/api/tournament/${(tournament.id)!"-1"}/judge/add"> Назначить судей</a>
                </#if>
              </li>
            </#list>
          </ul>
        </div>
        <!-- Список матчей турнира -->
        <div class="tab-pane fade" id="v-pills-matches" role="tabpanel" aria-labelledby="v-pills-matches-tab">
          <div class="accordion" id="accordion">
            <#-- Список матчей -->
            <#list (tournament.matches)?sort_by("id")?reverse as match>
              <!-- Матч ${match?counter} -->
              <div class="card">
                <div class="card-header" id="heading${match?counter}">
                  <h5 class="mb-0">
                    <button class="btn btn-link btn-block" type="button" data-toggle="collapse" data-target="#collapse${match?counter}" aria-expanded="true" aria-controls="collapse${match?counter}">
                      <div class="row" id="${match.id}">
                        <#assign isFirstTeamWinner = (match.winnerTeam)?? && (match.winnerTeam).id == (match.getFirstTeam()).id />
                        <#assign isSecondTeamWinner = (match.winnerTeam)?? && (match.winnerTeam).id == (match.getSecondTeam()).id />
                        <div class="col text-left">
                          <span <#if isFirstTeamWinner>class="text-success"<#elseif isSecondTeamWinner>class="text-danger"</#if>>${(match.getFirstTeam()).name} <#if isFirstTeamWinner><span class="font-weight-bold">- Победитель</span><#elseif isSecondTeamWinner><span class="font-weight-bold">- Проигравший</span></#if></span>
                        </div>
                        <div class="col">
                          <span>(<span <#if isFirstTeamWinner>class="text-success"<#elseif isSecondTeamWinner>class="text-danger"</#if>>${(match.firstTeamScore)!"0"}</span>:<span <#if isSecondTeamWinner>class="text-success"<#elseif isFirstTeamWinner>class="text-danger"</#if>>${(match.secondTeamScore)!"0"}</span>)</span>
                        </div>
                        <div class="col text-right">
                          <span <#if isSecondTeamWinner>class="text-success"<#elseif isFirstTeamWinner>class="text-danger"</#if>><#if isSecondTeamWinner><span class="font-weight-bold">Победитель -</span><#elseif isFirstTeamWinner><span class="font-weight-bold">Проигравший -</span></#if> ${(match.getSecondTeam()).name}</span>
                        </div>
                      </div>
                    </button>
                  </h5>
                </div>
                <div id="collapse${match?counter}" class="collapse" aria-labelledby="heading${match?counter}" data-parent="#accordion">
                  <div class="card-body">
                    <#-- Список игр -->
                    <#list (match.games)?sort_by("id") as game>
                      <!-- Матч ${match?counter} - Игра ${game?counter} -->
                      <div class="row" id="${game.id}">
                        <#assign isFirstTeamWinner = (game.winnerTeam)?? && game.winnerTeam == match.getFirstTeam()/>
                        <#assign isSecondTeamWinner = (game.winnerTeam)?? && game.winnerTeam == match.getSecondTeam()/>
                        <div class="col text-left">
                          <span <#if isFirstTeamWinner>class="text-success"<#elseif isSecondTeamWinner>class="text-danger"</#if>>${((match.getFirstTeam()).name)!"<название?>"}</span>
                        </div>
                        <div class="col text-center">
                          <span>(<span<#if isFirstTeamWinner>class="text-success"<#elseif isSecondTeamWinner>class="text-danger"</#if>>${(game.firstTeamScore)!"0"}</span>:<span <#if isSecondTeamWinner>class="text-success"<#elseif isFirstTeamWinner>class="text-danger"</#if>>${(game.secondTeamScore)!"0"}</span>)</span>
                        </div>
                        <div class="col text-right">
                          <span <#if isSecondTeamWinner>class="text-success"<#elseif isFirstTeamWinner>class="text-danger"</#if>>${((match.getSecondTeam()).name)!"<название?>"}</span>
                        </div>
                      </div>
                      <#if user.isAdmin()>
                        <div class="row justify-content-center text-center">
                          <a href="/api/game/${game.id}/delete">Удалить игру</a>
                        </div>
                      </#if>
                      <hr>
                    </#list>
                    <#if user.isAdmin()>
                      <div class="row justify-content-center">
                        <div class="col text-right"><a href="/api/tournament/${(tournament.id)!"-1"}/match/${(match.id)!"-1"}/settings">Внести изменения</a></div>
                        <div class="col text-left"><a href ="/api/tournament/${(tournament.id)!"-1"}/match/${match.id}/delete">Удалить матч</a></div>
                      </div>
                    </#if>
                  </div>
                </div>
              </div>
            <#else>
              <span>Матчей еще нет!</span>
              <#if user.isAdmin()>
                <a href="/api/tournament/${(tournament.id)!"-1"}/match/add"> Добавить матч</a>
              </#if>
            </#list>
          </div>
        </div>
        <div class="tab-pane fade" id="v-pills-teams" role="tabpanel" aria-labelledby="v-pills-teams-tab">
          <#-- Список всех команд-учатниц турнира -->
          <#--<#list (tournament.teams)?sort_by("name") as team>
            <p>${(team.name)!"<название?>"} - <#list (team.humans)?sort_by("surname") as human>${(human.surname)!"<фамилия?>"} ${(human.name)!"<имя?>"}<#sep>, </#sep><#else><игроков нет></#list></p>
          <#else>
            <span>Команд нет!</span>
          </#list>-->
          <ul>
            <#list (tournament.teams)?sort_by("name") as team>
              <li>
                ${(team.name)!"<название?>"}
              </li>
              <ul>
                <#list (team.people)?sort_by("surname") as human>
                  <li>
                    ${(human.surname)!"<фамилия?>"} ${(human.name)!"<имя?>"}<#if (human.patronymic)??> ${(human.patronymic)}</#if><#if (human.city)??>, ${human.city}</#if> (${human.printBirthDate()})
                  </li>
                <#else>
                  <li>
                    Игроки отсутствует
                  </li>
                </#list>
              </ul>
            <#else>
              <li><команд нет?></li>
            </#list>
          </ul>
        </div>
        <#if user.isAdmin()>
          <!-- Настройки -->
          <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
            <a href="/api/tournament/${tournament.id}/edit">Редактировать данные о турнире</a><br>
            <a href="/api/tournament/${tournament.id}/judge/add">Назначить судей</a><br>
            <a href="/api/tournament/${tournament.id}/team/settings">Добавить игроков в команду</a><br>
            <a href="/api/tournament/${tournament.id}/match/add">Добавить матч</a><br>
            <a href="/api/tournament/${tournament.id}/delete">Удалить турнир</a><br>
          </div>
        </#if>
      </div>
    </div>
  </div>
</@html.page>

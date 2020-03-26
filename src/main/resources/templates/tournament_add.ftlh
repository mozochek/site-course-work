<#import "parts/html_default.ftlh" as html>
<!--<#import "parts/elements.ftlh" as elem>-->

<@html.page "Добавить турнир">
    <div class="text-center mt-lg-2">
        <h2>Регистрация нового турнира</h2>
    </div>
    <form action="/tournament/add" method="post">
        <div class="col-lg-5">
            <div class="form-group mt-lg-3">
                <label for="inputTournamentName">Название турнира</label>
                <input type="text" class="form-control" id="tournamentName" name="tournamentName" aria-describedby="nameHelp">
                <!--<small id="nameHelp" class="form-text text-muted">Название турнира</small>-->
            </div>
            <div class="form-group">
                <label for="inputTournamentPlace">Спортивная дисциплина</label>
                <input type="text" class="form-control" id="sportDisciplineName" name="sportDisciplineName" aria-describedby="disciplineNameHelp">
                <!--<small id="disciplineNameHelp" class="form-text text-muted">Город</small>-->
            </div>
            <div class="form-group">
                <label for="inputTournamentPlace">Формат турнира</label>
                <input type="text" class="form-control" id="tournamentFormat" name="tournamentFormat" aria-describedby="formatHelp">
                <!--<small id="formatHelp" class="form-text text-muted">Город</small>-->
            </div>
            <div class="form-group">
                <label for="inputTournamentPlace">Место проведения</label>
                <input type="text" class="form-control" id="tournamentCity" name="tournamentCity" aria-describedby="cityHelp">
                <!--<small id="cityHelp" class="form-text text-muted">Город</small>-->
            </div>

            <button type="submit" class="btn btn-outline-success">Зарегестрировать турнир</button>
        </div>
    </form>
</@html.page>
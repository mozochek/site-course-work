<#import "parts/html_default.ftlh" as html>

<@html.page "Добавить спортивную дисциплину">
    <div class="text-center mt-lg-2">
        <h2>Добавить спортивную дисциплину</h2>
    </div>
    <form action="/sport_discipline/add" method="post">
        <div class="col-lg-5">
            <div class="form-group mt-lg-3">
                <label for="inputTournamentName">Название вида спорта</label>
                <input type="text" class="form-control" id="inputSportKindName" name="inputSportKindName" aria-describedby="nameHelp">

                <label for="inputTournamentName">Название дисциплины</label>
                <input type="text" class="form-control" id="inputSportDisciplineName" name="inputSportDisciplineName" aria-describedby="nameHelp">
            </div>
            <button type="submit" class="btn btn-outline-success">Добавить</button>
        </div>
    </form>
</@html.page>
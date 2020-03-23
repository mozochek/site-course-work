<#import "parts/html_default.ftlh" as html>
<!--<#import "parts/elements.ftlh" as elem>-->

<@html.page "Добавить турнир">
    <div class="text-center mt-lg-2">
        <h2>Регистрация нового турнира</h2>
    </div>
    <form>
        <div class="col-lg-5">
            <div class="form-group mt-lg-3">
                <label for="inputTournamentName">Название турнира</label>
                <input type="text" class="form-control" id="inputTournamentName" aria-describedby="nameHelp">
                <!--<small id="nameHelp" class="form-text text-muted">Название турнира</small>-->
            </div>
            <div class="form-group">
                <label for="inputTournamentPlace">Место проведения</label>
                <input type="text" class="form-control" id="inputTournamentPlace" aria-describedby="placeHelp">
                <small id="placeHelp" class="form-text text-muted">Город</small>
            </div>
            <button type="button" class="btn btn-outline-success">Зарегестрировать</button>
        </div>
    </form>
</@html.page>
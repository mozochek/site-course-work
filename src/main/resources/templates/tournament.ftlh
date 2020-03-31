<#import "parts/html_default.ftlh" as html>

<@html.page tournament.name>
    <div class="text-center">
        <p><h1>${tournament.name!'Турнир'}</h1></p>
    </div>
    <table class="table table-hover table-dark">
        <thead>
            <tr>
                <th scope="col">Дисциплина</th>
                <th scope="col">Место проведения</th>
                <th scope="col">Формат</th>
                <th scope="col">Дата проведения</th>
                <th scope="col">Ворастная группа</th>
                <th scope="col">Пол игроков</th>
                <th scope="col">Категория</th>
                <th scope="col">Класс</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${tournament.sportDiscipline.getDescription()}</td>
                <td>${tournament.city}</td>
                <td>${tournament.format}</td>
                <td>${tournament.dateFrom}-${tournament.dateTill}</td>
                <td>${tournament.ageGroup}</td>
                <td>${tournament.gender}</td>
                <td>${tournament.category}</td>
                <td>${tournament.tournamentClass}</td>
            </tr>
        </tbody>
    </table>
</@html.page>
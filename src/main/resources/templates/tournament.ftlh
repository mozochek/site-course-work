<#import "parts/html_default.ftlh" as html>

<@html.page tournament.name>
    <div class="text-center">
        <p><h1>${tournament.name!'Турнир'}</h1></p>
    </div>
    <table class="table table-hover table-dark">
        <thead>
            <tr>
                <th scope="col">Название турнира</th>
                <th scope="col">Дисциплина</th>
                <th scope="col">Место проведения</th>
                <th scope="col">Формат турнира</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">${tournament.name!'Название турнира'}</th>
                <td>${tournament.sportDiscipline.name!'Название спортивной дисциплины'}</td>
                <td>${tournament.city!'Город'}</td>
                <td>${tournament.format!'Формат турнира'}</td>
            </tr>
        </tbody>
    </table>
</@html.page>
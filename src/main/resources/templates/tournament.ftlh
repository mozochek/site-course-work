<#import "parts/html_default.ftlh" as html>

<@html.page tournament.name>
    <div class="text-center">
        <p><h1>${tournament.name}</h1></p>
    </div>
    <table class="table table-hover table-dark">
        <thead>
            <tr>
                <th scope="col">Название турнира</th>
                <th scope="col">Место проведения</th>
                <th scope="col">Сроки проведения</th>
                <th scope="col">Возрастная группа</th>
                <th scope="col">Пол игроков</th>
                <th scope="col">Категория</th>
                <th scope="col">Класс</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">${tournament.name}</th>
                <td>${tournament.place}</td>
                <td>${tournament.date}</td>
                <td>${tournament.age}</td>
                <td>${tournament.sex}</td>
                <td>${tournament.category}</td>
                <td>${tournament.type}</td>
            </tr>
        </tbody>
    </table>

    <table class="table table-borderless">
        <thead>
            <tr>
                <th scope="col" class="border">Учатник</th>
                <th scope="col"></th>
                <th scope="col" class="border">1/8</th>
                <th scope="col" class="border">Полуфинал</th>
                <th scope="col" class="border">Финал</th>
            </tr>
        </thead>
        <tbody>
            <#list tournament.contenders as c>
                <tr>
                    <th scope="row" class="border">${c} ${c?counter}</th>
                </tr>
            </#list>
        </tbody>
    </table>
</@html.page>
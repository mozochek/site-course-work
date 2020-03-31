<#import "parts/html_default.ftlh" as html>

<@html.page "Главная страница">
    <div class="text-center">
        <p><h1>Это главная страница</h1></p>
    </div>
    <#if tournaments??>
        <table class="table table-hover table-dark">
        <caption>Список турниров</caption>
            <thead>
                <tr>
                    <th scope="col-2">Название турнира</th>
                    <th scope="col">Дисциплина</th>
                    <th scope="col">Место проведения</th>
                    <!--<th scope="col">Формат</th>-->
                    <th scope="col">Дата проведения</th>
                    <th scope="col">Ворастная группа</th>
                    <th scope="col">Пол игроков</th>
                    <!--<th scope="col">Категория</th>
                    <th scope="col">Класс</th>-->
                </tr>
            </thead>
            <tbody>
            <#list tournaments as t>
                <tr>
                    <th scope="row"><a href="/tournament/${t.id}">${t.name}</a></th>
                    <td>${t.sportDiscipline.getDescription()}</td>
                    <td>${t.city}</td>
                    <!--<td>${t.format}</td>-->
                    <td>${t.dateFrom}-${t.dateTill}</td>
                    <td>${t.ageGroup}</td>
                    <td>${t.gender}</td>
                    <!--<td>${t.category}</td>
                    <td>${t.tournamentClass}</td>-->
                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        Нет турниров!
    </#if>
</@html.page>
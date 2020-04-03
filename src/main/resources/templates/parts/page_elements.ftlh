<#macro navbar>
    <#include "security.ftlh">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"><a href="/" class="nav-link">На начальную</a></li>
                <li class="nav-item"><a href="/tournaments" class="nav-link">Турниры</a></li>
                <#if user?? && user.isAdmin()>
                <li class="nav-item"><a href="/test" class="nav-link">Тест</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Админ-панель</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/admin/sport_kind/add">Добавить вид спорта</a>
                        <a class="dropdown-item" href="/admin/sport_discipline/add">Добавить спортинвую дисциплину</a>
                        <a class="dropdown-item" href="/admin/tournament/registration">Зарегестрировать турнир</a>
                        <a class="dropdown-item" href="/admin/human/add">Добавить человека</a>
                        <a class="dropdown-item" href="/admin/users/all">Список пользователей</a>
                    </div>
                </li>
                </#if>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <#if user??>
                    <div class="form-inline">
                        <div class="text-success mr-md-3"><a href="/profile">${username}</a></div>
                        <a class="text-danger" href="/logout">Выйти</a>
                    </div>
                <#else>
                    <a class="text-success" href="/login">Войти</a>
                </#if>
            </form>
        </div>
    </nav>
</#macro>

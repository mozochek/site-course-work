<#include "security.ftlh">
<!-- Навигационное меню -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a href="/" class="navbar-brand">Начальная</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <#if user??>
        <li class="nav-item"><a href="/tournaments" class="nav-link">Турниры</a></li>
        <#if isModerator>
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="addDataPanel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Добавить данные</a>
            <div class="dropdown-menu" aria-labelledby="addDataPanel">
              <a class="dropdown-item" href="/api/sport_kind/add">Добавить вид спорта</a>
              <a class="dropdown-item" href="/api/sport_discipline/add">Добавить спортинвую дисциплину</a>
              <a class="dropdown-item" href="/api/tournament/registration">Добавить турнир</a>
              <a class="dropdown-item" href="/api/human/add">Добавить человека</a>
              <#if user.isDeveloper()><a class="dropdown-item" href="/dev/create_user">Добавить пользователя</a></#if>
            </div>
          </li>
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="editDataPanel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Показать данные</a>
            <div class="dropdown-menu" aria-labelledby="editDataPanel">
              <a class="dropdown-item" href="/api/sport_kinds">Список видов спорта</a>
              <a class="dropdown-item" href="/api/sport_disciplines">Список спортивных дисциплин</a>
              <a class="dropdown-item" href="/api/people">Список людей</a>
              <#if isAdmin>
                <a class="dropdown-item" href="/admin/users/all">Список пользователей</a>
              </#if>
            </div>
          </li>
        </#if>
        <#if isDeveloper>
          <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="developerPanel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Панель разработчика</a>
            <div class="dropdown-menu" aria-labelledby="developerPanel">
              <a class="dropdown-item" href="/dev/db/backup">Сделать бэкап базы</a>
              <a class="dropdown-item" href="/dev/db/restore">Восстановить базу из бэкапа</a>
            </div>
          </li>
        </#if>
      </#if>
      <li class="nav-item"><a href="/reference" class="nav-link">Справка</a></li>
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
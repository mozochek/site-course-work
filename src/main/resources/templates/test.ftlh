<#import "parts/html_default.ftlh" as html>
<#import "parts/page_elements.ftlh" as elem>

<@html.page "Тестовая страница">
<!-- Large modal -->
<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target=".bd-example-modal-lg">Large modal</button>

<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">New message</h5>
            </div>
            <div class="modal-body">
                <form action="/test" method="post">
                    <div class="form-group">
                        <label for="username" class="col-form-label">Имя пользователя</label>
                        <input type="text" class="form-control" id="username">
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-form-label">Пароль</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-outline-success">Войти</button>
                    </div>
                <form>
            </div>
        </div>
    </div>
</div>
</@html.page>
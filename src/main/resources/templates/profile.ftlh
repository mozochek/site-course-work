<#import "parts/html_default.ftlh" as html>

<@html.page "Мой профиль">
<#include "parts/security.ftlh">
    <div class="d-flex justify-content-center">
        <div class="col-6">
            <div class="text-center my-md-4">
                <h3>${username}</h3>
            </div>
        </div>
    </div>
</@html.page>
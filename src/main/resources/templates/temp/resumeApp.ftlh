<#import "../layout.ftlh" as main>
<@main.layout>
    <link rel="stylesheet" href="/static/css/style.css">
    <div class="profile-box">
        <h3 class="text-danger mb-4">Личный кабинет Соискателя</h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2">
                <img src="api/images/images/1" alt="User" class="img-fluid">
            </div>
            <div class="col-4 text-start">
                <h5>Иван ИВанов</h5>
                <p>30</p>
                <a href="/resume/update/1" class="btn btn-edit">Редактировать</a>
            </div>
            <div class="col-2">
                <button class="btn btn-create rounded-3">+<br>Создать<br>резюме</button>
            </div>
            <div class="col-2 position-relative">
                <button class="btn btn-response rounded-3">Отклики</button>
                <span class="badge-response">5</span>
            </div>
        </div>

        <h5 class="text-start">Резюме:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list resumes as resume>
            <div class="resume-card">
                    <div class="resume-card">
                        <p>${resume.name}</p>
                        <p>Дата обновления:<br>${resume.updateTime}</p>
                        <button class="btn btn-primary btn-sm">Обновить</button>
                        <button class="btn btn-edit btn-sm">Ред.</button>
                    </div>
            </div>
            </#list>
        </div>
    </div>
</@main.layout>
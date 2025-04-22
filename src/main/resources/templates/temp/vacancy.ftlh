<#import "../layout.ftlh" as main>
<@main.layout>
    <link rel="stylesheet" href="/static/css/vacancyStyle.css">
    <div class="profile-box">
        <h3 class="text-danger mb-4">Личный кабинет Работодателя</h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2">
                <a href="images/upload">Загрузить фото</a>
            </div>
            <div class="col-4">
                <h5>Company name</h5>
                <a href="/vacancies/update/1" class="btn btn-edit">Редактировать</a>
            </div>
            <div class="col-4">
                <a href="/vacancy/created" class="btn btn-red rounded-pill p-3">
                    +<br>Создать<br>вакансию
                </a>

                <a href="/vacancy/allResume" class="btn btn-red rounded-pill p-3">
                    <br>Перейти к <br>просмотру резюме
                </a>

        </div>

        <h5 class="text-start">Созданные вакансии:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list vacancies as vacancy>
                <div class="vacancy-card">
                    <p>${vacancy.name}</p>
                    <p>Дата обновления:<br>vacancy.updateTime</p>
                    <button class="btn btn-primary btn-sm">Обновить</button>
                    <a href="/vacancy/edit/${vacancy.id}" class="btn btn-edit btn-sm">Ред.</a>

                </div>
            </#list>
        </div>
    </div>
</@main.layout>


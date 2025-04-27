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
                <h5>название компании</h5>
                <button type="button" class="btn btn-edit" data-bs-toggle="modal" data-bs-target="#editCompanyModal">
                    Редактировать
                </button>
            </div>
            <div class="col-4">
                <a href="/created" class="btn btn-red rounded-pill p-3">
                    +<br>Создать<br>вакансию
                </a>

                <a href="/allResume" class="btn btn-red rounded-pill p-3">
                    <br>Перейти к <br>просмотру резюме
                </a>
            </div>
        </div>

        <h5 class="text-start">Созданные вакансии:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list vacancies as vacancy>
                <div class="vacancy-card">
                    <p>${vacancy.name}</p>
                    <p>Дата обновления:<br>${vacancy.updateTime}</p>
                    <button class="btn btn-primary btn-sm">Обновить</button>
                    <a href="/edit/${vacancy.id}" class="btn btn-edit btn-sm">Ред.</a>
                </div>
            </#list>
        </div>
    </div>

    <div class="modal fade" id="editCompanyModal" tabindex="-1" aria-labelledby="editCompanyModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCompanyModalLabel">Редактировать название компании</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
            </div>
        </div>
    </div>
</@main.layout>



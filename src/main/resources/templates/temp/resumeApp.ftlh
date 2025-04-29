<#import "../layout.ftlh" as main>
<@main.layout>
    <link rel="stylesheet" href="/static/css/style.css">
    <div class="profile-box">
        <h3 class="text-danger mb-4">Личный кабинет Соискателя</h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2">
                <a href="/images/upload">Загрузить фото</a>
            </div>
            <div class="col-4 text-start">
                <h5>${user.name}</h5>
                <p>${user.age}</p>
                <a href="/users/updateProfile/${user.id}" class="btn btn-edit">Редактировать</a>
            </div>
            <div class="col-2">
                <a href="/resume/created" class="btn btn-create rounded-3">+<br>Создать<br>резюме</a>
            </div>
            <div class="col-2 position-relative">
                <button class="btn btn-response rounded-3">Отклики</button>
                <span class="badge-response">5</span>
            </div>
        </div>

        <h5 class="text-start">Резюме:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list resumes as resume>
                <div class="resume-card p-3">
                    <p class="fw-bold mb-2">${resume.name}</p>
                    <p class="text-muted mb-3">Дата обновления:<br><span>${resume.updateTime}</span></p>
                    <div class="d-flex justify-content-center gap-3">
                        <form action="/resume/updateDate/${resume.id}" method="post">
                            <button type="submit" class="btn btn-primary btn-sm px-4">Обновить</button>
                        </form>
                        <a href="/resume/editResume/${resume.id}" class="btn btn-edit btn-sm px-4">Редактировать</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@main.layout>

<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="dropdown mt-3">
        <button class="btn btn-outline-warning dropdown-toggle" type="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown" aria-expanded="false">
            Резюме
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="/responses">Отклики</a></li>
            <li><a class="dropdown-item" href="/resume/resumes">Список резюме</a></li>
        </ul>
    </div>

    <#list resumes as resume>
        <div class="custom-alert">
            <h3>${resume.name}</h3>
            <div class="job-info">
                <span class="job-label">Зарплата:</span> ${resume.salary}
            </div>
            <div class="job-info mt-3">
                <span class="job-label">Дата публикации:</span> ${resume.createdDate}
            </div>
            <div class="d-flex justify-content-end mt-3">
                <a href="/resume/info/${resume.id}" class="btn btn-warning">
                    Подробнее
                </a>
            </div>
        </div>
    </#list>

</@main.layout>
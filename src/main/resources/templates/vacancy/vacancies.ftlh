<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="dropdown mt-3">
        <button class="btn btn-outline-warning dropdown-toggle" type="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown" aria-expanded="false">
            Сортировать вакансии
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="/sortDesc">Сначала новые</a></li>
            <li><a class="dropdown-item" href="/sortAsc">Сначала старые</a></li>
            <li><a class="dropdown-item" href="/responded">По откликам 📥</a></li>
        </ul>
    </div>

    <#list vacancies as vacancy>
        <div class="custom-alert">
            <h3>${vacancy.name}</h3>
            <div class="job-info">
                <span class="job-label">Зарплата:</span> ${vacancy.salary}
            </div>
            <div class="job-info mt-3">
                <span class="job-label">Дата публикации:</span> ${vacancy.createdDate}
            </div>
            <div class="d-flex justify-content-end mt-3">
                <a href="/info/${vacancy.id}" class="btn btn-warning">
                    Подробнее
                </a>
            </div>
        </div>
    </#list>

</@main.layout>

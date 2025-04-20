<#import "../layout.ftlh" as main>
<@main.layout>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <div class="container mt-4">
        <h3 class="mb-4">Список вакансий</h3>

        <#list vacancies as vacancy>
            <div class="card mb-3 w-100">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="card-title text-primary">${vacancy.name}</h5>
                        <p class="card-text mb-1">
                            <strong>Зарплата:</strong> ${vacancy.salary} сом
                        </p>
                        <p class="card-text mb-1">
                            <strong>Дата публикации:</strong> ${vacancy.createdDate}
                        </p>
                    </div>
                    <div class="text-end">
                        <a href="/vacancy/info/${vacancy.id}" class="btn btn-outline-primary">
                            Подробнее
                        </a>
                    </div>
                </div>
            </div>
        </#list>
    </div>

</@main.layout>


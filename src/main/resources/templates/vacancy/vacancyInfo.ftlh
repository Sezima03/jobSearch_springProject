<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-bg: #121212;
            --text-light: #e0e0e0;
            --text-muted: #888888;
        }

        body {
            background-color: var(--dark-bg);
            color: var(--text-light);
        }

        .card {
            background-color: var(--dark-bg);
            border: 1px solid var(--soft-yellow);
        }

        .card-header {
            background-color: var(--dark-bg);
            color: var(--soft-yellow);
            font-weight: bold;
        }

        .card-body {
            color: var(--text-light);
        }

        .card-title {
            color: var(--soft-yellow);
        }

        .card-footer {
            background-color: var(--dark-bg);
            color: var(--text-muted);
        }

        .btn-primary {
            background-color: var(--dark-bg);
            border: 1px solid var(--soft-yellow);
            color: white;
        }

        .btn-primary:hover {
            background-color: var(--soft-yellow);
            color: #121212;
        }

        .card-container {
            max-width: 400px;
            margin: 80px auto 20px auto;
        }

        .modal-content {
            background-color: var(--dark-bg);
            color: var(--text-light);
        }

        .modal-header {
            background-color: var(--dark-bg);
            color: var(--soft-yellow);
        }

        .modal-footer {
            background-color: var(--dark-bg);
            color: var(--text-muted);
        }
    </style>
    <title>Job Search</title>

    <div class="card-container">
        <div class="card text-center">
            <div class="card-header">
                Работодатель:
            </div>
            <div class="card-body">
                <h5 class="card-title">${vacancy.name}</h5>
                <p class="card-text"><p6>Категория: </p6>${category}</p>
                <p class="card-text">${vacancy.description}</p>
                <p class="card-text"><p6>Зарплата: </p6>${vacancy.salary} сом</p>
                <p class="card-text"><p6>Опыт работы от: </p6>${vacancy.expFrom} лет</p>
                <p class="card-text"><p6>Вакансия создана: </p6>${vacancy.createdDate}</p>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#resumeModal">Откликнуться</button>
            </div>
        </div>
    </div>

    <div class="modal fade" id="resumeModal" tabindex="-1" aria-labelledby="resumeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="resumeModalLabel">Выберите резюме</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/applyForVacancy/${vacancy.id}" method="post">
                        <#list resumes as resume>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="resumeId" id="resume-${resume.id}" value="${resume.id}">
                                <label class="form-check-label" for="resume-${resume.id}">
                                    ${resume.name}
                                </label>
                            </div>
                        </#list>
                        <button type="submit" class="btn btn-primary">Отправить отклик</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</@main.layout>
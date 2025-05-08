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

        .btn-primary {
            margin-top: 25px;
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

        .card-text {
            text-align: left;
            font-size: 16px;
        }

        .label-text {
            font-weight: bold;
            font-size: 16px;
        }

        .resume-text {
            color: var(--text-light);
        }
    </style>
    <title>Job Search</title>

    <div class="card-container">
        <div class="card text-center">
            <div class="card-header">
                Соискатель: <span class="label-text" style="color: #e0e0e0">${resume.applicantId.name}</span>
            </div>
            <div class="card-body">
                <h5 class="card-title">${resume.name}</h5>
                <p class="card-text"><span class="label-text">Категория: </span><span class="resume-text">${resume.categoryId}</span></p>
                <p class="card-text"><span class="label-text">Зарплата: </span><span class="resume-text">${resume.salary} сом</span></p>
                <h5 class="card-title">Образование</h5>
                <#list eduInfos as eduInfo>
                    <p class="card-text"><span class="label-text">Учебное заведение: </span><span class="resume-text">${eduInfo.institution}</span></p>
                    <p class="card-text"><span class="label-text">Специальность: </span><span class="resume-text">${eduInfo.program}</span></p>
                    <p class="card-text"><span class="label-text">Год начала обучения: </span><span class="resume-text">${eduInfo.startDate}</span></p>
                    <p class="card-text"><span class="label-text">Год окончания: </span><span class="resume-text">${eduInfo.endDate}</span></p>
                </#list>
                <h5 class="card-title">Опыт работы</h5>
                <#list weis as wei>
                    <p class="card-text"><span class="label-text">Год работы: </span><span class="resume-text">${wei.year}</span></p>
                    <p class="card-text"><span class="label-text">Название компании: </span><span class="resume-text">${wei.companyName}</span></p>
                    <p class="card-text"><span class="label-text">Специальность: </span><span class="resume-text">${wei.position}</span></p>
                    <p class="card-text"><span class="label-text">Обязанности: </span><span class="resume-text">${wei.responsibilities}</span></p>
                </#list>

                <p class="card-text"><span class="label-text">Вакансия создана: </span><span class="resume-text">${resume.createdDate}</span></p>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#resumeModal">Подтвердить</button>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</@main.layout>

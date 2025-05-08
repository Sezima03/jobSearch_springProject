<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        .custom-alert {
            margin-bottom: 20px;
        }
    </style>
    <div class="container mt-5">
        <title>Откликнувшиеся кандидаты</title>

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

        <div class="mt-4">
            <#list respondedApplicant as applicant>
                <div class="custom-alert">
                    <h3>${applicant}</h3>
                    <div class="job-info" style="padding-top: 15px">
                        <span class="job-label">${applicant.resumeId}</span>
                    </div>
                    <div class="job-info" style="padding-top: 15px">
                        <span class="job-label">${applicant.vacancyId}</span>
                    </div>
                    <div class="job-info mt-3">
                        <span class="job-label">Подтверждено:</span>
<#--                        <span class="badge bg-success">${applicant.confirmation}</span>-->
                    </div>
                    <div class="d-flex justify-content-end mt-3">
                        <#--                    <form action="/respond/confirm" method="POST">-->
                        <input type="hidden" name="responseId" value="1">
                        <button type="submit" class="btn btn-sm btn-success">Подтвердить</button>
                        <#--                    </form>-->
                        <a href="#" class="btn btn-sm btn-primary ms-2">Просмотреть резюме</a>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</@main.layout>
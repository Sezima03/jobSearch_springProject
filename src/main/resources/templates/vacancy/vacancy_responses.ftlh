<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-5">
        <h2 class="text-warning mb-4">Откликнувшиеся кандидаты</h2>

        <#if applicants?? && applicants?size gt 0>
            <table class="table table-dark table-hover">
                <thead>
                <tr>
                    <th>ID Пользователя</th>
                    <th>ID Резюме</th>
                    <th>Подтвержден</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <#list applicants as applicant>
                    <tr>
                        <td>${applicant.userId}</td>
                        <td>
                            <a href="/resume/view/${applicant.resumeId}" target="_blank" class="btn btn-sm btn-outline-info">
                                Посмотреть
                            </a>
                        </td>
                        <td>
                            <#if applicant.confirmation>
                                <span class="badge bg-success">Да</span>
                            <#else>
                                <span class="badge bg-secondary">Нет</span>
                            </#if>
                        </td>
                        <td>
                            <#if !applicant.confirmation>
                                <form method="post" action="/employer/confirm_response">
                                    <input type="hidden" name="responseId" value="${applicant.id}" />
                                    <button type="submit" class="btn btn-sm btn-success">Подтвердить</button>
                                </form>
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        <#else>
            <div class="alert alert-warning text-center">
                Пока нет откликов на эту вакансию.
            </div>
        </#if>

        <div class="mt-4 text-center">
            <a href="/employer/vacancies" class="btn btn-outline-warning">Назад к вакансиям</a>
        </div>
    </div>
</@main.layout>

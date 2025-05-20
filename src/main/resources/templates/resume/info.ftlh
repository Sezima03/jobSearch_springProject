<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
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
                <@spring.message "jobSearch.applicant"/> <span class="label-text" style="color: #e0e0e0">${resume.applicantId.name}</span>
            </div>
            <div class="card-body">
                <h5 class="card-title">${resume.name}</h5>
                <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.category"/> </span><span class="resume-text">${resume.categoryId}</span></p>
                <p class="card-text"><span class="label-text"><@spring.message "layout.nav.salary"/> </span><span class="resume-text">${resume.salary} сом</span></p>
                <h5 class="card-title"><@spring.message "jobSearch.info.edu"/></h5>
                <#list eduInfos as eduInfo>
                    <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.ins"/> </span>
                        <#if eduInfo.institution??>
                            <span class="resume-text">${eduInfo.institution}</span></p>
                            <#else>
                            <span class="text-muted"><@spring.message"jobSearch.info.notAvailable"/></span>
                        </#if>

                    <#if eduInfo.program??>
                     <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.program"/> </span><span class="resume-text">${eduInfo.program}</span></p>
                        <#else>
                            <span class="text-muted"><@spring.message"jobSearch.info.notAvailable"/></span>
                    </#if>

                    <p class="card-text">
                        <span class="label-text"><@spring.message "jobSearch.info.startDate"/> </span>
                        <span class="resume-text">
        <#if eduInfo.startDate??>
            ${eduInfo.startDate}
        <#else>
            <@spring.message "jobSearch.info.notAvailable"/>
        </#if>
    </span>
                    </p>
                    <p class="card-text">
                        <span class="label-text"><@spring.message "jobSearch.info.endDate"/> </span>
                        <span class="resume-text">
        <#if eduInfo.endDate??>
            ${eduInfo.endDate}
        <#else>
            <span class="text-muted"><@spring.message "jobSearch.info.notAvailable"/></span>
        </#if>
    </span></p></#list>
                <h5 class="card-title"><@spring.message "jobSearch.info.wei"/></h5>

                <#list weis as wei>
                    <#if wei.year??>
                        ${wei.year}
                    <#else>
                    </#if>

                    <#if wei.companyName??>
                      <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.companyTitle"/> </span><span class="resume-text">${wei.companyName}</span></p>
                      <#else>
                       <@spring.message "jobSearch.info.notAvailable"/>
                    </#if>

                    <#if wei.position??>
                     <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.position"/> </span><span class="resume-text">${wei.position}</span></p>
                    <#else>
                    </#if>
                    <#if wei?? && wei.responsibilities??>
                        ${wei.responsibilities}
                    <#else>
                    </#if>
                </#list>

                <p class="card-text"><span class="label-text"><@spring.message "jobSearch.info.createVacancy"/> </span><span class="resume-text">${resume.createdDate}</span></p>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#resumeModal"><@spring.message "jobSearch.info.confirm"/></button>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</@main.layout>

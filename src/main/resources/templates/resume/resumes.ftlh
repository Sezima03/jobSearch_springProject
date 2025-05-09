<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h3 style="margin-left: 40%; color: #bfa500"><@spring.message "resume.all"/></h3>
    <div class="dropdown mt-3">
        <button class="btn btn-outline-warning dropdown-toggle" type="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown" aria-expanded="false">
            <@spring.message "profileApplicant.resumes"/>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="/responses">Отклики</a></li>
            <li><a class="dropdown-item" href="/resume/resumes"></a><@spring.message "resume.all"/></li>
            <li><a class="dropdown-item" href="/users/profileEmp"></a><@spring.message "jobSearchExit"/></li>
        </ul>
    </div>

    <#list resumes as resume>
        <div class="custom-alert">
            <h3>${resume.name}</h3>
            <div class="job-info">
                <span class="job-label"><@spring.message "layout.nav.salary"/></span> ${resume.salary}
            </div>
            <div class="job-info mt-3">
                <span class="job-label"><@spring.message "layout.nav.publicationDate"/></span> ${resume.createdDate}
            </div>
            <div class="d-flex justify-content-end mt-3">
                <a href="/resume/info/${resume.id}" class="btn btn-warning">
                    <@spring.message "layout.nav.info"/>
                </a>
            </div>
        </div>
    </#list>

</@main.layout>
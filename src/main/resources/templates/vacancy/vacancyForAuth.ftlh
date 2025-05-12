<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>

    <div class="dropdown mt-3">
        <button class="btn btn-outline-warning dropdown-toggle" type="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown" aria-expanded="false">
            <@spring.message "layout.nav.sortVacancy"/>
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="/sortDesc"><@spring.message "layout.nav.sortDesc"/></a></li>
            <li><a class="dropdown-item" href="/sortAsc"><@spring.message "layout.nav.sortAsc"/></a></li>
            <li><a class="dropdown-item" href="/responseToVacancy"><@spring.message "responses"/></a></li>

        </ul>
    </div>

    <#list vacancies as vacancy>
        <div class="custom-alert">
            <h3>${vacancy.name}</h3>
            <div class="job-info">
                <span class="job-label"><@spring.message "layout.nav.salary"/></span> ${vacancy.salary}
            </div>
            <div class="job-info mt-3">
                <span class="job-label"><@spring.message "layout.nav.publicationDate"/></span> ${vacancy.createdDate}
            </div>
            <div class="d-flex justify-content-end mt-3">
                <a href="/info/${vacancy.id}" class="btn btn-warning">
                    <@spring.message "layout.nav.info"/>
                </a>
            </div>
        </div>
    </#list>

</@main.layout>

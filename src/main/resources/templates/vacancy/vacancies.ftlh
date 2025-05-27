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
        </ul>
    </div>

    <form id="filterForm" class="mt-4">
        <div class="row">
            <div class="col">
                <input type="text" name="position" class="form-control" id="filterName" placeholder="Название вакансии">
            </div>
            <div class="col">
                <input type="text" name="minSalary" class="form-control" id="filterSalary" placeholder="Зарплата (от)">
            </div>
            <div class="col">
                <button type="submit" class="btn btn-warning">Применить фильтр</button>
            </div>
        </div>
    </form>
    <div id="vacancyList" class="mt-4">
        <#list vacancies as vacancy>
            <div class="custom-alert job-item"
                 data-title="${vacancy.name?lower_case}"
                 data-salary="${vacancy.salary}">
                <h3>${vacancy.name}</h3>
                <div class="job-info">
                    <span class="job-label"><@spring.message "layout.nav.salary"/></span> ${vacancy.salary}
                </div>
                <div class="job-info mt-3">
                    <span class="job-label"><@spring.message "layout.nav.publicationDate"/></span> ${vacancy.createdDate}
                </div>
                <div class="d-flex justify-content-end mt-3">
                    <a href="/infoForUnauthorized/${vacancy.id}" class="btn btn-warning">
                        <@spring.message "layout.nav.info"/>
                    </a>
                </div>
            </div>
        </#list>
    </div>

    <script>
        const form = document.getElementById('filterForm');
        const vacancyList = document.getElementById('vacancyList');

        form.addEventListener('submit', function(e) {
            e.preventDefault();

            const positionInput = form.querySelector('[name="position"]').value.trim().toLowerCase();
            const minSalaryInput = parseInt(form.querySelector('[name="minSalary"]').value) || 0;

            const filters = {
                position: positionInput,
                minSalary: minSalaryInput
            };
            localStorage.setItem('vacancyFilters', JSON.stringify(filters));

            applyFilters(filters);
        });

        function applyFilters(filters) {
            const allVacancies = document.querySelectorAll('.job-item');

            allVacancies.forEach(function(vacancy) {
                const title = vacancy.dataset.title;
                const salary = parseInt(vacancy.dataset.salary);

                const titleCheck = !filters.position || title.includes(filters.position);
                const salaryCheck = salary >= filters.minSalary;

                vacancy.style.display = (titleCheck && salaryCheck) ? '' : 'none';
            });
        }

        window.addEventListener('DOMContentLoaded', function() {
            const savedFilters = localStorage.getItem('vacancyFilters');
            if (savedFilters) {
                const filters = JSON.parse(savedFilters);

                form.querySelector('[name="position"]').value = filters.position || '';
                form.querySelector('[name="minSalary"]').value = filters.minSalary || '';

                applyFilters(filters);
            }
        });
    </script>
</@main.layout>
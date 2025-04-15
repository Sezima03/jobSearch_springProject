<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link rel="stylesheet" href="/static/css/editeResume.css">

    <h2>Создание резюме</h2>

    <form action="/resume/created" method="POST" class="mt-4">

        <label for="name">Название резюме:</label>
        <@spring.formInput "resumeDto.name" 'id="name" class="form-control form-control-lg" placeholder="Введите название"' />
        <@spring.showErrors "resumeDto.name" "error text-danger" />

        <label for="applicantId">applicantId</label>
        <@spring.formInput "resumeDto.applicantId" 'id="applicantId" class="form-control form-control-lg" placeholder="Введите пользователя"' />
        <@spring.showErrors "resumeDto.applicantId" "error text-danger" />

        <label for="categoryId">Категория (ID):</label>
        <@spring.formInput "resumeDto.categoryId" 'id="categoryId" class="form-control"' />
        <@spring.showErrors "resumeDto.categoryId" "error text-danger" />

        <label for="salary">Ожидаемая зарплата (сом):</label>
        <@spring.formInput "resumeDto.salary" 'id="salary" class="form-control"' />
        <@spring.showErrors "resumeDto.salary" "error text-danger" />

        <fieldset>
            <legend>Образование</legend>

            <label for="institution">Учебное заведение:</label>
            <@spring.formInput "educationfoDto.institution" 'id="institution" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.institution" "error text-danger" />

            <label for="program">Program</label>
            <@spring.formInput "educationfoDto.program" 'id="program" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.program" "error text-danger" />
        </fieldset>

        <fieldset>
            <legend>Опыт работы</legend>

            <label for="year">Годы работы:</label>
            <@spring.formInput "work.year" 'id="year" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "work.year" "error text-danger" />

            <label for="companyName">Название компании:</label>
            <@spring.formInput "work.companyName" 'id="companyName" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "work.companyName" "error text-danger" />

            <label for="position">Должность:</label>
            <@spring.formInput "work.position" 'id="position" class="form-control form-control-lg" placeholder="Введите должность"' />
            <@spring.showErrors "work.position" "error text-danger" />

            <label for="responsibilities">Обязанности:</label>
            <textarea id="responsibilities" name="responsibilities"
                      placeholder="Опишите, чем вы занимались"></textarea>
        </fieldset>

        <label><input type="checkbox" name="active" checked> Опубликовано</label>

        <button type="submit">Сохранить изменения</button>
    </form>

</@main.layout>
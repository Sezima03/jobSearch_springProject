<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link rel="stylesheet" href="/static/css/editeResume.css">

    <h2>Редактирование резюме</h2>

    <form action="/resume/editResume/${resumes.id}" method="POST" class="mt-4">

        <label for="name">Название резюме:</label>
        <@spring.formInput "resumeDto.name" 'id="name" class="form-control form-control-lg" placeholder="Введите название"' />
        <@spring.showErrors "resumeDto.name" "error text-danger" />

            <label>Категория:</label>
            <select id="category" name="category" required>
                <option value="" disabled selected>Выберите категорию</option>
                <option value="js">js</option>
                <option value="java">java</option>
                <option value="python">python</option>
                <option value="project manager">project manager</option>
            </select>
            <@spring.showErrors "resumeDto.categoryId" "error text-danger" />

        <label for="salary">Ожидаемая зарплата (сом):</label>
        <input type="number" id="salary" name="salary" placeholder="Введите сумму" required>

        <fieldset>
            <legend>Образование</legend>

          <label for="institution">Учебное заведение:</label>
            <@spring.formInput "educationfoDto.institution" 'id="institution" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.institution" "error text-danger" />

            <label for="program">Program</label>
            <@spring.formInput "educationfoDto.program" 'id="program" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.program" "error text-danger" />

            <label for="startDate">Дата начала обучения:</label>
            <@spring.formInput "educationfoDto.startDate" 'id="startDate" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.startDate" "error text-danger" />


            <label for="endDate">Дата окончания обучения:</label>
            <@spring.formInput "educationfoDto.endDate" 'id="endDate" class="form-control form-control-lg" placeholder="Введите название"' />
            <@spring.showErrors "educationfoDto.endDate" "error text-danger" />
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
            <input type="text" id="position" name="position" placeholder="Программист">

            <label for="responsibilities">Обязанности:</label>
            <textarea id="responsibilities" name="responsibilities"
                      placeholder="Опишите, чем вы занимались"></textarea>
        </fieldset>

        <label><input type="checkbox" name="active" checked> Опубликовано</label>

        <button type="submit">Сохранить изменения</button>
    </form>

</@main.layout>
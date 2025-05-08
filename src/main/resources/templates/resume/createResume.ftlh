<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link rel="stylesheet" href="/static/css/editeResume.css">

    <h2>Создание резюме</h2>

    <form action="/resume/create" method="POST" class="mt-4">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>
        <div class="form-group">
            <label for="name">Название резюме:</label>
            <@spring.formInput "resumeDto.name" 'id="name" class="form-control form-control-sm" placeholder="Введите название"' />
            <@spring.showErrors "resumeDto.name" "error text-danger" />
        </div>

        <div class="form-group">
            <label for="categoryId">Выберите категорию</label>
            <select name="categoryId" id="categoryId" class="form-control form-control-sm">
                <option value=1>java script</option>
                <option value=2>java</option>
                <option value=3>python</option>
                <option value=4>project manager</option>
            </select>
            <@spring.showErrors "resumeDto.categoryId" "error text-danger" />
        </div>

        <div class="form-control">
            <label for="salary">Ожидаемая зарплата (сом):</label>
            <@spring.formInput "resumeDto.salary" 'id="salary" class="form-control form-control-sm"'/>
            <@spring.showErrors "resumeDto.salary" "error text-danger" />
        </div>

        <fieldset>
            <legend>Образование</legend>

            <div class="form-control">
                <label for="institution">Учебное заведение:</label>
                <@spring.formInput "educationfoDto.institution" 'id="institution" class="form-control form-control-sm" placeholder="Введите название"' />
                <@spring.showErrors "educationfoDto.institution" "error text-danger" />
            </div>

            <div class="form-group">
                <label for="program">Program</label>
                <@spring.formInput "educationfoDto.program" 'id="program" class="form-control form-control-sm" placeholder="Введите название"' />
                <@spring.showErrors "educationfoDto.program" "error text-danger" />
            </div>
        </fieldset>

        <fieldset>
            <legend>Опыт работы</legend>

            <div class="for-group">
                <label for="year">Годы работы:</label>
                <@spring.formInput "work.year" 'id="year" class="form-control form-control-sm" placeholder="Введите название"' />
                <@spring.showErrors "work.year" "error text-danger" />
            </div>

            <div class="form-group">
                <label for="companyName">Название компании:</label>
                <@spring.formInput "work.companyName" 'id="companyName" class="form-control form-control-sm" placeholder="Введите название"' />
                <@spring.showErrors "work.companyName" "error text-danger" />
            </div>

            <div class="form-group">
                <label for="position">Должность:</label>
                <@spring.formInput "work.position" 'id="position" class="form-control form-control-sm" placeholder="Введите должность"' />
                <@spring.showErrors "work.position" "error text-danger" />
            </div>

            <div class="form-group">
                <label for="responsibilities">Обязанности:</label>
                <textarea id="responsibilities" name="responsibilities" class="form-control form-control-sm"
                          placeholder="Опишите, чем вы занимались"></textarea>
            </div>
        </fieldset>

        <label><input type="checkbox" name="active" checked> Опубликовано</label>

        <button type="submit" class="btn-yellow">Сохранить изменения</button>
        <button type="reset" class="btn-reset">Отменить изменения</button>
    </form>

</@main.layout>
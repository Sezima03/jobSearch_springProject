<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link rel="stylesheet" href="/static/css/createdVacancy.css">
    <h2>Изменение вакансии</h2>

    <form action="/update/${vacancy.id}" method="POST">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>

        <label for="companyName">Название компании:</label>
        <@spring.formInput "vacancy.companyName" 'id="vacancy.companyName" class="form-control"' />
        <@spring.showErrors "vacancy.companyName" "error text-danger" />

        <label for="name">Название вакансии:</label>
        <@spring.formInput "vacancy.name" 'id="name" class="form-control"' />
        <@spring.showErrors "vacancy.name" "error text-danger" />

        <label for="description">Описание:</label>
        <@spring.formTextarea "vacancy.description" 'id="description" class="form-control" placeholder="Опиши обязанности и требования..." required' />
        <@spring.showErrors "vacancy.description" "error text-danger" />

        <label for="categoryId">Категория (ID):</label>
        <select name="categoryId" id="categoryId" class="form-control">
            <option value=1>js</option>
            <option value=2>java</option>
            <option value=3>python</option>
            <option value=4>project Manager</option>
        </select>
        <@spring.showErrors "vacancy.categoryId" "error text-danger" />

        <label for="salary">Зарплата (сом):</label>
        <@spring.formInput "vacancy.salary" 'id="salary" class="form-control" type="number" required placeholder="Пример: 100000"' />
        <@spring.showErrors "vacancy.salary" "error text-danger" />

        <label for="expFrom">Опыт от (лет):</label>
        <@spring.formInput "vacancy.expFrom" 'id="expFrom" class="form-control" type="number" min="0"' />
        <@spring.showErrors "vacancy.expFrom" "error text-danger" />

        <label for="expTo">Опыт до (лет):</label>
        <@spring.formInput "vacancy.expTo" 'id="expTo" class="form-control" type="number" min="0"' />
        <@spring.showErrors "vacancy.expTo" "error text-danger" />


        <label>
            <input type="hidden" name="vacancy.isActive" value="false" />
            <@spring.formCheckbox "vacancy.isActive" 'checked' /> Активна
        </label>

        <button type="submit" class="btn btn-primary mt-3">Создать вакансию</button>
    </form>
</@main.layout>
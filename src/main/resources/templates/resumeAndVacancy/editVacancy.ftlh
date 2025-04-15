<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link rel="stylesheet" href="/static/css/createdVacancy.css">



    <h2>Редактирование вакансии</h2>

    <form action="/vacancy/edit/${vacancy.id}" method="POST" class="mb-4">

        <label for="name">Название вакансии:</label>
        <@spring.formInput "vacancy.name" 'id="name" class="form-control"' />
        <@spring.showErrors "vacancy.name" "error text-danger" />

        <label for="description">Описание:</label>
        <@spring.formTextarea "vacancy.description" 'id="description" class="form-control" placeholder="Опиши обязанности и требования..." required' />
        <@spring.showErrors "vacancy.description" "error text-danger" />

        <label for="categoryId">Категория (ID):</label>
        <@spring.formInput "vacancy.categoryId" 'id="categoryId" class="form-control"' />
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

        <label for="authorId">ID автора:</label>
        <@spring.formInput "vacancy.authorId" 'id="authorId" class="form-control" required' />
        <@spring.showErrors "vacancy.authorId" "error text-danger" />

        <label>
            <input type="hidden" name="vacancy.isActive" value="false" />
            <@spring.formCheckbox "vacancy.isActive" 'checked' /> Активна
        </label>

        <button type="submit" class="btn btn-primary mt-3">Создать вакансию</button>
    </form>
</@main.layout>

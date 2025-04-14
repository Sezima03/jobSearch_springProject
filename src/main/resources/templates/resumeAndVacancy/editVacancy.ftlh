    <#import "../layout.ftlh" as main>
    <@main.layout ; spring>
        <link rel="stylesheet" href="/static/css/createdVacancy.css">



        <h2>Создание вакансии</h2>

        <form action="/vacancy/edit/${vacancy.id}" method="POST" class="mb-4">

            <label for="name">Редактирование вакансии:</label>
            <@spring.formInput "vacancy.name" 'id="name" class="form-control"'/>
            <@spring.showErrors "vacancy.name" "error text-danger"/>

            <label for="description">Описание:</label>
            <textarea id="description" name="description" placeholder="Опиши обязанности и требования..."
                      required></textarea>

            <label for="categoryId">Категория:</label>
            <select id="categoryId" name="categoryId" required>
                <option disabled selected value="">Выберите категорию</option>
                <option value="java">Java</option>
                <option value="python">Python</option>
                <option value="frontend">Frontend</option>
                <option value="pm">Project Manager</option>
            </select>

            <label for="salary">Зарплата (сом):</label>
            <input type="number" id="salary" name="salary" required placeholder="Пример: 100000">

            <label for="expFrom">Опыт от (лет):</label>
            <input type="number" id="expFrom" name="expFrom" min="0">

            <label for="expTo">Опыт до (лет):</label>
            <input type="number" id="expTo" name="expTo" min="0">

            <label for="authorId">ID автора:</label>
            <input type="text" id="authorId" name="authorId" required>


            <label>
                <input type="checkbox" name="isActive" checked> Активна
            </label>

            <button type="submit">Создать вакансию</button>
        </form>
    </@main.layout>

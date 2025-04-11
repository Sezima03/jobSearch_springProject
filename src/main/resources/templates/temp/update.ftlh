<#import "../layout.ftlh" as main>
<@main.layout>
    <h3 class="mb-4 text-center text-primary">Редактировать профиль</h3>

    <form method="post" action="/applicant/update/${user.id}">
        <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <input type="text" id="name" name="name" value="${user.name}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="surname" class="form-label">Фамилия</label>
            <input type="text" id="surname" name="surname" value="${user.surname}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="age" class="form-label">Возраст</label>
            <input type="number" id="age" name="age" value="${user.age}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" value="${user.email}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Телефон</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="avatar" class="form-label">Ссылка на аватар</label>
            <input type="text" id="avatar" name="avatar" value="${user.avatar}" class="form-control">
        </div>

        <button type="submit" class="btn btn-success">Сохранить изменения</button>
        <a href="/applicant" class="btn btn-secondary">Отмена</a>
    </form>
</@main.layout>

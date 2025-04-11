<#import "../layout.ftlh" as main>
<@main.layout>
    <h2 class="mt-5">Регистрация нового пользователя</h2>

    <form action="/auth/login" method="POST" class="mt-4">
        <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Фамилия</label>
            <input type="text" class="form-control" id="surname" name="surname" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Пароль</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="mb-3">
            <label for="role" class="form-label">Тип учетной записи</label>
            <select class="form-control" id="role" name="role">
                <option value="APPLICANT">Соискатель</option>
                <option value="EMPLOYER">Работодатель</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
</@main.layout>

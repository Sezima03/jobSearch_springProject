<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <h2 class="mt-5">Регистрация нового пользователя</h2>

    <form action="/users" method="POST" class="mt-4">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                   value="${(_csrf.token)!'csrf-token'}"/>
        </#if>


        <div class="mb-3">
            <label for="name" class="form-label">Имя</label>
            <@spring.formInput "userDto.name" 'id="name" class="form-control form-control-lg"' />
            <@spring.showErrors "userDto.name" "error text-danger" />
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Фамилия</label>
            <@spring.formInput "userDto.surname" 'id="surname" class="form-control form-control-lg"' />
            <@spring.showErrors "userDto.surname" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="age" class="form-label">Возраст</label>
            <@spring.formInput "userDto.age" 'id="age" class="form-control form-control-lg"' />
            <@spring.showErrors "userDto.age" "error text-danger" />
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <@spring.formInput "userDto.email" 'id="form3Example3cg" class="form-control form-control-lg"' />
            <@spring.showErrors "<br>" "error text-danger" />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Пароль</label>
            <@spring.formPasswordInput "userDto.password" 'id="password" class="form-control form-control-lg"' />
            <@spring.showErrors "userDto.password" "text-danger" />
        </div>

        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Номер телефона</label>
            <@spring.formInput "userDto.phoneNumber" 'id="phoneNumber" class="form-control form-control-lg"' />
            <@spring.showErrors "userDto.phoneNumber" "error text-danger" />
        </div>

        <label for="authorityId">Тип учетной записи</label>
        <select name="authorityId" id="authorityId" class="form-control">
            <option value=1>соискатель</option>
            <option value=2>работодатель</option>
        </select>
        <@spring.showErrors "userDto.authorityId" "error text-danger" />

        <button type="submit" class="btn btn-primary mt-4">Зарегистрироваться</button>
    </form>

    <div class="text-center mt-3">
        Уже зарегистрированы? <a href="/auth/login">Войти</a>
    </div>
</@main.layout>

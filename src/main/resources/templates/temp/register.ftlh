<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="card w-100 shadow p-4" style="max-width: 600px;">
            <h3 class="card-title mb-4 text-center">Регистрация нового пользователя</h3>

            <form action="/users" method="POST">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <div class="mb-3">
                    <label for="name" class="form-label">Имя</label>
                    <@spring.formInput "userDto.name" 'id="name" class="form-control"' />
                    <@spring.showErrors "userDto.name" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="surname" class="form-label">Фамилия</label>
                    <@spring.formInput "userDto.surname" 'id="surname" class="form-control"' />
                    <@spring.showErrors "userDto.surname" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="age" class="form-label">Возраст</label>
                    <@spring.formInput "userDto.age" 'id="age" class="form-control"' />
                    <@spring.showErrors "userDto.age" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <@spring.formInput "userDto.email" 'id="email" class="form-control"' />
                    <@spring.showErrors "userDto.email" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Пароль</label>
                    <@spring.formPasswordInput "userDto.password" 'id="password" class="form-control"' />
                    <@spring.showErrors "userDto.password" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Номер телефона</label>
                    <@spring.formInput "userDto.phoneNumber" 'id="phoneNumber" class="form-control"' />
                    <@spring.showErrors "userDto.phoneNumber" "text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="authorityId" class="form-label">Тип учетной записи</label>
                    <select name="authorityId" id="authorityId" class="form-select">
                        <option value=1>Соискатель</option>
                        <option value=2>Работодатель</option>
                    </select>
                    <@spring.showErrors "userDto.authorityId" "text-danger small" />
                </div>

                <button type="submit" class="btn btn-primary w-100 mt-3">Зарегистрироваться</button>
            </form>

            <div class="text-center mt-3">
                Уже зарегистрированы? <a href="/auth/login">Войти</a>
            </div>
        </div>
    </div>
</@main.layout>


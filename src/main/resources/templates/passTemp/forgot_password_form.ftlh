<#import "../layout.ftlh" as main>
<@main.layout>
    <style>
        .card {
            border: none;
            border-radius: 1rem;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
        }
        .form-label {
            font-weight: 500;
        }
        .btn-primary {
            width: 100%;
        }
    </style>

    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="card w-100" style="max-width: 500px;">
            <div class="card-body p-5">
                <h3 class="card-title mb-4 text-center">Восстановление пароля</h3>

                <#if error??>
                    <div class="alert alert-danger text-center">${error}</div>
                </#if>
                <#if message??>
                    <div class="alert alert-success text-center">${message}</div>
                </#if>

                <form action="/auth/forgot_password" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Введите email" required autofocus>
                    </div>

                    <p class="small text-muted">
                        Мы отправим ссылку для сброса пароля на вашу почту.
                    </p>

                    <button type="submit" class="btn btn-primary">Отправить</button>
                </form>

                <div class="mt-4 text-center">
                    <a href="/">Вернуться в главную страницу</a>
                </div>
            </div>
        </div>
    </div>
</@main.layout>

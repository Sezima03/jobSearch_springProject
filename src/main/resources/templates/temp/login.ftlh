<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>Вход в систему</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #ece9e6, #ffffff);
            height: 100vh;
        }
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
        .login-img {
            object-fit: cover;
            height: 100%;
            border-top-left-radius: 1rem;
            border-bottom-left-radius: 1rem;
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="card w-100" style="max-width: 900px;">
        <div class="row g-0">
            <div class="col-md-6 d-none d-md-block">
                <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                     alt="Login image" class="img-fluid login-img">
            </div>
            <div class="col-md-6">
                <div class="card-body p-5">
                    <h3 class="card-title mb-4 text-center">Вход</h3>
                    <form id="login-form" action="/auth/login" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                                   value="${(_csrf.token)!'csrf-token'}"/>
                        </#if>
                        <div class="mb-3">
                            <label for="formEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="formEmail" name="username"
                                   placeholder="Введите email" required>
                        </div>
                        <div class="mb-3">
                            <label for="formPassword" class="form-label">Пароль</label>
                            <input type="password" class="form-control" id="formPassword" name="password"
                                   placeholder="Введите пароль" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Войти</button>
                    </form>
                    <div class="mt-4 text-center">
                        <small>Нет аккаунта? <a href="/users">Зарегистрируйтесь</a></small>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

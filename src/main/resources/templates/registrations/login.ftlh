<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" />
    <title>Job Search - Авторизация</title>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-bg: #121212;
            --text-light: #e0e0e0;
            --text-muted: #888888;
        }

        body {
            background-color: var(--dark-bg);
            color: var(--text-light);
            font-family: 'Arial', sans-serif;
        }

        .login-container {
            max-width: 400px;
            margin: 5rem auto;
            padding: 2rem;
            background-color: #1a1a1a;
            border-radius: 8px;
            border: 2px solid var(--soft-yellow);
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 2rem;
            color: var(--soft-yellow);
        }

        .form-control {
            background-color: #2c2c2c;
            border: 1px solid #444;
            color: var(--text-light);
        }

        .form-control::placeholder {
            color: #aaa;
        }

        .btn-yellow {
            background-color: var(--soft-yellow);
            color: black;
            width: 100%;
        }

        .btn-yellow:hover {
            background-color: #e0e0e0;
            color: var(--dark-bg);
        }

        .text-center a {
            color: var(--soft-yellow);
            text-decoration: none;
        }

        .text-center a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="login-container">
        <h2>Авторизация</h2>
        <form action="/auth/login" method="POST">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}"/>
            </#if>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="username" placeholder="Введите ваш email" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Введите ваш пароль" required>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-yellow">Войти</button>
            </div>
        </form>

        <div class="text-center mt-3">
            <a href="/">Еще нет аккаунта? Вернуться в главную страницу</a>
        </div>

        <div class="text-center mt-3">
            <a href="/auth/forgot_password">Забыли пароль?</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>


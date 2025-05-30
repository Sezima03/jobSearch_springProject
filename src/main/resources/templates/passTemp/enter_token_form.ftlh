<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring/>
<@main.layout>
    <style>
        .card {
            border: none;
            border-radius: 20px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
            background-color: #1a1a1a;
        }

        .card-body {
            padding: 2rem;
            color: white;
        }

        .form-label {
            font-weight: 500;
            color: #f8d7da;
        }

        .form-control {
            border-radius: 10px;
            padding: 10px;
            border: 1px solid #ffd700;
            background-color: #333;
            color: white;
        }

        .form-control:focus {
            border-color: #ff7b7b;
            box-shadow: 0 0 10px rgba(255, 123, 123, 0.8);
        }

        .btn-primary {
            width: 100%;
            background: linear-gradient(135deg, #ff7b7b, #7b99ff);
            border: none;
            color: #0e0620;
            padding: 12px;
            border-radius: 10px;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background: linear-gradient(135deg, #ff7b7b, #ff99c3);
        }

        .alert {
            border-radius: 10px;
            margin-bottom: 1rem;
        }

        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
            padding: 1rem;
            text-align: center;
        }

        a {
            color: #f8d7da;
            text-decoration: none;
        }

        a:hover {
            color: #ffd700;
        }
    </style>

    <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="card w-100" style="max-width: 500px;">
            <div class="card-body p-5">
                <h3 class="card-title mb-4 text-center" style="color: #ffd700;">Введите токен восстановления</h3>
                <#if error??>
                    <div class="alert alert-danger">${error}</div>
                </#if>
                <form method="post" action="/auth/enter_token">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param'}" value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="token" class="form-label">Токен</label>
                        <input type="text" class="form-control" id="token" name="token" placeholder="Введите токен" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Продолжить</button>
                </form>
                <div class="mt-4 text-center">
                    <a href="/"><@spring.message "return.to.main"/></a>
                </div>
            </div>
        </div>
    </div>
</@main.layout>

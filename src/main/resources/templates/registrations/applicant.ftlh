<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-overlay: rgba(18, 18, 18, 0.85);
            --text-light: #e0e0e0;
        }

        body,
        html {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            color: var(--text-light);
        }

        .bg-image {
            background: url('https://images.unsplash.com/photo-1542744173-05336fcc7ad4') no-repeat center center fixed;
            background-size: cover;
            height: 100%;
            position: relative;
        }

        .overlay {
            background-color: var(--dark-overlay);
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .registration-form {
            background-color: #1a1a1a;
            padding: 2rem 2.5rem;
            border-radius: 10px;
            border: 2px solid var(--soft-yellow);
            width: 100%;
            max-width: 500px;
            box-shadow: 0 0 20px rgba(255, 215, 0, 0.3);
        }

        .registration-form h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: var(--soft-yellow);
        }

        .btn-yellow {
            background-color: var(--soft-yellow);
            color: black;
            border: none;
        }

        .btn-yellow:hover {
            background-color: #e6c200;
        }
    </style>
    </head>

    <body>
    <div class="bg-image">
        <div class="overlay">
            <form class="registration-form" action="/register/applicant" method="post">
                <#if _csrf??>-->
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"-->
                    value="${(_csrf.token)!'csrf-token'}"/>-->
                </#if>
                <h2><@spring.message "registration.register"/></h2>
                <#if error??>
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <i class="fas fa-exclamation-circle"></i>
                        <div>${error}</div>
                    </div>
                </#if>
                <div class="mb-3">
                    <label for="name" class="form-label"><@spring.message "registration.name"/></label>
                    <@spring.formInput "userDto.name" 'id="name" class="form-control"' />
                    <@spring.showErrors "userDto.name" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label for="surname" class="form-label"><@spring.message "registration.lastName"/></label>
                    <@spring.formInput "userDto.surname" 'id="surname" class="form-control"' />
                    <@spring.showErrors "userDto.surname" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label"><@spring.message "registration.age"/></label>
                    <@spring.formInput "userDto.age" 'id="age" class="form-control"' />
                    <@spring.showErrors "userDto.age" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label"><@spring.message "registration.email"/></label>
                    <@spring.formInput "userDto.email" 'id="email" class="form-control"' />
                    <@spring.showErrors "userDto.email" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label"><@spring.message "registration.password"/></label>
                    <@spring.formPasswordInput "userDto.password" 'id="password" class="form-control"' />
                    <@spring.showErrors "userDto.password" "text-danger small" />
                </div>
                <div class="mb-3">
                    <label for="phoneNumber" class="form-label"><@spring.message "registration.phoneNumber"/></label>
                    <@spring.formInput "userDto.phoneNumber" 'id="phoneNumber" class="form-control"' />
                    <@spring.showErrors "userDto.phoneNumber" "text-danger small" />
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-yellow"><@spring.message "registration.register"/></button>
                </div>
                <div class="text-center mt-3">
                    <a href="/auth/login" class="text-decoration-none already-registered"><@spring.message "registration.alreadyRegister"/></a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</@main.layout>


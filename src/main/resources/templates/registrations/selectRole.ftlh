<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-overlay: rgba(18, 18, 18, 0.85);
            --text-light: #e0e0e0;
            --button-hover: #e6c200;
        }

        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            color: var(--text-light);
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
            padding: 3rem 2.5rem;
            margin-top: 105px;
            margin-bottom: 100px;
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

        .role-radio-group {
            display: flex;
            justify-content: space-around;
            margin-bottom: 1.5rem;
        }

        .role-radio-group label {
            background-color: var(--soft-yellow);
            color: black;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            display: inline-block;
        }

        .role-radio-group label:hover {
            background-color: var(--button-hover);
        }

        .role-radio-group input {
            display: none;
        }

        .role-radio-group input:checked + label {
            background-color: #e6c200;
        }

        .radio-buttons label {
            font-size: 1.1rem;
            color: var(--text-light);
        }

        .role-link {
            color: inherit;
            text-decoration: none;
        }
    </style>

    <body>
    <div class="bg-image">
        <div class="overlay">
            <form class="registration-form" action="/register" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <h2><@spring.message "selectARole"/></h2>

                <div class="role-radio-group">
                    <input type="radio" id="roleEmployer" name="role" value="employer">
                    <label for="roleEmployer">
                        <a href="/register/employer" class="role-link"><@spring.message "registration.employer"/></a>
                    </label>

                    <input type="radio" id="roleApplicant" name="role" value="applicant" checked>
                    <label for="roleApplicant">
                        <a href="/register/applicant" class="role-link"><@spring.message "registration.applicant"/></a>
                    </label>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</@main.layout>

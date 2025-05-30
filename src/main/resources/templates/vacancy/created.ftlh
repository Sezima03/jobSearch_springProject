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

        .vacancy-form {
            background-color: #1a1a1a;
            padding: 2rem 2.5rem;
            border-radius: 10px;
            border: 2px solid var(--soft-yellow);
            width: 100%;
            max-width: 500px;
            box-shadow: 0 0 20px rgba(255, 215, 0, 0.3);
        }

        .vacancy-form h2 {
            text-align: center;
            margin-bottom: 1.5rem;
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
            border: none;
        }

        .btn-yellow:hover {
            background-color: #e6c200;
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
    <div class="bg-image">
        <div class="overlay">
            <form class="vacancy-form" action="/created" method="POST" modelAttribute="vacancy">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>
                <h2><@spring.message "vacancy.created"/></h2>
                <div class="mb-3">
                    <label for="name" class="form-label"><@spring.message "jobSearch.vacancy.title"/></label>
                    <@spring.formInput "vacancy.name" 'id="name" class="form-control"' />
                    <@spring.showErrors "vacancy.name" "error text-danger" />
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label"><@spring.message "jobSearch.vacancy.description"/></label>
                    <@spring.formTextarea "vacancy.description" 'id="description" class="form-control"' />
                    <@spring.showErrors "vacancy.description" "error text-danger" />
                </div>

                <div class="mb-3">
                    <select name="categoryId" id="categoryId" class="form-control">
                        <option value=""><@spring.message "vacancy.created.category"/></option>
                        <option value=1>js</option>
                        <option value=2>java</option>
                        <option value=3>python</option>
                        <option value=4>project Manager</option>
                    </select>
                    <@spring.showErrors "vacancy.categoryId" "error text-danger" />
                </div>

                <div class="mb-3">
                    <label for="salary" class="form-label"><@spring.message "layout.nav.salary"/></label>
                    <@spring.formInput "vacancy.salary" 'id="salary" class="form-control" type="number"' />
                    <@spring.showErrors "vacancy.salary" "error text-danger" />
                </div>

                <div class="mb-3">
                    <label for="expFrom" class="form-label"><@spring.message "vacancy.expFrom"/></label>
                    <@spring.formInput "vacancy.expFrom" 'id="expFrom" class="form-control" type="number" min="0"' />
                </div>

                <div class="mb-3">
                    <label for="expTo" class="form-label"><@spring.message "vacancy.expTo"/></label>
                    <@spring.formInput "vacancy.expTo" 'id="expTo" class="form-control" type="number" min="0"' />
                </div>

                <label>
                    <input type="hidden" name="vacancy.isActive" value="false" /><@spring.message "vacancy.created.active"/>
                    <@spring.formCheckbox "vacancy.isActive" 'checked' />
                </label>

                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-yellow"><@spring.message "vacancy.created.save"/></button>
                </div>
                <div class="text-center mt-3">
                    <a href="/users/profileEmp" class="text-decoration-none already-registered"><@spring.message "vacancy.created.toVacancy"/></a>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</@main.layout>


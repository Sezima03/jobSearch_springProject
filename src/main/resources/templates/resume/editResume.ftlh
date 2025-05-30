<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-overlay: rgba(18, 18, 18, 0.85);
            --text-light: #e0e0e0;
        }

        body, html {
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

        .resume-form {
            background-color: #1a1a1a;
            padding: 2rem 2.5rem;
            border-radius: 10px;
            border: 2px solid var(--soft-yellow);
            width: 100%;
            max-width: 550px;
            box-shadow: 0 0 20px rgba(255, 215, 0, 0.3);
        }

        .resume-form h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: var(--soft-yellow);
        }

        .form-control::placeholder {
            color: #aaa;
        }

        fieldset {
            margin-top: 1rem;
            border: 1px solid #666;
            border-radius: 8px;
            margin-bottom: 20px;
            padding: 10px;
        }

        legend {
            color: var(--soft-yellow);
            font-weight: bold;
        }

        label {
            margin-top: 10px;
            margin-bottom: 5px;
            display: block;
            font-weight: normal;
            color: #ccc;
        }

        textarea {
            resize: vertical;
            min-height: 80px;
        }

        .btn-yellow {
            background-color: var(--soft-yellow);
            color: black;
            border: none;
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }

        .btn-yellow:hover {
            background-color: #e6c200;
        }

        .btn-reset {
            background-color: transparent;
            border: 1px solid #ccc;
            color: #ccc;
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            margin-top: 10px;
            cursor: pointer;
        }

        .btn-reset:hover {
            background-color: #333;
        }

    </style>
    <div class="bg-image">
        <div class="overlay">
            <form class="resume-form"  action="/resume/editResume/${resumes.id}" method="POST">

                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}"/>
                </#if>

                <h2><@spring.message "editeResume.title"/></h2>

                <div class="form-group">
                    <label for="name"><@spring.message "createResume.title"/></label>
                    <@spring.formInput "resumeDto.name" 'id="name" class="form-control form-control-sm"' />
                    <@spring.showErrors "resumeDto.name" "error text-danger" />
                </div>

                <div class="form-group">
                    <label for="categoryId"><@spring.message "createResume.category"/></label>
                    <select name="categoryId" id="categoryId" class="form-control form-control-sm">
                        <option value=1>java script</option>
                        <option value=2>java</option>
                        <option value=3>python</option>
                        <option value=4>project manager</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="salary"><@spring.message "createResume.salary"/></label>
                    <@spring.formInput "resumeDto.salary" 'id="salary" class="form-control form-control-sm"'/>
                    <@spring.showErrors "resumeDto.salary" "error text-danger" />
                </div>

                <div>
                    <h4>Образование</h4>
                    <div id="educationContainer"></div>
                    <button type="button" class="btn-yellow" onclick="addEducation()">Добавить образование</button>
                </div>

                <div>
                    <h4>Опыт работы</h4>
                    <div id="experienceContainer"></div>
                    <button type="button" class="btn-yellow" onclick="addExperience()">Добавить опыт</button>
                </div>

                <label><input type="checkbox" name="active" checked><@spring.message "createResume.published"/></label>

                <button type="submit" class="btn-yellow"><@spring.message "createResume.save"/></button>
                <a href="/users/profileApplicant" class="btn-reset" style="text-decoration: none; display: inline-block; text-align: center;">
                    <@spring.message "createResume.cancel"/>
                </a>
            </form>
        </div>
    </div>

    <script>

        let eduIndex = 0;
        let expIndex = 0;

        function addEducation() {
            const container = document.getElementById('educationContainer');
            const div = document.createElement('fieldset');
            div.innerHTML = `
                <div style="display: flex; align-items: center;">
                <label style="flex: 1;">Учебное заведение</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="text" name="educationInfo[${eduIndex}].institution" class="form-control form-control-sm" />

                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Программа</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="text" name="educationInfo[${eduIndex}].program" class="form-control form-control-sm" />


                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Дата начала</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="date" name="educationInfo[${eduIndex}].startDate" class="form-control form-control-sm" />


                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Дата окончания</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="date" name="educationInfo[${eduIndex}].endDate" class="form-control form-control-sm" />
            `;
            container.appendChild(div);
            eduIndex++;
        }

        function addExperience() {
            const container = document.getElementById('experienceContainer');
            const div = document.createElement('fieldset');
            div.innerHTML = `
                <div style="display: flex; align-items: center;">
                <label style="flex: 1;">Год</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="text" name="workExperienceInfo[${expIndex}].year" class="form-control form-control-sm" />

                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Компания</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="text" name="workExperienceInfo[${expIndex}].companyName" class="form-control form-control-sm" />

                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Должность</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <input type="text" name="workExperienceInfo[${expIndex}].position" class="form-control form-control-sm" />

                <div style="display: flex; align-items: center; margin-top: 10px;">
                <label style="flex: 1;">Обязанности</label>
                <button type="button" onclick="removeField(this)" style="margin-left: 5px; background: none; border: none; cursor: pointer;">
                <img src="https://img.icons8.com/m_rounded/512/FFFFFF/trash.png" alt="Удалить" width="20" height="20" />
                </button>
                </div>
                <textarea name="workExperienceInfo[${expIndex}].responsibilities" class="form-control form-control-sm"></textarea>
            `;
            container.appendChild(div);
            expIndex++;
        }

        addEducation();
        addExperience();

    </script>
</@main.layout>
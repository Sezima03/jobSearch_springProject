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
                    <@spring.showErrors "resumeDto.categoryId" "error text-danger" />
                </div>

                <div class="form-group">
                    <label for="salary"><@spring.message "createResume.salary"/></label>
                    <@spring.formInput "resumeDto.salary" 'id="salary" class="form-control form-control-sm"'/>
                    <@spring.showErrors "resumeDto.salary" "error text-danger" />
                </div>

                <fieldset>
                    <legend><@spring.message "createResume.education"/></legend>
                    <div class="form-group">
                        <label for="institution"><@spring.message "createResume.institution"/></label>
                        <@spring.formInput "educationfoDto.institution" 'id="institution" class="form-control form-control-sm"' />
                        <@spring.showErrors "educationfoDto.institution" "error text-danger" />
                    </div>

                    <div class="form-group">
                        <label for="program"><@spring.message "createResume.program"/></label>
                        <@spring.formInput "educationfoDto.program" 'id="program" class="form-control form-control-sm"' />
                        <@spring.showErrors "educationfoDto.program" "error text-danger" />
                    </div>
                    <div class="form-group">
                        <label for="startDate"><@spring.message "createResume.startdate"/></label>
                        <@spring.formInput "educationfoDto.startDate" 'id="startDate" type="date" class="form-control form-control-sm"' />
                        <@spring.showErrors "educationfoDto.startDate" "error text-danger" />
                    </div>

                    <div class="form-group">
                        <label for="endDate"><@spring.message "createResume.enddate"/></label>
                        <@spring.formInput "educationfoDto.endDate" 'id="endDate" type="date" class="form-control form-control-sm"' />
                        <@spring.showErrors "educationfoDto.endDate" "error text-danger" />
                    </div>
                </fieldset>

                <fieldset>
                    <legend>Опыт работы</legend>
                    <div class="for-group">
                        <label for="year"><@spring.message "createResume.year"/></label>
                        <@spring.formInput "work.year" 'id="year" class="form-control form-control-sm"' />
                        <@spring.showErrors "work.year" "error text-danger" />
                    </div>

                    <div class="form-group">
                        <label for="companyName"><@spring.message "createResume.titleCompany"/></label>
                        <@spring.formInput "work.companyName" 'id="companyName" class="form-control form-control-sm"' />
                        <@spring.showErrors "work.companyName" "error text-danger" />
                    </div>

                    <div class="form-group">
                        <label for="position"><@spring.message "createResume.position"/></label>
                        <@spring.formInput "work.position" 'id="position" class="form-control form-control-sm"' />
                        <@spring.showErrors "work.position" "error text-danger" />
                    </div>

                    <div class="form-group">
                        <label for="responsibilities"><@spring.message "createResume.responsibilites"/></label>
                        <textarea id="responsibilities" name="responsibilities" class="form-control form-control-sm"
                                  placeholder="<@spring.message 'responsibilites.placeholder' />"></textarea>
                    </div>
                </fieldset>

                <label><input type="checkbox" name="active" checked><@spring.message "createResume.published"/></label>

                <button type="submit" class="btn-yellow"><@spring.message "createResume.save"/></button>
                <a href="/users/profileApplicant" class="btn-reset" style="text-decoration: none; display: inline-block; text-align: center;">
                    <@spring.message "createResume.cancel"/>
                </a>
            </form>
        </div>
    </div>
</@main.layout>
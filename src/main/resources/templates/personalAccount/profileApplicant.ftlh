<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <link rel="stylesheet" href="/static/css/style.css">
    <div class="profile-box">
        <h3 class="text-danger mb-4"><@spring.message "profileApplicant.forApplicant"/></h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2" style="margin-right: 95px">

                <div class="container mt-5 text-center">
                    <form action="/images/upload" method="post" enctype="multipart/form-data">

                        <div class="upload-box mx-auto"
                             style="background-image: url('<#if userImageDto??>/images/by-name/${userImageDto}</#if>');">
                            <label for="file">
                                <span>Нажмите, чтобы выбрать файл</span>
                            </label>
                            <input type="file" name="file" id="file" accept="image/*" required>
                        </div>

                        <input type="hidden" name="userId" value="${user.id}">
                        <button type="submit" class="btn btn-success mt-3" style="margin-left: 35px">Загрузить</button>
                    </form>
                </div>
            </div>
            <div class="col-4 text-start">
                <h5>${user.name}</h5>
                <p>${user.age}</p>
                <a href="/users/updateProfile/${user.id}" class="btn btn-edit btn-sm px-4"><@spring.message "profileApplicant.edit"/></a>
            </div>
            <div class="col-2">
                <a href="/resume/create" class="btn btn-create rounded-3">+<br><@spring.message "profileApplicant.create"/><br><@spring.message "profileApplicant.resumes"/></a>
            </div>
            <div class="col-2 position-relative">
                <a href="/" class="btn btn-response rounded-3"><@spring.message "profileApplicant.responses"/></a>
                <span class="badge-response">${count}</span>
            </div>
        </div>

        <h5 class="text-start text-white"><@spring.message "profileApplicant.resumes"/></h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list resumes as resume>
                <div class="resume-card p-3 text-white">
                    <p class="fw-bold mb-2">${resume.name}</p>
                    <p class="text-white mb-3"><@spring.message "profileApplicant.dateupdate"/><br><span>${resume.updateTime}</span></p>
                    <div class="d-flex justify-content-center gap-3">
                        <form action="/resume/updateDate/${resume.id}" method="post">
                            <button type="submit" class="btn btn-primary btn-sm px-4"><@spring.message "profileApplicant.update"/></button>
                        </form>
                        <a href="/resume/editResume/${resume.id}" class="btn btn-edit btn-sm px-4"><@spring.message "profileApplicant.edit"/></a>
                    </div>
                </div>
            </#list>
        </div>

    </div>
</@main.layout>


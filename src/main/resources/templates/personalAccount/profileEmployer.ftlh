<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <link rel="stylesheet" href="/static/css/vacancyStyle.css">
    <div class="profile-box">
        <h3 style="color: #bfa500; margin-bottom: 35px" class="mb-4"><@spring.message "profile.employer"/></h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2" style="margin-top: 150px !important; margin-bottom: 50px !important; margin-left: 50px !important;">
            <div class="container mt-5 text-center">

                <form action="/images/uploadForEmployer" method="post" enctype="multipart/form-data">

                    <div class="upload-box mx-auto"
                         style="background-image: url('/images/by-name/${userImageDto}')">
                        <label for="file">
                            <span><@spring.message "image.down.click"/></span>
                        </label>
                        <input type="file" name="file" id="file" accept="image/*" required>
                    </div>

                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit" class="btn btn-success mt-3" style="margin-left: 40px;  background: linear-gradient(135deg, #ff7b7b, #7b99ff); color: #0e0620"><@spring.message "image.down"/></button>
                </form>
            </div>
            </div>

            <div class="col-4" style="margin-left: auto">
                <h5>${user.companyName}</h5>
                <a href="/users/updateProfileEmployer/${user.id}" class="btn btn-edit"><@spring.message "profile.employer.updateProfile"/></a>
            </div>
            <div class="col-4">
                <a href="/created" class="btn btn-red rounded-pill p-3" style="margin-left: 50px; width: 110px; height: 110px; background: linear-gradient(135deg, #ff7b7b, #7b99ff);">
                    +<br><@spring.message "profile.employer.create"/><br><@spring.message "profile.employer.createVacancy"/>
                </a>

                <a href="/resume/resumes" class="btn btn-red rounded-pill p-3" style="margin-left: 80px; width: 110px; height: 110px; background: linear-gradient(135deg, #ff7b7b, #7b99ff);">
                    <br><@spring.message "profile.employer.sehen"/> <br><@spring.message "profile.employer.sehenResume"/>
                </a>
            </div>
        </div>



        <h5 class="text-start"><@spring.message "profile.employer.AllVacancy"/></h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3" style="margin-top: 25px !important;">
            <#list vacancies as vacancy>
                <div class="vacancy-card p-3">
                    <p class="fw-bold mb-2" style="color: #ffd700;">${vacancy.name}</p>
                    <p class="mb-3" style="color: white"><@spring.message "profileApplicant.dateupdate"/><br><span>${vacancy.updateTime}</span></p>
                    <div class="d-flex justify-content-center gap-3">
                        <form action="/updateDate/${vacancy.id}" method="post">
                            <button type="submit" class="btn btn-primary btn-sm px-4" style="width: auto; text-align: center; min-width: 130px"><@spring.message "profile.employer.updateTimeDate"/></button>
                        </form>
                        <a href="/edit/${vacancy.id}" class="btn btn-edit btn-sm px-4"><@spring.message "profile.employer.updateProfile"/></a>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <div class="modal fade" id="editCompanyModal" tabindex="-1" aria-labelledby="editCompanyModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCompanyModalLabel">Редактировать название компании</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
            </div>
        </div>
    </div>
</@main.layout>



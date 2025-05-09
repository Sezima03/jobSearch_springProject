<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>
<@main.layout>
    <h3 class="mb-4 text-center text-warning"><@spring.message "jobSearch.updateProfile"/></h3>

    <form method="post" action="/users/updateProfileEmployer/${user.id}">
        <div class="mb-3">
            <label for="companyName" class="form-label"><@spring.message "registration.nameCompany"/></label>
            <input type="text" id="companyName" name="companyName" value="${user.companyName}" class="form-control">
        </div>

        <div class="mb-3">
            <label for="name" class="form-label"><@spring.message "registration.name"/></label>
            <input type="text" id="name" name="name" value="${user.name}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="surname" class="form-label"><@spring.message "registration.lastName"/></label>
            <input type="text" id="surname" name="surname" value="${user.surname}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="age" class="form-label"><@spring.message "registration.age"/></label>
            <input type="number" id="age" name="age" value="${user.age}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label"><@spring.message "registration.email"/></label>
            <input type="email" id="email" name="email" value="${user.email}" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="phoneNumber" class="form-label"><@spring.message "registration.phoneNumber"/></label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-warning"><@spring.message "jobSearch.saveChanges"/></button>
        <a href="/users/profileEmp" class="btn btn-secondary"><@spring.message "createResume.cancel"/></a>
    </form>
</@main.layout>

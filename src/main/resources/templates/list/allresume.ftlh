<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="col-12">
        <table class="table table-image">
            <thead>
            <tr>
                <th scope="col">applicant ID</th>
                <th scope="col">Название резюме</th>
                <th scope="col">Category ID</th>
                <th scope="col">Salary</th>
                <th scope="col">created date</th>
                <th scope="col">update time</th>
            </tr>
            </thead>
            <tbody>
            <#list resumes as resume>
                <tr>
                    <td>${resume.applicantId}</td>
                    <td>${resume.name}</td>
                    <td>${resume.categoryId}</td>
                    <td>${resume.salary}</td>
                    <td>${resume.createdDate}</td>
                    <td>${resume.updateTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@main.layout>
<#import "../layout.ftlh" as main>
<@main.layout>

    <div class="col-12">
        <table class="table table-image">
            <thead>
            <tr>
                <th scope="col">name</th>
                <th scope="col">description</th>
                <th scope="col">Category ID</th>
                <th scope="col">Salary</th>
                <th scope="col">exp_from</th>
                <th scope="col">exp_to</th>
                <th scope="col">author ID</th>
                <th scope="col">created date</th>
                <th scope="col">update time</th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <td>${vacancy.name}</td>
                    <td>${vacancy.description}</td>
                    <td>${vacancy.categoryId}</td>
                    <td>${vacancy.salary}</td>
                    <td>${vacancy.expFrom}</td>
                    <td>${vacancy.expTo}</td>
                    <td>${vacancy.authorId}</td>
                    <td>${vacancy.createdDate}</td>
                    <td>${vacancy.updateTime}</td>
                </tr>

            </tbody>
        </table>
    </div>
</@main.layout>
<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="col-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Описание</th>
                <th scope="col">Категория</th>
                <th scope="col">Зарплата</th>
                <th scope="col">Опыт от</th>
                <th scope="col">Опыт до</th>
                <th scope="col">Автор</th>
                <th scope="col">Дата создания</th>
                <th scope="col">Дата обновления</th>
                <th scope="col"></th>
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
                <td>
                    <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#responseModal" data-vacancy-id="${vacancy.id}">
                        Откликнуться
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</@main.layout>
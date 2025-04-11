<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет Работодателя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-box {
            border: 2px solid black;
            border-radius: 20px;
            padding: 30px;
            margin-top: 30px;
            text-align: center;
        }
        .vacancy-card {
            border: 2px solid black;
            border-radius: 15px;
            padding: 15px;
            min-width: 200px;
        }
        .btn-red {
            background-color: #f8d7da;
            border: 1px solid red;
        }
        .btn-edit {
            background-color: #e0f0c0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="profile-box">
        <h3 class="text-danger mb-4">Личный кабинет Работодателя</h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2">
                <img src="api/images/images/1" alt="Company" class="img-fluid">
            </div>
            <div class="col-4">
                <h5>Company name</h5>
                <button class="btn btn-edit">Редактировать</button>
            </div>
            <div class="col-4">
                <button class="btn btn-red rounded-pill p-3">+<br>Создать<br>резюме</button>
            </div>
        </div>

        <h5 class="text-start">Созданные вакансии:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list vacancies as vacancy>
                <div class="vacancy-card">
                    <p>${vacancy.name}</p>
                    <p>Дата обновления:<br>vacancy.updateTime</p>
                    <button class="btn btn-primary btn-sm">Обновить</button>
                    <button class="btn btn-edit btn-sm">Ред.</button>
                </div>
            </#list>
        </div>
    </div>
</div>
</body>
</html>


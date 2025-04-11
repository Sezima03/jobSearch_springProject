<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет Соискателя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-box {
            border: 2px solid black;
            border-radius: 20px;
            padding: 30px;
            margin-top: 30px;
            text-align: center;
        }
        .resume-card {
            border: 2px solid black;
            border-radius: 15px;
            padding: 15px;
            min-width: 200px;
        }
        .btn-edit {
            background-color: #e0f0c0;
        }
        .btn-create {
            background-color: #f8d7da;
            border: 1px solid red;
            padding: 12px;
        }
        .btn-response {
            border: 1px solid black;
            position: relative;
            padding: 20px;
        }
        .badge-response {
            position: absolute;
            top: -10px;
            right: 55px;
            background-color: red;
            color: white;
            border-radius: 50%;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="profile-box">
        <h3 class="text-danger mb-4">Личный кабинет Соискателя</h3>
        <div class="row align-items-center justify-content-center mb-4">
            <div class="col-2">
                <img src="api/images/images/1" alt="User" class="img-fluid">
            </div>
            <div class="col-4 text-start">
                <h5>Иван ИВанов</h5>
                <p>30</p>
                <button class="btn btn-edit">Редактировать</button>
            </div>
            <div class="col-2">
                <button class="btn btn-create rounded-3">+<br>Создать<br>резюме</button>
            </div>
            <div class="col-2 position-relative">
                <button class="btn btn-response rounded-3">Отклики</button>
                <span class="badge-response">5</span>
            </div>
        </div>

        <h5 class="text-start">Резюме:</h5>
        <div class="d-flex justify-content-around flex-wrap gap-3 mt-3">
            <#list resumes as resume>
            <div class="resume-card">
                    <div class="resume-card">
                        <p>${resume.name}</p>
                        <p>Дата обновления:<br>${resume.updateTime}</p>
                        <button class="btn btn-primary btn-sm">Обновить</button>
                        <button class="btn btn-edit btn-sm">Ред.</button>
                    </div>
            </div>
            </#list>
        </div>
    </div>
</div>
</body>
</html>
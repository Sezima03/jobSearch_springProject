<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Загрузка фотографии</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .upload-box {
            width: 200px;
            height: 200px;
            border: 2px dashed #ccc;
            border-radius: 10px;
            position: relative;
            overflow: hidden;
            background-color: #f9f9f9;
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .upload-box input[type="file"] {
            opacity: 0;
            position: absolute;
            width: 100%;
            height: 100%;
            cursor: pointer;
        }

        .upload-box label {
            position: absolute;
            width: 100%;
            height: 100%;
            cursor: pointer;
        }

        .upload-box span {
            color: #888;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container mt-5 text-center">
    <h3 class="mb-4 text-primary">Загрузка фотографии</h3>

    <form action="/images/upload" method="post" enctype="multipart/form-data">

        <div class="upload-box mx-auto"
             style="background-image: url('<#if userImageDto??>/images/by-name/${userImageDto}<#else>/css/ava.png</#if>');">
            <label for="file">
                <span>Нажмите, чтобы выбрать файл</span>
            </label>
            <input type="file" name="file" id="file" accept="image/*" required>
        </div>

        <input type="hidden" name="userId" value="1">
        <button type="submit" class="btn btn-success mt-3">Загрузить</button>
    </form>
</div>
</body>
</html>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Загрузка фотографии</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4 text-primary">Загрузка фотографии</h3>

    <form action="/images/upload" method="post" enctype="multipart/form-data">
    <div class="mb-3">
            <label for="file" class="form-label">Выберите изображение:</label>
            <input type="file" class="form-control" id="file" name="file" accept="image/*" required>
        </div>

        <input type="hidden" name="userId" value="1">

        <button type="submit" class="btn btn-success">Загрузить</button>
    </form>

    <#if userImageDto??>
        <div class="mt-4">
            <h5>Текущая фотография:</h5>
            <img src="/images/by-name/${userImageDto}" alt="Аватарка" class="img-thumbnail" width="200">
        </div>
    </#if>

</div>
</body>
</html>

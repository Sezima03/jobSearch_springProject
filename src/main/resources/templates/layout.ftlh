<#import "spring.ftl" as spring>
<#macro layout>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" />
    <title>Job Search</title>
    <style>
        :root {
            --soft-yellow: #ffd700;
            --dark-bg: #121212;
            --text-light: #e0e0e0;
            --text-muted: #888888;
        }

        body {
            background-color: var(--dark-bg);
            color: var(--text-light);
        }

        .custom-navbar {
            border: 2px solid var(--soft-yellow);
            background-color: #1a1a1a;
            border-radius: 8px;
            margin-top: 1rem;
        }

        .navbar-brand,
        .nav-link {
            color: var(--soft-yellow) !important;
        }

        .nav-link.disabled {
            color: var(--text-muted) !important;
        }

        .form-control {
            background-color: #2c2c2c;
            border: 1px solid #444;
            color: var(--text-light);
        }

        .form-control::placeholder {
            color: #aaa;
        }

        .btn-outline-warning {
            border-color: var(--soft-yellow);
            color: var(--soft-yellow);
        }

        .btn-outline-warning:hover {
            background-color: var(--soft-yellow);
            color: #000;
        }

        .custom-alert {
            background-color: #1a1a1a;
            border: 2px solid transparent;
            color: var(--text-light);
            border-radius: 8px;
            padding: 1.5rem;
            margin-top: 2rem;
            transition: border-color 0.3s ease;
        }

        .custom-alert:hover {
            border-color: var(--soft-yellow);
        }

        .job-info {
            margin-bottom: 0.5rem;
            font-size: 1rem;
        }

        .job-label {
            font-weight: 500;
            color: var(--soft-yellow);
            margin-right: 0.5rem;
        }

        .dropdown-item i {
            margin-right: 8px;
        }
    </style>
</head>

<body>
<div class="container">
    <nav class="navbar navbar-expand-lg custom-navbar" data-bs-theme="dark">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="/">Job Search</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/">Вакансии</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Личный кабинет
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark bg-dark border border-warning">
                            <li><a class="dropdown-item text-light" href="/register/applicant">Соискатель</a></li>
                            <li><a class="dropdown-item text-light" href="/register/employer">Работодатель</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                    <button class="btn btn-outline-warning" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="custom-alert">
        <#nested spring>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>

</#macro>

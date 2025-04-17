<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/static/css/styles.css">
    <title>Document</title>
</head>
<body>
<section class=" text-center text-lg-start">

    <style>

        .rounded-t-5 {

            border-top-left-radius: 0.5rem;

            border-top-right-radius: 0.5rem;

        }


        @media (min-width: 992px) {

            .rounded-tr-lg-0 {

                border-top-right-radius: 0;

            }


            .rounded-bl-lg-5 {

                border-bottom-left-radius: 0.5rem;

            }

        }

    </style>

    <div class="card m-3">

        <div class="row g-0 d-flex align-items-center">

            <div class="col-lg-4 d-none d-lg-flex">

                <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"

                     class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5"/>

            </div>

            <div class="col-lg-8">

                <div class="card-body py-5 px-md-5">


                    <form id="login-form" action="/auth/login" method="post">

                        <!-- Email input -->

                        <div data-mdb-input-init class="form-outline mb-4">

                            <input type="email" id="form2Example1" class="form-control" name="username"/>

                            <label class="form-label" for="form2Example1">Email address</label>

                        </div>


                        <!-- Password input -->

                        <div data-mdb-input-init class="form-outline mb-4">

                            <input type="password" id="form2Example2" class="form-control" name="password"/>

                            <label class="form-label" for="form2Example2">Password</label>

                        </div>

                        <#if error??>
                            <div class="alert alert-danger">${error}</div>
                        </#if>

                        <!-- Submit button -->

                        <button type="submit" data-mdb-button-init data-mdb-ripple-init

                                class="btn btn-primary btn-block mb-4">Sign in

                        </button>


                    </form>


                </div>

            </div>

        </div>

    </div>

</section>
</body>
</html>
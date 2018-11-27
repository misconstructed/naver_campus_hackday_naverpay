<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


<script>
    function login() {
        var id = document.getElementById("id").value;
        var password = document.getElementById("password").value;

        var data = {
            "id":id,
            "password":password
        };

        $.ajax({
            url : "/login",
            type : "POST",
            contentType: "application/json",
            data : JSON.stringify(data),
            success : function (data){
                if(data.status == "success") {
                    location.href="/main";
                } else {
                    alert("login failed");
                }
            },
            error : function(data, textStatus, errorThrown) {

            }
        });
    }
</script>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<!--
<style>
    body {
        background-color: #1AC800;
    }
</style>
-->

<body>
    <div class="container" style="margin-top:10% " align="center">
        <div class="col-md-4" > </div>
        <div class="col-md-4" >
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Sign In</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="ID" name="id" id="id" type="id" autofocus="">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" id="password" type="password" value="">
                            </div>
                            <p align="left"><a href="/signup">Create account.</a></p>
                            <a href="javascript:login();" class="btn btn-sm btn-success" style="width: 40%">Login</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-4" > </div>
    </div>
</body>

</html>
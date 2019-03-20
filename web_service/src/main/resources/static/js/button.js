
//로그인 버튼 클릭 시 호출
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
                alert("로그인 실패");
            }
        },
        error : function(data, textStatus, errorThrown) {

        }
    });
}

//회원가입 완료 버튼을 클릭한 경우 호출
function signup() {
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;
    var passwordCheck = document.getElementById("passwordCheck").value;
    var name = document.getElementById("name").value;
    var state = "0";

    var data = {
        "id":id,
        "password":password,
        "name":name,
        "state":state
    };

    if(password != passwordCheck)
        alert("password incorrect!");
    else {
        $.ajax({
            url : "/register",
            type : "POST",
            contentType: "application/json",
            data : JSON.stringify(data),
            success : function (data){
                if(data.status == "success") {
                    alert("success");
                    location.href="/main";
                } else {
                    alert("fail");
                }
            },
            error : function(data, textStatus, errorThrown) {

            }
        });

    }
}

//결제 품목 TOP5 필터링하는 경우
function searchProduct() {
    var branchSelect = document.getElementById("branchSelect");
    var branchName = branchSelect.options[branchSelect.selectedIndex].value;
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var day = document.getElementById("day").value;

    if(month.length == 1)
        month = "0" + month;
    if(day.length == 1)
        day = "0" + day;

    if(year == "") {
        $.ajax({
            url : "/product",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else if(month == "") {
        $.ajax({
            url : "/product",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else if(day == "") {
        $.ajax({
            url : "/product",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year,
                month : month
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else {
        $.ajax({
            url : "/product",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year,
                month : month,
                day : day
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    }
}

//첫 화면에서 전체 통계를 필터링 하는 경우
function search() {
    var branchSelect = document.getElementById("branchSelect");
    var branchName = branchSelect.options[branchSelect.selectedIndex].value;
    //alert(branchName);

    $.ajax({
        url : "/main",
        type : "GET",
        contentType: "application/json",
        data : {
            branchName : branchName
        },
        success : function (data){
            //location.reload();
            var result = $('<div />').append(data).find('#table').html();
            $('#table').html(result);
        },
        error : function(data, textStatus, errorThrown) {
            alert("error");
            console.log(data);
        }
    });
}

//가맹점별 필터링 하는 경우
function searchBranch() {
    var branchSelect = document.getElementById("branchSelect");
    var branchName = branchSelect.options[branchSelect.selectedIndex].value;
    var year = document.getElementById("year").value;
    var month = document.getElementById("month").value;
    var day = document.getElementById("day").value;

    if(month.length == 1)
        month = "0" + month;
    if(day.length == 1)
        day = "0" + day;

    if(year == "") {
        $.ajax({
            url : "/branch",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else if(month == "") {
        $.ajax({
            url : "/branch",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else if(day == "") {
        $.ajax({
            url : "/branch",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year,
                month : month
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    } else {
        $.ajax({
            url : "/branch",
            type : "GET",
            contentType: "application/json",
            data : {
                branchName : branchName,
                year : year,
                month : month,
                day : day
            },
            success : function (data){
                //location.reload();
                var result = $('<div />').append(data).find('#table').html();
                $('#table').html(result);
            },
            error : function(data, textStatus, errorThrown) {
                alert("error");
                console.log(data);
            }
        });
    }
}

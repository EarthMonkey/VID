/**
 * Created by TQ on 17/01/11.
 */

// 0切换到登录；1切换到忘记密码
function change(syb) {
    Ids = ["login", "forget_password"];
    var current = (syb + 1) % 2;
    $("#" + Ids[current]).hide();
    showDiv(Ids[syb]);
}

function showDiv(elem_id) {
    $("#" + elem_id).fadeIn("slow");
}

var err_lbl = document.createElement("div");
err_lbl.className = "err_lbl";

// 登录
function login() {

    var name_field = $(".textfield")[0];
    var username = name_field.value;
    if (username == "") {
        err_lbl.innerHTML = "请输入用户名";
        name_field.parentNode.appendChild(err_lbl);
        $(name_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    var pwd_field = $(".textfield")[1];
    var pwd = pwd_field.value;
    if (pwd == "") {
        err_lbl.innerHTML = "请输入密码";
        pwd_field.parentNode.appendChild(err_lbl);
        $(pwd_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    $.ajax({
        type: "POST",
        url: SERVER_IP + "/login",
        async: false,
        data: {
            id: username,
            password: pwd
        },
        success: function (data) {

            if (data.status == true) {

                // location.href = "Homepage.html"

                isLogin()

            } else {
                if (data.info == "密码错误") {
                    err_lbl.innerHTML = "密码错误";
                    pwd_field.parentNode.appendChild(err_lbl);
                    $(pwd_field).focus(function () {
                        this.parentNode.removeChild(err_lbl);
                    });
                } else {
                    err_lbl.innerHTML = data.info;
                    name_field.parentNode.appendChild(err_lbl);
                    $(name_field).focus(function () {
                        this.parentNode.removeChild(err_lbl);
                    });
                }
            }
        },
        error: function () {
            alert("登录失败");
            // console.log(xhr);
            // console.log(status);
            // console.log(error);
        }
    });
}

function isLogin() {

    $.ajax({
        type: "POST",
        url: SERVER_IP + "/isLogin",
        async: false,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function (data) {
            alert(data.status + " info: " + data.info + " name:" + data.object);
        },
        error: function () {
            alert("登录失败");
            // console.log(xhr);
            // console.log(status);
            // console.log(error);
        }
    });

    // var xhr = sendXML(SERVER_IP + "/isLogin", "POST", "");
    // xhr.onreadystatechange = function () {
    //     if (xhr.readyState == 4 && xhr.status == 200) {
    //         var data = xhr.response;
    //         alert(data.status + " info: " + data.info + " name:" + data.object);
    //         if (data.status == true) {
    //             $($(".nav_username_div")[1]).hide();
    //             $($(".nav_username_div")[0]).show();
    //             $($(".nav_username_div")[0]).find("span").html(data.info);
    //         }
    //     }
    // }

}

// 注册
function register() {

    var email_field = $(".textfield")[0];
    var email = email_field.value;
    if (email == "") {
        err_lbl.innerHTML = "请输入邮箱或手机号";
        email_field.parentNode.appendChild(err_lbl);
        $(email_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    var pwd_field = $(".textfield")[1];
    var pwd = pwd_field.value;
    if (pwd == "") {
        err_lbl.innerHTML = "请输入密码";
        pwd_field.parentNode.appendChild(err_lbl);
        $(pwd_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    var pwd2_field = $(".textfield")[2];
    var pwd2 = pwd2_field.value;
    if (pwd2 !== pwd) {
        err_lbl.innerHTML = "两次密码不一致";
        pwd2_field.parentNode.appendChild(err_lbl);
        $(pwd2_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    var name_field = $(".textfield")[3];
    var name = name_field.value;
    if (name == "") {
        err_lbl.innerHTML = "请输入名字";
        name_field.parentNode.appendChild(err_lbl);
        $(name_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    $.ajax({
        type: "POST",
        url: SERVER_IP + "/register",
        async: false,
        data: {
            email: email,
            phoneNum: '',
            password: pwd,
            name: name
        },
        success: function (data) {

            if (data.status == true) {
                $("#successModal").fadeIn();
                $("#successModal").find("span").html(email);
            } else {
                err_lbl.innerHTML = data.info;
                email_field.parentNode.appendChild(err_lbl);
                $(email_field).focus(function () {
                    this.parentNode.removeChild(err_lbl);
                });
            }
        },
        error: function () {
            alert("注册失败");
            // console.log(xhr);
            // console.log(status);
            // console.log(error);
        }
    });
}

// 忘记密码发送
function forget() {

    var email_field = $(".textfield")[2];
    var email = email_field.value;
    if (email == "") {
        err_lbl.innerHTML = "请输入邮箱地址";
        email_field.parentNode.appendChild(err_lbl);
        $(email_field).focus(function () {
            this.parentNode.removeChild(err_lbl);
        });
        return;
    }

    $.ajax({
        type: "POST",
        url: SERVER_IP + "/findPass",
        async: false,
        data: {
            id: email
        },
        dataType: "json",
        success: function (data) {

            if (data.status == true) {
                $("#successModal").fadeIn();
            } else {

                err_lbl.innerHTML = data.info;
                email_field.parentNode.appendChild(err_lbl);
                $(email_field).focus(function () {
                    this.parentNode.removeChild(err_lbl);
                });
            }
        },
        error: function () {
            alert("发送邮件失败");
            // console.log(xhr);
            // console.log(status);
            // console.log(error);
        }
    });
}

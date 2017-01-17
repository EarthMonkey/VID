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

    err_lbl.innerHTML = "密码错误";
    pwd_field.parentNode.appendChild(err_lbl);
    $(pwd_field).focus(function () {
        this.parentNode.removeChild(err_lbl);
    });

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

    err_lbl.innerHTML = "邮箱或手机无效";
    email_field.parentNode.appendChild(err_lbl);
    $(email_field).focus(function () {
        this.parentNode.removeChild(err_lbl);
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

    err_lbl.innerHTML = "邮箱地址无效";
    email_field.parentNode.appendChild(err_lbl);
    $(email_field).focus(function () {
        this.parentNode.removeChild(err_lbl);
    });
}
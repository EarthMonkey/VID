/**
 * Created by L.H.S on 2017/2/23.
 */

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/*
 * 邮箱验证
 * */
function verify() {

    var data = "userID=" + getParameter("userID") + "&random=" + getParameter("random");
    var xhr = sendXML("/activate", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.response;
            if (result.status == true) {
                gotoTime($(".goto_btn").find("span"));
            }
        }
    }
}

function gotoTime(span) {

    setInterval(function () {
        var second = span.html();
        if (second > 0) {
            second--;
            span.html(second);
        } else {
            gotoHomePage();
        }
    }, 1000);

    $(".goto_btn").click(function () {
        gotoHomePage();
    });
}

function gotoHomePage() {
    location.href = "Homepage.html";
}


/*
 * 重置密码
 * */
function checkEmail() {

    var data = "userID=" + getParameter("userID") + "&random=" + getParameter("random");
    var xhr = sendXML("/verifyMail/findPass", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState==4 && xhr.status==200) {
            var resp = xhr.response;
            if (resp.status == true) {
                $("#invalidDiv").hide();
                $("#validDiv").show();
            } else {
                console.log(resp);
            }
        }
    }

}

var ERR_LBL = $("<div class='err_lbl' style='line-height: 40px;'></div>");
function resetPwd() {

    var pwd = $(".modal_pwd").find("input")[0];
    var pwd2 = $(".modal_pwd").find("input")[1];

    if ($(pwd).val() == "") {
        ERR_LBL.html("请输入密码");
        $(pwd).parent("div").append(ERR_LBL);

        $(pwd).focus(function () {
            ERR_LBL.remove();
        });

        return;
    }

    if ($(pwd2).val() != $(pwd).val()) {
        ERR_LBL.html("两次密码不一致");
        $(pwd2).parent("div").append(ERR_LBL);

        $(pwd2).focus(function () {
            ERR_LBL.remove();
        });

        return;
    }

    var data = "password=" + pwd;
    var xhr = sendXML("/resetPass", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;
            if (resp.status == true) {
                $("#resetDiv").hide();
                $("#successDiv").slideDown();
                gotoTime($("#successDiv").find(".goto_btn").find("span"));
            } else {
                alert(resp.info);
            }
        }
    };
}
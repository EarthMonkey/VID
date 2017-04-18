/**
 * Created by L.H.S on 2017/4/16.
 */

var VIDEO_ID;  // 用来存储videoId
var NOTE_NAME; // 用来存储name

window.onload = function () {
    judgeLogin();
};

// 判断是否已登录
function judgeLogin() {

    var xhr = sendXML("/isLogin", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = xhr.response;

            if (data.status == true) {
                // 已登录
                var usernameDiv = $(".nav_username_div");
                $(usernameDiv).eq(1).hide();
                $(usernameDiv).eq(0).show();
                $(usernameDiv).eq(0).find("span").html(data.object);

                $(".tip_div").hide();
                $(".add_contact").show();

                getVideoInfo(2);
            } else {
                // 未登录
                getVideoInfo(1);
            }
        }
    }
}

// 获取地址栏videoId
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

// 1，未登录；2，已登录
function getVideoInfo(loginState) {

    var videoID = getParameter("videoId");
    alert(videoID);
    var data = "videoID=" + videoID;

    var xhr = sendXML("/video/info/" + loginState, "POST", data);
    xhr.onreadystatechange = function () {

        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;

            if (resp.status) {
                setVideoInfo(resp.object);
            } else {
                alert(resp.info);
            }
        }
    };
}

function setVideoInfo(videoInfo) {

    VIDEO_ID = videoInfo.id;

    // 填充视频信息
    var data = "contactID=" + videoInfo.ownerid;
    var xhr = sendXML("/profile/contact", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;
            if (resp.status) {
                var owner = resp.object;

                console.log(owner)
                $(".portrait_div").find("img").attr("src", owner.portrait);
                $(".contact_name").html(owner.name);
                var myInfo = $(".contact_info").find("span");
                $(myInfo).eq(0).html(owner.note);  // 备注
                $(myInfo).eq(1).html(owner.phoneNum);
                $(myInfo).eq(2).html(owner.mail);

                NOTE_NAME = owner.name;
            }
        }
    };

    $(".video_name").html(videoInfo.name);
    $("video").find("source").attr("src", videoInfo.url);
}

function logout() {
    var xhr = sendXML("/logout", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = xhr.response;
            if (data.status == true) {
                location.reload();
            } else {
                alert(data.info);
            }
        }
    }
}

function videoLogin() {

    $(".modal_backdrop").show();
    $("#loginModal").slideDown();
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

    var data = "id=" + username + "&password=" + pwd;
    var xhr = sendXML("/login", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = xhr.response;
            if (data.status == true) {
                location.reload();

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
        }
    };
}

function addContact() {

    var data = "name=" + NOTE_NAME + "&videoID=" + VIDEO_ID;
    var xhr = sendXML("/contacts/new/video", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;

            var remindInfo;
            if (resp.status) {
                remindInfo = "添加成功";
            } else {
                remindInfo = resp.info;
            }

            var remind = $(".remind_div");
            $(remind).html(remindInfo);
            $(remind).slideDown("fast");
            setTimeout("$('.remind_div').slideUp()", 2000);
        }
    }
}
/**
 * Created by L.H.S on 2017/2/7.
 */

function changeDot(index) {
    if (index == 0) {
        $($(".each_dot")[1]).css("background-color", "#fff");
        $($(".each_dot")[0]).css("background-color", "#2b2b2b");
        $(".introduce_text").find("img").attr("src", "../image/introduce_text_white.png");
        $(".img_right").css("z-index", -10);
        $(".dot_div").css("left", "46%");
    } else {
        $($(".each_dot")[0]).css("background-color", "#fff");
        $($(".each_dot")[1]).css("background-color", "#2b2b2b");
        $(".introduce_text").find("img").attr("src", "../image/introduce_text_black.png");
        $(".dot_div").css("left", "48.5%");
        $(".img_right").css("z-index", 0);
    }
}

var LAST_AD = 0;
var LEFT_ABLE = 0;
var RIGHT_ABLE = 0;
function changeAd(index) {
    var ads = $(".each_ad");
    var dots = $(".ad_dot").find(".each_dot");

    $(ads[LAST_AD]).hide();
    $(ads[index]).animate({
        width: "show"
    }, 200);
    $(ads[index]).css("display", "inline-block");

    $(dots[LAST_AD]).css("background-color", "#fff");
    $(dots[index]).css("background-color", "#2b2b2b");

    LAST_AD = index;
}

function slideCheck() {
    $(".part_one").on("swipeleft", function () {
        changeDot(1);
    });
    $(".part_one").on("swiperight", function () {
        changeDot(0);
    });

    $(".part_three").bind("swipeleft", function () {
        if ($(".ad_dot").css("display") != "none" && (LAST_AD + 1) < 5) {
            LEFT_ABLE = (LEFT_ABLE + 1) % 2;
            if (LEFT_ABLE == 1) {
                changeAd(LAST_AD + 1);
            }
        }
    });
    $(".part_three").on("swiperight", function () {
        if ($(".ad_dot").css("display") != "none" && (LAST_AD - 1) > -1) {
            RIGHT_ABLE = (RIGHT_ABLE + 1) % 2;
            if (RIGHT_ABLE == 1) {
                changeAd(LAST_AD - 1);
            }
        }
    });
}

function judgeLogin() {

    var xhr = sendXML("/isLogin", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = xhr.response;
            loginState = data.status;

            if (data.status == true) {
                $($(".nav_username_div")[1]).hide();
                $($(".nav_username_div")[0]).show();
                $($(".nav_username_div")[0]).find("span").html(data.object);
            }
        }
    }
}

function logout() {

    var xhr = sendXML("/logout", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var data = xhr.response;
            if (data.status == true) {
                location.href = "Login.html";
            }
        }
    }
}

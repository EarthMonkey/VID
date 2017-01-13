/**
 * Created by L.H.S on 2017/1/10.
 */

var last_contact_click = null;

window.onload = function () {
    addIndex();
    initContacts();
    getVideos();
};

function search() {

    if ($("#search_cancel").css("display") == "none" && $("#search").val() != "") {
        $("#search_cancel").show();
        $("#search_cancel").click(function () {
            $("#search").val("");
            $(this).hide();
            $("#search_list").hide();
            $("#lists").show();
        });
    } else {
        if ($("#search").val() == "") {
            $("#search_cancel").hide();
        }
    }

    if ($("#lists").css("display") != "none" && $("#search").val() != "") {
        $("#lists").hide();
        $("#search_list").show();
    } else {
        if ($("#search").val() == "") {
            $("#search_list").hide();
            $("#lists").show();
        }
    }


}

function addIndex() {
    var parent = document.getElementById("indexA");

    var a = 65;
    for (var i = 0; i < 26; i++) {
        var div = document.createElement("div");
        div.innerHTML = String.fromCharCode(a + i);
        parent.appendChild(div);

        $(div).click(function () {
            var pos = $("#" + this.innerHTML).offset().top;
            $(".contacts_list").animate({scrollTop: pos}, 500);
        });
    }

    var diva = document.createElement("div");
    diva.innerHTML = "#";
    parent.appendChild(diva);

    $(".contacts_list").on('scroll', function () {
        parent.style.top = 90 + $(".contacts_list").scrollTop() + "px";
    });
}

function initContacts() {

    var parent = document.getElementById("lists");

    var a = 65;
    for (var i = 0; i < 3; i++) {
        var idx = document.createElement("div");
        idx.className = "each_idx";
        var idx_name = String.fromCharCode(a + i);
        idx.innerHTML = idx_name;
        $(idx).attr("id", idx_name);
        parent.appendChild(idx);

        for (var j = 1; j < 6; j++) {
            var con_div = document.createElement("div");
            con_div.className = "each_contact";
            con_div.innerHTML = idx_name + "姓联系人" + j + "<hr>"
            parent.appendChild(con_div);

            $(con_div).click(function () {

                if (last_contact_click != null)
                    $(last_contact_click).css("background-color", "transparent");

                $(this).css("background-color", "#d9eef9");
                last_contact_click = this;
            });

        }
    }
}

// 视频放大
function enlarge(node) {

    var vid = node.parentNode.getElementsByTagName("video")[0];
    if (!vid.paused) {
        vid.pause();
    }

    $("#modal").fadeIn(200);
    $("#main_body").css("-webkit-filter", "blur(3px)");
    $($("#modal").find(".enlarge_btn")).click(function () {
        $("#modal").hide();
        $("#main_body").css("-webkit-filter", "");
        $($("#modal").find("video")).remove();
    });

    var video = $(node).parent("div").find("video");
    var video_copy = $(video).clone();

    $($("#modal").find(".enlarge_modal")).append($(video_copy));
    $(video_copy).attr("poster", "");
    $(video).attr("preload", "metadata");
    $(video_copy).css("width", "100%");
    $(video_copy).css("margin", "30px auto");

}

// 滑出我的名片夹
function showMine() {

    $("#detail_part").hide();

    initMyVideos();
    $("#mine").animate({
        width: "show"
    }, 200);

    $(".group_lbl, .contacts_list").click(function (e) {
        var elem = e.target || e.srcElement;
        if (elem.className && elem.className == "each_group first_bottom") {
            return;
        }
        hideMine();
    });
}

// 点击侧边滑回
function hideMine() {
    if ($("#mine").css("display") != "none") {
        $("#mine").animate({
            width: "hide"
        }, 200);
        $("#detail_part").show();
    }

    if ($("#left_part").css("display") != "none") {
        $("#left_part").animate({
            width: "hide"
        }, 200);
    }

    var mine = document.getElementById("mine");
    var tips = mine.getElementsByClassName("tip_text");
    $(tips[0]).show();
    $(tips[1]).hide();
}

// 我的视频
function initMyVideos() {

    var parent = document.getElementById("my_videos");
    var copy = document.getElementById("video_copy");

    var number = 3;
    $("#my_videos").css("width", 230 * number + "px");
    for (var i = 0; i < number; i++) {

        var div = document.createElement("div");
        div.className = "video_div";
        div.innerHTML = copy.innerHTML;

        var source = div.getElementsByTagName("source")[0];
        var name_div = div.getElementsByClassName("video_name")[0];

        source.src = "https://media.html5media.info/video.iphone.mp4";
        name_div.innerHTML = "我的视频" + (i + 1);

        parent.appendChild(div);
    }
}

// 联系人的视频
function getVideos() {

    var parent = document.getElementById("videos");
    var copy = document.getElementById("video_copy");

    var number = 3;
    for (var i = 0; i < number; i++) {

        var div = document.createElement("div");
        div.className = "video_div";
        div.innerHTML = copy.innerHTML;

        var source = div.getElementsByTagName("source")[0];
        var name_div = div.getElementsByClassName("video_name")[0];

        source.src = "https://media.html5media.info/video.iphone.mp4";
        name_div.innerHTML = "我的视频" + (i + 1);

        parent.appendChild(div);
    }
}

// 展开我的左半边
function showMyAll() {
    $("#left_part").animate({
        width: "show"
    }, 200);

    var mine = document.getElementById("mine");
    var tips = mine.getElementsByClassName("tip_text");
    $(tips[1]).show();
    $(tips[0]).hide();
}
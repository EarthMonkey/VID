/**
 * Created by L.H.S on 2017/1/10.
 */

var last_contact_click = null;
var CONTACTS = {};

window.onload = function () {
    getAll();
    slideRight();
    addIndex();
};

function getAll() {

    var xhr = sendXML("/contacts/all", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            CONTACTS = (xhr.response).object;

            $(".nav_username_div").find("span").html(CONTACTS.name);

            initGroups(CONTACTS.groupList);
            initContacts(CONTACTS.contacts);
        }
    };
}

var LAST_SEARCH = "";  // 用来防止输入上下左右键
function search() {

    // 取消搜索
    if ($("#search_cancel").css("display") == "none" && $("#search").val() != "") {
        $("#search_cancel").show();
        $("#search_cancel").click(function () {
            $("#search").val("");
            $(this).hide();
            $("#search_list").hide();
            $("#groupContact").hide();
            $("#lists").show();
            if ($(".no_search").css("display") == "none") {
                $(".no_search").show();
            }
            LAST_SEARCH = "";
        });
    } else {
        if ($("#search").val() == "") {
            $("#search_cancel").hide();
        }
    }

    // 搜索
    if ($("#lists").css("display") != "none" && $("#search").val() != "") {
        $("#lists").hide();
        $("#groupContact").hide();
        $("#search_list").show();

    } else {
        if ($("#search").val() == "") {
            $("#search_list").hide();
            if ($(".no_search").css("display") == "none") {
                $(".no_search").show();
            }
            LAST_SEARCH = "";

            $("#lists").show();
        }
    }

    var key = $("#search").val()
    if (key != "") {
        searchKey(key);
    }
}

function searchKey(key) {

    if (key === LAST_SEARCH) {
        return;
    } else {
        LAST_SEARCH = key;
        $("#search_list").find(".each_contact").remove();
    }

    var hasResult = 0;

    var contactArray = CONTACTS.contacts;

    for (var i = 0; i < contactArray.length; i++) {
        for (var j = 0; j < contactArray[i].length; j++) {

            var eachContact = contactArray[i][j];

            var conName = eachContact.noteName;
            if (conName.indexOf(key) > -1 || makePy(conName).indexOf(key.toUpperCase()) > -1) {
                hasResult = 1;
                // 搜索到
                appendContact(eachContact, $("#search_list"));
            }
        }
    }

    if (hasResult == 0) {
        if ($(".no_search").css("display") == "none") {
            $(".no_search").show();
        }
    } else {
        if ($(".no_search").css("display") != "none") {
            $(".no_search").hide();
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
            if ($("#" + this.innerHTML).length > 0) {
                var pos = $("#" + this.innerHTML).offset().top;
                $(".contacts_list").animate({scrollTop: pos}, 500);
            }
        });
    }

    var diva = document.createElement("div");
    diva.innerHTML = "#";
    parent.appendChild(diva);

    $(".contacts_list").on('scroll', function () {

        parent.style.top = 80 + $(".contacts_list").scrollTop() + "px";
    });
}

function initContacts(contacts) {

    var parent = $("#lists");

    var a = 65;
    for (var i = 0; i < contacts.length; i++) {

        var eachIndex = contacts[i];
        if (eachIndex.length > 0) {
            // 索引
            var idx = $("<div class='each_idx'></div>");
            var idx_name = String.fromCharCode(a + i);
            idx.html(idx_name);
            idx.attr("id", idx_name);
            parent.append(idx);

            // 索引下联系人
            for (var j = 0; j < eachIndex.length; j++) {
                appendContact(eachIndex[j], parent);
            }
        }
    }

    var ie = !-[1,];
    if (ie) {
        $($("#lists").find(".each_contact")[0]).trigger('click').trigger('change');
    } else {
        $($("#lists").find(".each_contact")[0]).trigger('click');
    }
}

function appendContact(eachIndex, parent) {

    var con_div = $("<div class='each_contact'></div>");
    con_div.html(eachIndex.noteName + "<hr>");
    parent.append(con_div);

    var idStore = $("<a style='display: none;'></a>");
    idStore.html(eachIndex.userID);
    con_div.append(idStore);

    con_div.click(function () {
        if (last_contact_click != null) {
            $(last_contact_click).css("background-color", "transparent");
        }
        $(this).css("background-color", "#d9eef9");
        last_contact_click = this;

        // 展示该联系人详情；
        getContactDetail(this);
    });
}

function changeAllContact() {
    $('#search_list').hide();
    $('#groupContact').hide();
    $('#lists').show();

    if (LAST_GROUP != null) {
        $(LAST_GROUP).css("background-color", "#d6d8db");
        LAST_GROUP = null;
    }
}

function getContactDetail(node) {

    var conId = $(node).find("a").html();

    var data = "contactID=" + conId;
    var xhr = sendXML("/contacts/profile", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {

            var resp = xhr.response;
            if (resp.status == true) {
                var info = resp.object;

                // 头像链接
                //
                $("#detail").find(".contact_name").html(info.noteName);
                var spans = $("#detail").find(".contact_info").find("span");
                $(spans[0]).html(info.interest);
                $(spans[1]).html(info.phoneNum);
                $(spans[2]).html(info.email);

                // 视频
                setVideos(info.videoList);
            }
        }
    }
}

// 视频放大
function enlarge(node) {

    var video = $(node).parent("div").find("video");

    if (!$(video)[0].paused) {
        $(video)[0].pause();
    }

    $("#modal").fadeIn(200);
    $("#main_body").css("-webkit-filter", "blur(3px)");
    $($("#modal").find(".enlarge_btn")).click(function () {
        $("#modal").hide();
        $("#main_body").css("-webkit-filter", "");
        $($("#modal").find("video")).remove();
    });

    var video_copy = $(video).clone();

    $($("#modal").find(".enlarge_modal")).append($(video_copy));
    $(video_copy).attr("poster", "");
    $(video).attr("preload", "auto");
    $(video_copy).css("width", "100%");
    $(video_copy).css("margin", "30px auto");
    $(video_copy)[0].play();
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
function setVideos(videoList) {

    $("#videos").find(".video_div").remove();
    var copy = $("#video_copy");

    var noVideo = $("#videos").find(".no_video");
    if (videoList.length == 0) {
        if (noVideo.css("display") == "none") {
            noVideo.show();
        }
        return;
    }

    if (noVideo.css("display") != "none") {
        noVideo.hide();
    }

    for (var i = 0; i < videoList.length; i++) {

        var div = $("<div class='video_div'></div>");
        div.html(copy.html());

        div.find("source").attr("src", videoList[i].url);
        div.find(".video_name").html(videoList[i].name);

        $("#videos").append(div);
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

// 增加分组
function addGroup() {
    $("#groupModal").fadeIn();
    $("#main_body").css("-webkit-filter", "blur(3px)");


    var tagI = $("#groupModal").find("i");
    $(tagI[1]).click(function () {
        $("#main_body").css("-webkit-filter", "");
        $("#groupModal").hide();
    });
}

function newGroup() {
    var inputField = $("#groupModal").find("input");

    var groupName = inputField.val();
    if (groupName == "") {
        inputField.focus();
    } else {
        // 存储新分组

        var data = "name=" + groupName;
        var xhr = sendXML("/contacts/group/add", "POST", data);
        xhr.onreadystatechange = function () {

            if (xhr.readyState == 4 && xhr.status == 200) {
                var groupId = (xhr.response).object;
                var newGroup = {
                    "name": groupName,
                    "id": groupId
                };
                setGroup(newGroup);
                $("#main_body").css("-webkit-filter", "");
                $("#groupModal").hide();
                inputField.val("");
            }
        };
    }
}

var LAST_GROUP;
function initGroups(groupList) {
    for (var i = 0; i < groupList.length; i++) {
        setGroup(groupList[i]);
    }
}

function setGroup(groupList) {
    var parent = $("#lbls");
    var copy = $("#group_copy");

    var group = $("<div class='each_group'></div>");
    group.html(copy.html());
    group.find("span").html(groupList.name);
    parent.append(group);

    var idStore = $("<a style='display: none;'></a>")
    idStore.html(groupList.id);
    group.append(idStore);

    group.click(function () {
        if (LAST_GROUP != null) {
            $(LAST_GROUP).css("background-color", "#d6d8db");
        }

        $(this).css("background-color", "#d9eef9");
        LAST_GROUP = this;

        getTheGroup(this);
    });
}

// 获取分组下信息 
function getTheGroup(node) {

    $("#groupContact").find(".each_contact").remove();

    var groupName = $(node).find("span").html();
    var allContact = CONTACTS.contacts;

    var hasContacts = 0;
    for (var i = 0; i < allContact.length; i++) {
        var eachArray = allContact[i];
        for (var j = 0; j < eachArray.length; j++) {
            if (eachArray[j].group == groupName) {

                hasContacts = 1;
                appendContact(eachArray[j], $("#groupContact"));
            }
        }
    }

    if (hasContacts == 0) {
        $(".no_search").show();
    } else {
        $(".no_search").hide();
    }

    $("#lists").hide();
    $("#search_list").hide();
    $("#groupContact").show();
}

function modGroup() {

    var nameOld = $(LAST_GROUP).find("span").html();

    $("#groupModModal").fadeIn();
    $("#main_body").css("-webkit-filter", "blur(3px)");

    var inputField = $("#groupModModal").find("input");
    inputField.val(nameOld);

    var tagI = $("#groupModModal").find("i");

    $(tagI[0]).click(function () {
        var groupName = inputField.val();
        if (groupName == "") {
            inputField.focus();
        } else {
            // 更新分组名称
            var groupID = $(LAST_GROUP).find("a").html();

            var data = "groupID=" + groupID + "&name=" + groupName;
            var xhr = sendXML("/contacts/group/rename", "POST", data);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var resp = xhr.response;

                    if (resp.status == true) {
                        $(LAST_GROUP).find("span").html(groupName);
                        $("#main_body").css("-webkit-filter", "");
                        $("#groupModModal").hide();
                    } else {
                        // 分组重名
                        alert(resp.info);
                    }
                }
            };

        }
    });

    $(tagI[1]).click(function () {
        $("#main_body").css("-webkit-filter", "");
        $("#groupModModal").hide();
    });
}

function delGroup() {

    $("#confirmModal").fadeIn();
    $("#main_body").css("-webkit-filter", "blur(3px)");

    var btn = $("#confirmModal").find(".con_modal_btn");

    $(btn[1]).click(function () {
        $("#confirmModal").hide();
        $("#main_body").css("-webkit-filter", "");
    });
}

function deleteG() {
    // 删除分组
    var groupID = $(LAST_GROUP).find("a").html();
    var data = "groupID=" + groupID;
    var xhr = sendXML("/contacts/group/remove", "POST", data);
    xhr.onreadystatechange = function () {

        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;
            if (resp.status == true) {
                $(LAST_GROUP).remove();
                $("#confirmModal").hide();
                $("#main_body").css("-webkit-filter", "");
            } else {
                alert(resp.info);
            }
        }
    };
}

function slideRight() {

    var groups = $("#lbls").find(".each_group");

    for (var i = 0; i < groups.length; i++) {
        swipeListener(groups[i]);
        mouseSwiper(groups[i]);
    }
}

var DELETE_QUEUE = [];
var DELETE_INDEX = 0;

// 修改联系人详细信息
function modDetail(node) {

    if ($(node).html() == "取消") {
        $("#detailMod").hide();
        $("#detail").show();
        $(node).html("编辑");
        clearVideoMod();
        DELETE_INDEX = 0;
        DELETE_QUEUE = [];

        return;
    }

    $(node).html("取消");
    $("#detail").hide();
    $("#detailMod").show();

    var inputs = $("#detailMod").find("input");
    inputs[0].value = $("#detail").find(".contact_name").html();
    var spans = $("#detail").find("span");
    for (var i = 0; i < spans.length; i++) {
        inputs[i + 1].value = spans[i].innerHTML;
    }

    // 视频
    var videoParent = $("#videosMod");
    var videoCopy = $("#vidMod_copy");
    var videos = $("#videos").find(".video_div");
    for (var i = 0; i < videos.length; i++) {
        var div = $("<div></div>");
        div.html(videoCopy.html());
        div.find("source").attr("src", $(videos[i]).find("source").attr("src"));
        div.find("input").val($(videos[i]).find(".video_name").html());

        var delBtn = div.find(".del_btn");
        delBtn.click(function () {
            DELETE_QUEUE[DELETE_INDEX] = $("#videosMod").find("input").index($(this.parentNode).find("input"));
            DELETE_INDEX++;
            $(this.parentNode).hide();
        });

        videoParent.append(div);
    }
}

// 修改完成
function comDetailMod() {

    // 修改信息
    var inputs = $("#detailMod").find("input");
    $("#detail").find(".contact_name").html(inputs[0].value);
    var spans = $("#detail").find("span");
    for (var i = 0; i < spans.length; i++) {
        spans[i].innerHTML = inputs[i + 1].value;
    }

    // 修改视频名称
    var videoMods = $("#videosMod").find("input");
    var videoNames = $("#videos").find(".video_div").find(".video_name");
    for (var i = 1; i < videoMods.length; i++) {
        $(videoNames[i - 1]).html($(videoMods[i]).val());
    }

    // 删除视频
    for (var i = 0; i < DELETE_INDEX; i++) {
        $($("#videos").find(".video_div")[DELETE_QUEUE[i]]).remove();
    }

    DELETE_INDEX = 0;
    DELETE_QUEUE = [];
    clearVideoMod();
    $("#detailMod").hide();
    $("#detail").show();
    $("#detail_part").find(".edit_btn").html("编辑");
}

// 删除联系人
function delContact() {

    DELETE_INDEX = 0;
    DELETE_QUEUE = [];
}

// 清空div
function clearVideoMod() {
    var videoMods = $("#videosMod").find(".del_btn");
    for (var i = 0; i < videoMods.length; i++) {
        $(videoMods[i].parentNode).remove();
    }
}
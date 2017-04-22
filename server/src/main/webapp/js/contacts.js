/**
 * Created by L.H.S on 2017/1/10.
 */

var last_contact_click = null;
var CONTACTS = {};
var ME;  // 我的信息

window.onload = function () {
    getMyInfo();
    getAll();
    addIndex();

    $(".group_lbl").css("height", document.documentElement.clientHeight + "px");
    $(".contacts_list").css("height", document.documentElement.clientHeight + "px");

};

function getMyInfo() {

    var xhr = sendXML("/profile", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;
            if (resp.status) {
                var temp_me = resp.object;
                // 我的信息
                ME = {
                    noteName: temp_me.name,
                    userId: temp_me.userID,
                    portrait: temp_me.portrait,
                    interest: temp_me.interest,
                    phoneNum: temp_me.phoneNum,
                    email: temp_me.email,
                    videoList: temp_me.videoList
                };
                appendContact(ME, $("#lists"), "me");
            } else {
                alert(resp.info);
            }
        }
    };

}

function getAll() {

    var xhr = sendXML("/contacts/all", "POST", "");
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            CONTACTS = (xhr.response).object;

            $(".nav_username_div").find("span").html(CONTACTS.name);
            initGroups(CONTACTS.groupList);
            initContacts(CONTACTS.contacts);
            slideRight();
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
                var pos = $("#" + this.innerHTML).offset().top - 95;
                $("#lists").animate({scrollTop: pos}, 500);
            }
        });
    }

    var diva = document.createElement("div");
    diva.innerHTML = "#";
    parent.appendChild(diva);
}

function initContacts(contacts) {

    var parent = $("#lists");
    parent.css("height", document.documentElement.clientHeight - "95" + "px");
    console.log(parent.css("height"));
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

    if (arguments.length > 2) {  // 用来标识自己的块
        $(con_div).attr("isMe", "isMe");
    }

    con_div.click(function () {

        if ($("#detailMod").css("display") != "none") {
            DELETE_INDEX = 0;
            DELETE_QUEUE = [];
            clearVideoMod();
            $("#detailMod").hide();
            $("#detail").show();
            $("#detail_part").find(".edit_btn").html("编辑");
        }

        if (last_contact_click != null) {
            $(last_contact_click).css("background-color", "transparent");
        }
        $(this).css("background-color", "#d9eef9");
        last_contact_click = this;

        // 展示该联系人详情；
        if ($(this).attr("isMe")) {
            getContactDetail(this, "me");
        } else {
            getContactDetail(this);
        }

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

    if (arguments.length > 1) { // 获取自己的信息，通过ME获得
        $("#detail").find(".portrait_div").find("img").attr("src", ME.portrait);
        // 其他信息
        $("#detail").find(".contact_name").html(ME.noteName);
        var spans = $("#detail").find(".contact_info").find("span");
        $(spans[0]).html(ME.interest);
        $(spans[1]).html(ME.phoneNum);
        $(spans[2]).html(ME.email);
        $("#detail").find("storage").html("-1");

        $("#detail").attr("isMe", true);  // 用来判断是否调用修改个人信息的url

        // 视频
        setVideos(ME.videoList);

        return;
    }

    $("#detail").attr("isMe", false);

    var conId = $(node).find("a").html();

    var data = "contactID=" + conId;
    var xhr = sendXML("/contacts/profile", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {

            var resp = xhr.response;
            if (resp.status == true) {
                var info = resp.object;

                // 头像链接
                $("#detail").find(".portrait_div").find("img").attr("src", info.portrait);
                // 其他信息
                $("#detail").find(".contact_name").html(info.noteName);
                var spans = $("#detail").find(".contact_info").find("span");
                $(spans[0]).html(info.interest);
                $(spans[1]).html(info.phoneNum);
                $(spans[2]).html(info.email);

                if (info.group != null) {
                    $("#detail").find("storage").html(info.group.name);
                } else {
                    $("#detail").find("storage").html("未分组");
                }

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

var MINE_FIRST = 0;  // 用来判断是否为第一次加载我的信息
// 滑出我的名片夹
function showMine() {

    // 通过ME来加载数据
    if (MINE_FIRST == 0) {
        $("#previewImg").attr("src", ME.portrait);
        $("#left_part").find(".contact_name").html(ME.noteName);
        var myInfo = $("#mine").find(".contact_info").eq(0).find("span");
        console.log(ME)
        $(myInfo).eq(0).html(ME.interest);
        $(myInfo).eq(1).html(ME.phoneNum);
        $(myInfo).eq(2).html(ME.email);

        initMyVideos();

        MINE_FIRST = 1;
    }

    $("#detail_part").hide();

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

    $("#mine").find(".tip_text").show();
}

// 我的视频
function initMyVideos() {

    var parent = document.getElementById("my_videos");
    var copy = document.getElementById("video_copy");

    var videoList = ME.videoList;
    for (var i = 0; i < videoList.length; i++) {

        var div = document.createElement("div");
        div.className = "video_div";
        div.innerHTML = copy.innerHTML;

        var source = div.getElementsByTagName("source")[0];
        var name_div = div.getElementsByClassName("video_name")[0];

        var qr_img = $("<img class='qr_img' src='../image/qrcode.png'>");
        qr_img.attr("videoId", videoList[i].id);
        $(div).append(qr_img);
        $(qr_img).click(function () {
            getQRCode(this);
        });

        source.src = videoList[i].url;
        name_div.innerHTML = videoList[i].name;

        // 存储id
        var aStore = $("<a style='display: none;'>" + videoList[i].id + "</a>");
        $(div).append(aStore);

        parent.appendChild(div);
    }
}

// 联系人的视频
function setVideos(videoList) {

    $("#videos").find(".video_div").remove();
    var copy = $("#video_copy");

    var noVideo = $("#videos").find(".no_video");
    if (videoList[0] == null) {
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

        var idStore = $("<a style='display:none'></a>");
        idStore.html(videoList[i].id);
        div.append(idStore);

        $("#videos").append(div);
    }
}

// 展开我的左半边
function showMyAll() {
    $("#left_part").animate({
        width: "show"
    }, 200);

    $("#mine").find(".tip_text").hide();
}

// 增加分组
function addGroup() {
    $("#groupModal").fadeIn();
    $("#main_body").css("-webkit-filter", "blur(3px)");


    var tagI = $("#groupModal").find(".con_modal_btn");
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
    var modGroup = $("#modGroup");

    for (var i = 0; i < groupList.length; i++) {
        setGroup(groupList[i]);

        var option = $("<option></option>");
        option.html(groupList[i].name);
        option.attr("Gid", groupList[i].id);
        $(modGroup).append(option); // 修改分组
    }

    var option_default = $("<option></option>");
    option_default.html("未分组");
    option_default.attr("Gid", -1);
    $(modGroup).append(option_default);
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

    var tagI = $("#groupModModal").find(".con_modal_btn");

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
var LOOP_POS = 0;

var OLD_GROUP;  // 原group，用来判断是否发生了分组变更

// 修改联系人详细信息
function modDetail(node) {

    DELETE_INDEX = 0;
    LOOP_POS = 0;
    DELETE_QUEUE = [];
    clearVideoMod();

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

    // 头像
    var imgUrl = $("#detail").find(".portrait_div").find("img").attr("src");
    $("#detailMod").find(".photo_mod").find("img").attr("src", imgUrl);

    var inputs = $("#detailMod").find("input");
    inputs[0].value = $("#detail").find(".contact_name").html();
    var spans = $("#detail").find("span");
    for (var i = 0; i < spans.length; i++) {
        inputs[i + 1].value = spans[i].innerHTML;
    }

    // 分组选择
    OLD_GROUP = $("#detail").find("storage").html();
    if (OLD_GROUP == -1) {   // 用户自己
        OLD_GROUP = "未分组";
        $("#modGroup").val(OLD_GROUP);
        $("#modGroup").attr("disabled", "disabled");

        $("#detailMod").find(".del_contact").hide();
    } else {  // 联系人
        $("#modGroup").attr("disabled", false);
        $("#modGroup").val(OLD_GROUP);

        $("#detailMod").find(".del_contact").show();
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

        var idStore = $("<a style='display: none'></a>");
        idStore.html($(videos[i]).find("a").html());
        div.append(idStore);

        var delBtn = div.find(".del_btn");
        delBtn.click(function () {
            DELETE_QUEUE[DELETE_INDEX] = $("#videosMod").find("input").index($(this.parentNode).find("input"));
            DELETE_INDEX++;
            $(this.parentNode.parentNode).hide();
        });

        videoParent.append(div);
    }
}

// 修改完成
function comDetailMod() {


    // 修改信息
    var inputs = $("#detailMod").find("input");

    var contactID = $(last_contact_click).find("a").html();

    var xhr;

    if ($("#detail").attr("isMe") == true) { // 修改个人信息
        var profile = {
            name: inputs[0].value,
            phoneNum: inputs[2].value,
            email: inputs[3].value,
            industry: "",    // 不修改
            interest: inputs[1].value
        };
        var data = "profile=" + JSON.stringify(profile);
        xhr = sendXML("/profile/edit", "POST", data);

    } else {
        var profile = {
            noteName: inputs[0].value,
            phoneNum: inputs[2].value,
            email: inputs[3].value,
            industry: "",    // 不修改
            interest: inputs[1].value
        };
        var data = "contactID=" + contactID + "&profile=" + JSON.stringify(profile);
        xhr = sendXML("/contacts/edit", "POST", data);
    }

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var resp = xhr.response;

            if (resp.status == true) {

                var detail = $("#detail");
                $(detail).find("img").eq(0).attr("src", inputs[0].value);
                $(detail).find(".contact_name").html(inputs[0].value);
                var spans = $(detail).find("span");
                for (var i = 0; i < spans.length; i++) {
                    spans[i].innerHTML = inputs[i + 1].value;
                }
            } else {
                alert(resp.info);
            }
        }
    };

    // 修改视频名称
    var videoMods = $("#videosMod").find("input");
    var videoNames = $("#videos").find(".video_div").find(".video_name");
    for (var i = 1; i < videoMods.length; i++) {

        /** 修改视频名称 *********************************/
        $(videoNames[i - 1]).html($(videoMods[i]).val());
    }

    // 删除视频
    for (var i = 0; i < DELETE_INDEX; i++) {

        var theVideo = $("#videos").find(".video_div")[DELETE_QUEUE[i] - 1];
        var videoID = $(theVideo).find("a").html();

        var videoData = "videoID=" + videoID;
        var xhr_vd = sendXML("/video/remove", "POST", videoData);
        xhr_vd.onreadystatechange = function () {
            if (xhr_vd.readyState == 4 && xhr_vd.status == 200) {
                var resp = xhr_vd.response;

                if (resp.status == true) {
                    // alert(DELETE_QUEUE[LOOP_POS])
                    $($("#videos").find(".video_div")[DELETE_QUEUE[LOOP_POS] - 1]).remove();
                    LOOP_POS++;
                } else {
                    alert(resp.info);
                }
            }
        };
    }

    // 修改分组信息
    var Gname = $("#modGroup").val();
    if (Gname != OLD_GROUP) {
        var Gdata = "contactID=" + contactID + "&groupID=" + $($("#modGroup").get(0).selectedOptions).attr("gid");
        var Gxhr = sendXML("/contacts/group/group", "POST", Gdata);

        Gxhr.onreadystatechange = function () {
            if (Gxhr.readyState == 4 && Gxhr.status == 200) {
                console.log(Gxhr.response);
            }
        }
    }

    $("#detailMod").hide();
    $("#detail").show();
    $("#detail_part").find(".edit_btn").html("编辑");
}

// 删除联系人
function delContact() {

    DELETE_INDEX = 0;
    DELETE_QUEUE = [];

    var data = "contactID=" + $(last_contact_click).find("a").html();
    var xhr = sendXML("/contacts/remove", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {

            $(last_contact_click).remove();
            last_contact_click = null;

            clearVideoMod();
            $("#detailMod").hide();
            $("#detail_part").find(".edit_btn").html("编辑");
            $("#detail").show();

            var ie = !-[1,];
            if (ie) {
                $($("#lists").find(".each_contact")[0]).trigger('click').trigger('change');
            } else {
                $($("#lists").find(".each_contact")[0]).trigger('click');
            }
        }
    };
}

// 清空div
function clearVideoMod() {

    var videoMods = $("#videosMod").find(".del_btn");
    for (var i = 1; i < videoMods.length; i++) {
        $(videoMods[i].parentNode).remove();
    }
}

// 上传头像
function uploadImage(id) {

    var input = $(id);
    var ie = !-[1,];
    if (ie) {
        $(input).trigger('click').trigger('change');
    } else {
        $(input).trigger('click');
    }
}

//使用IE条件注释来判断是否IE6，通过判断userAgent不一定准确
if (document.all) document.write('<!--[if lte IE 6]><script type="text/javascript">window.ie6= true<\/script><![endif]-->');
// var ie6 = /msie 6/i.test(navigator.userAgent);//不推荐，有些系统的ie6 userAgent会是IE7或者IE8
function changePortrait(picId, fileId) {
    var pic = document.getElementById(picId);
    var file = document.getElementById(fileId);
    if (window.FileReader) {//chrome,firefox7+,opera,IE10,IE9，IE9也可以用滤镜来实现
        oFReader = new FileReader();
        oFReader.readAsDataURL(file.files[0]);
        oFReader.onload = function (oFREvent) {
            pic.src = oFREvent.target.result;
        };
    }
    else if (document.all) {//IE8-
        file.select();
        var reallocalpath = document.selection.createRange().text//IE下获取实际的本地文件路径
        if (window.ie6) pic.src = reallocalpath; //IE6浏览器设置img的src为本地路径可以直接显示图片
        else { //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
        }
    }
    else if (file.files) {//firefox6-
        if (file.files.item(0)) {
            url = file.files.item(0).getAsDataURL();
            pic.src = url;
        }
    }

    var xhr_token = sendXML("/auth/token", "POST", "");
    xhr_token.onreadystatechange = function () {
        if (xhr_token.readyState == 4 && xhr_token.status == 200) {
            var resp = xhr_token.response;
            var token = resp.object;

            var formData = new FormData();
            formData.append('token', token);
            formData.append('file', file.files[0]);

            var xhr_QiNiu = sendXML("http://up.qiniu.com", "POST", formData, "qiniu");
            xhr_QiNiu.onreadystatechange = function () {
                if (xhr_QiNiu.readyState == 4 && xhr_QiNiu.status == 200) {
                    var resp = xhr_QiNiu.response;

                    var data = "url=http://ooosh9wza.bkt.clouddn.com/" + resp.key;
                    var xhr = sendXML("/profile/portrait/upload", "POST", data);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState == 4 && xhr.status == 200) {
                            var resp = xhr.response;
                            if (!resp.status) {
                                alert(resp.info);
                            }
                        }
                    };

                }
            };
        }
    };
}

function getQRCode(video) {

    var videoId = $(video).attr("videoId");

    /******* 跳转地址 **********/
    var url = "http://115.28.210.167:8080/VID/html/VideoPlay.html?videoId=" + videoId;
    var text = "http://qr.liantu.com/api.php?logo=http://ooosh9wza.bkt.clouddn.com/code-logo.png&text=" + url;

    var code = $("#QRCode");
    $(code).fadeIn();
    $(code).css("display", "flex");
    $(code).find("img").attr("src", text);
    $("#main_body").css("filter", "blur(3px)");

    $(code).click(function () {
        $(this).fadeOut();
        $("#main_body").css("filter", "none");
    });
}

function middle2left() {
    $(".swiper").hide();
    showMine();
    $("#Right").fadeIn();
}

function middle2right() {
    var cWidth = document.documentElement.clientWidth;
    $(".swiper").hide();
    $("body").animate({scrollLeft: cWidth}, 300);
    $("#Left").fadeIn();
}

function left2middle() {

    $("#Right").hide();
    hideMine();
    $("#middleLeft").fadeIn();
    $("#middleRight").fadeIn();
}

function right2middle() {

    $("#Left").hide();
    $("body").animate({scrollLeft: 0}, 300);
    $("#middleLeft").fadeIn();
    $("#middleRight").fadeIn();
}


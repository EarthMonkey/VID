/**
 * Created by L.H.S on 2017/2/24.
 */

var loginState = false;

function showMenu() {
    $("#menu").slideDown(200);

    $("body").click(function () {
        if (event.target.className && event.target.className != "dialog_btn") {
            $("#menu").hide();
        }
    });
}

// xhr.readyState == 4 && xhr.status == 200
function sendXML(url, type, data) {

    var xhr;
    if (window.ActiveXObject) {
        //如果是IE浏览器
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        //非IE浏览器
        xhr = new XMLHttpRequest();
    }

    xhr.open(type, url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    xhr.responseType = "json";

    xhr.onload = function () {
        console.log(xhr.response);
    };

    xhr.onerror = function () {
        alert("error");
    };

    xhr.send(data);

    return xhr;
}

// 上传视频
//
// function uploadVideo() {
//
//     var xhr1 = sendXML("/isLogin", "POST", "");
//     xhr1.onreadystatechange = function () {
//         if (xhr1.readyState == 4 && xhr1.status == 200) {
//             var data = xhr1.response;
//             var loginState = data.status;
//
//             if (loginState == false) {
//
//                 $(".remindness_div").html("您还未登录");
//                 $(".remindness_div").fadeIn();
//                 setTimeout("$('.remindness_div').fadeOut()", 3000);
//             } else {
//
//                 upload();
//             }
//         }
//     };
// }

function uploadVideo() {

    if (loginState == false) {
        $(".remindness_div").html("您还未登录");
        $(".remindness_div").fadeIn();
        setTimeout("$('.remindness_div').fadeOut()", 3000);
        return;
    }

    var ie = !-[1,];
    if (ie) {
        $("input:file").trigger('click').trigger('change');
    } else {
        $("input:file").trigger('click');
    }

    $("input:file").change(function () {
        var file = $("input:file")[0].files[0];

        var fileName = file.name;
        var fileSize = file.size;
        var filePath = $("input:file").val();

        var data = "name=" + fileName + "&size=" + fileSize + "&url=" + filePath;

        var xhr = sendXML("/video/upload", "POST", data);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var data = xhr.response;
                if (data.status == true) {
                    $(".remindness_div").html("上传成功");
                    $(".remindness_div").fadeIn();
                    setTimeout("$('.remindness_div').fadeOut()", 3000);
                }
            }
        };

    });
}
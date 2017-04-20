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

    var qiniu = 0;
    if (arguments.length > 3) {  // 七牛云不用设置contenttype
        qiniu = 1;
    }

    var xhr;
    if (window.ActiveXObject) {
        //如果是IE浏览器
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        //非IE浏览器
        xhr = new XMLHttpRequest();
    }

    xhr.open(type, url);
    if (qiniu != 1) {
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    }
    xhr.responseType = "json";

    xhr.onload = function () {
        console.log(xhr.response);
    };

    xhr.onerror = function () {
        console.log("error");
    };

    xhr.send(data);

    return xhr;
}

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

        if (fileSize > 10*1024*1024) {  // 10MB
            alert(fileSize)
            $(".remindness_div").html("视频要小于10MB");
            $(".remindness_div").fadeIn();
            setTimeout("$('.remindness_div').fadeOut()", 3000);
            return;
        }

        var xhr_token = sendXML("/auth/token", "POST", "");
        xhr_token.onreadystatechange = function () {
            if (xhr_token.readyState == 4 && xhr_token.status == 200) {
                var resp = xhr_token.response;
                var token = resp.object;

                var formData = new FormData();
                formData.append('token', token);
                formData.append('file', file);

                var xhr_QiNiu = sendXML("http://up.qiniu.com", "POST", formData , "qiniu");
                xhr_QiNiu.onreadystatechange = function () {
                    if (xhr_QiNiu.readyState == 4 && xhr_QiNiu.status == 200) {
                        var resp_QiNiu = xhr_QiNiu.response;

                        var data = "name=" + fileName + "&size=" + fileSize +
                            "&url=" + "http://ooosh9wza.bkt.clouddn.com/" + resp_QiNiu.key;
                        console.log(data);
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
                    }
                };
            }
        };
    });
}

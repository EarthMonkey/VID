/**
 * Created by L.H.S on 2017/2/24.
 */

var SERVER_IP = "http://115.28.210.167:8080/VID";

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
    xhr.responseType = "json";

    xhr.onload = function () {
        console.log(xhr.response);
    };

    xhr.onerror = function () {
        alert("error");
    };

    xhr.send(JSON.stringify(data));

    return xhr;
}

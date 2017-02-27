/**
 * Created by L.H.S on 2017/2/23.
 */

function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function verify() {

    var data = "userID=" + getParameter("userID") + "&random=" + getParameter("random");

    var xhr = sendXML(SERVER_IP + "/activate", "POST", data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.response;
           if (result.status == true) {
               gotoTime();
           }
        }
    }

}

function gotoTime() {
    var span = $(".goto_btn").find("span");
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
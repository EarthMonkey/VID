/**
 * Created by L.H.S on 2017/2/24.
 */

var SERVER_IP = "115.28.210.167:8080/VID";

function showMenu() {
    $("#menu").slideDown(200);

    $("body").click(function () {
        if (event.target.className && event.target.className != "dialog_btn") {
            $("#menu").hide();
        }
    });
}
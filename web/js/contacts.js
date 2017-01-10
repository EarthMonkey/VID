/**
 * Created by L.H.S on 2017/1/10.
 */

window.onload = function () {
    addIndex();
};

function search() {

    if ($("#search_cancel").css("display") == "none") {
        $("#search_cancel").show();
        $("#search_cancel").click(function () {
            $("#search").val("");
            $(this).hide();
        });
    } else {
        if ($("#search").val() == "") {
            $("#search_cancel").hide();
        }
    }


}

function addIndex() {
    var parent = document.getElementById("indexA");

    var a = 65;
    for (var i=0; i<26; i++) {
        var div = document.createElement("div");
        div.innerHTML = String.fromCharCode(a+i);
        parent.appendChild(div);
    }

    var div = document.createElement("div");
    div.innerHTML = "#";
    parent.appendChild(div);

}
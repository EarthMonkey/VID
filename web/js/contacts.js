/**
 * Created by L.H.S on 2017/1/10.
 */

var last_contact_click = null;

window.onload = function () {
    addIndex();
    initContacts();
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
/**
 * Created by L.H.S on 2017/2/23.
 */

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
    location.href = "HomePage.html";
}
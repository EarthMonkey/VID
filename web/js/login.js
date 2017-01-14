/**
 * Created by TQ on 17/01/11.
 */
// 0切换到登录；1切换到忘记密码
function change(syb) {
    Ids = ["login", "forget_password"];
    var current = (syb + 1) % 2;
    $("#" + Ids[current]).hide();
    showDiv(Ids[syb]);
}

function showDiv(elem_id) {
    $("#" + elem_id).fadeIn("slow");
}
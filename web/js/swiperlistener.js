/**
 * Created by L.H.S on 2017/2/20.
 */

//
function swipeListener(obj) {
    var shift = 0;

    /*单指拖动*/
    obj.addEventListener("touchstart", function (event) {
        var touch = event.targetTouches[0];

        var x = touch.pageX;
        obj.addEventListener('touchmove', function (event) {
            // 如果这个元素的位置内只有一个手指的话

            if (event.targetTouches.length == 1) {
                event.preventDefault(); // 阻止浏览器默认事件，重要
                var touch = event.targetTouches[0];

                shift = touch.pageX - x;
            }
        }, false);

    });

    obj.addEventListener("touchend", function () {
        if (shift > 0) {
            // alert("swipeRight");
            var tagI = $(this).find("i");
            $(tagI[0]).animate({
                width: "show"
            }, 500);
            $(tagI[1]).animate({
                width: "show"
            }, 500);
        } else if (shift < 0) {
            // alert("swipeLeft");
            var tagI = $(this).find("i");
            $(tagI[0]).animate({
                width: "hide"
            }, 300);
            $(tagI[1]).animate({
                width: "hide"
            }, 300);
        }
    });
}
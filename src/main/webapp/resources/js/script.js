$ = jQuery

$(document).ready(function () {
    $("#closeButton").click(function () {
        $("#fade").hide();
        $("#fade").css({ opacity: 0 });
        $("#loginForm").hide();
    })
});
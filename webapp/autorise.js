$(document).ready(function () {
    $(".log_pass_send").click(function(){
        sendPass();
    });
});

function sendPass() {
    $.post(
        "/auth",
        {login: $(".login").val(), pass: $(".pass").val()},
        function(callback){
            $.cookie('uuid', callback);
            $(location).attr('href',"/mychat.html");
        }
    );
}
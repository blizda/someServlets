$(document).ready(function () {
    $(".log_pass_send").click(function(){
        sendPass();
    });
    /*$.post(
        "/chat",
        {from: "log"},
        function(callback){
            if(callback === "ok"){
                $(location).attr('href',"/mychat.html");
            }
        }
    );*/
});

function sendPass() {
    $.post(
        "/chat",
        {login: $(".login").val(), pass: $(".pass").val(), from: "log"},
        function(callback){
            if(callback === "ok"){
                $(location).attr('href',"/mychat.html");
            }
        }
    );
}
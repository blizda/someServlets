function sendMessage() {
    $.post(
        "/chat",
        {uuid: $.cookie('uuid'), case: "setMassage", message: $(".chat_new_messages").val()},
        function (callback) {
            var massagesList = "";
            var obj = $.parseJSON(callback);
            var massageHistory = obj.massagesarray;
            massageHistory.forEach(function (massage) {
                massagesList += massage.login + ": " + massage.message + "\n";
            });
            $(".chat_messages_list").val(massagesList);
        }
    );
}

$(document).ready(function () {
        $(".chat_submit_massage").click(function(){
            sendMessage();
            $(".chat_new_messages").val('');
        });
        getMassages();
        setInterval(getMassages(), 5000);
});

function getMassages() {
    $.post(
        "/chat",
        {uuid: $.cookie('uuid'), case: "getMassages"},
        function(callback){
            var massagesList= "";
            var obj = $.parseJSON(callback);
            var massageHistory = obj.massagesarray;
            massageHistory.forEach(function (massage) {
                massagesList += massage.login + ": " + massage.message + "\n";
            });
            $(".chat_messages_list").val(massagesList);
        }
    );
}
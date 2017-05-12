var currentMessagesList = "";

function sendMessage() {
    $.post(
        "/chat",
        {uuid: $.cookie('uuid'), case: "setMassage", message: $(".chat_new_messages").val(), mydate: new Date()},
        function (callback) {
            var obj = $.parseJSON(callback);
            var massageHistory = obj.massagesarray;
            massageHistory.forEach(function (massage) {
                currentMessagesList += "[" + massage.data + "] " + massage.login
                    + ": " + massage.message + "\n";
            });
            $(".chat_messages_list").val(currentMessagesList);
        }
    );
}

$(document).ready(function () {
        $(".chat_submit_massage").click(function(){
            sendMessage();
            $(".chat_new_messages").val('');
        });
        getMassages(new Date(0));
        setInterval(getMassages(new Date(), currentMessagesList), 7000);
});

function getMassages(time) {
    $.post(
        "/chat",
        {uuid: $.cookie('uuid'), case: "getMassages", mydate: time},
        function(callback){
            var obj = $.parseJSON(callback);
            var massageHistory = obj.massagesarray;
            massageHistory.forEach(function (massage) {
                currentMessagesList += "[" + massage.data + "] " + massage.login
                    + ": " + massage.message + "\n";
            });
            $(".chat_messages_list").val(currentMessagesList);
        }
    );
}
function sendMessage() {
    $.post(
        "/chat",
        {from: "chat", case: "setMassage", message: $(".chat_new_messages").val()},
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
            $(".chat_new_messages").val('')
        });
        getMassages();
        var listOfMassage = $(".chat_messages_list");

        setInterval(getMassages(), 5000);
});

function getMassages() {
    $.post(
        "/chat",
        {from: "chat", case: "getMassages"},
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
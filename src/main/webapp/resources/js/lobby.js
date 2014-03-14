$ = jQuery;
var queuePol;
var user;

function queue(){
    var trs = $("#qTable").find("tr");
    $(trs[0]).removeClass("permInvisible");
    $(trs[1]).removeClass("permInvisible");

    user = document.getElementById("menuFrm:logName").innerHTML
    queuePol = setInterval(function(){addUserToQueue()}, 5000);
}

function addUserToQueue() {
    var posting = $.post("http://localhost:8080/api/addUserToQueue", { username: user });

    posting.done(function(data) {
        var result = JSON.parse(data);

        alert(result.playerId);

        if(result.playerId>0) {
            clearInterval(queuePol);
            window.location = "http://localhost:8080/play.xhtml?playerId="+result.playerId+"&gameId="+result.gameId+"&color="+result.color;
        }
    });

    posting.fail(function(data) {
        alert("fail");
    });
}

function cnclQueue(){
    var trs = $("#qTable").find("tr");

    $(trs[0]).addClass("permInvisible");
    $(trs[1]).addClass("permInvisible");

    var jBtnL = document.getElementById("joinQueue:leaveBtn");
    jBtnL.click();
}



   
$ = jQuery

$(document).ready(function () {
    $("#closeButton").click(function () {
        $("#fade").hide();
        $("#fade").css({ opacity: 0 });
        $("#loginForm").hide();
    })
});

function getPastGames() {
    var player = $("#un").text();

    $.getJSON("http://localhost:8080/api/user/getGameHistory?username=" + player)
        .done(function(data) {
            var g = data.games;
            var arrayLength = g.length;
            var rows ="";

            for (var i = 0; i < arrayLength; i++) {
             //   alert(g[i].gameId);
                var p = g[i].players;
                var aL = p.length;
                 rows += "<tr>";
                for (var i = 0; i < aL; i++) {
                     var s = p[i].username;
                     var score = p[i].score;

                    rows += "<td>" + s + "</td><td>" + score + "</td>";
                }
                rows += "</tr>";
            }
            $( rows ).appendTo( "#historyList tbody" );
        })
        .fail(function() {
            alert("get turn fail " + player);
        });
}
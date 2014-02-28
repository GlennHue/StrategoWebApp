$ = jQuery;

function ini(){
    var element = document.getElementById("j_idt9:logName");
    if (element.innerHTML !=""){
        var elements = $(".ui-menuitem-text");
        for(var i = 0;i<elements.length;i++) {
            var element = elements[i];
            if(element.innerHTML == "Login") {
                element.innerHTML = "Logout";
            }
    }
}
}

function showLogin() {
    var elements = $(".ui-menuitem-text");
    for(var i = 0;i<elements.length;i++) {
        var element = elements[i];
        if(element.innerHTML == "Login") {
            $("#fade").show();
            $("#fade").animate({ opacity: 0.75 }, 500);
            $("#loginForm").show();
        } else if(element.innerHTML == "Logout") {
            FB.logout(function(response) {
                element.innerHTML = "Login";
                $(".visible").addClass("invisible");
                $(".visible").removeClass("visible");
            });
            element.innerHTML = "Login";
            if (document.getElementById("j_idt9:logName").innerHTML != ""){
            var logout = document.getElementById("logoutFrm:logoutBtn");
            logout.click();
            }

        }
    }
}


function showAlert(){
    alert("er gebeurt iets");
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : "216993358489527",
        status     : true, // check login status
        cookie     : true, // enable cookies to allow the server to access the session
        xfbml      : true  // parse XFBML
    });

    FB.Event.subscribe('auth.authResponseChange', function(response) {
        // Here we specify what we do with the response anytime this event occurs.
        if (response.status === 'connected') {
            testAPI();
        } else if (response.status === 'not_authorized') {
            //FB.logout();
            //FB.login();
        } else {
            //FB.login();
        }
    });
};

// Load the SDK asynchronously
(function(d){
    var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement('script'); js.id = id; js.async = true;
    js.src = "//connect.facebook.net/en_US/all.js";
    ref.parentNode.insertBefore(js, ref);
}(document));

function testAPI() {
    $("#fade").hide();
    $("#fade").css({ opacity: 0 });
    $("#loginForm").hide();

    FB.api('/me', function(response) {
        var elements = $(".ui-menuitem-text");
        for(var i = 0;i<elements.length;i++) {
            var element = elements[i];
            if(element.innerHTML == "Login") {
                element.innerHTML = "Logout";
            }

            if(element.innerHTML =="name" || element.innerHTML == "Welcome, " + response.name) {
                element.innerHTML = "Welcome, " + response.name;
                $(".invisible").addClass("visible");
                $(".invisible").removeClass("invisible");
            }
        }
    });
}
/**
 * Created by mshields on 2017-06-17.
 */
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) { // success
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
            //showGreeting(JSON.parse(message.body).content);
        });

        $("#conversation").show();
        $('#name').focus();

    }, function (frame) { //error handler
        $("#messages").html("");
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").text()}));

    var topic = $('#topic').val(),
        from = $("#name").text(),
        text = $('#msg').val();
    stompClient.send("/app/chat/" + topic, {}, JSON.stringify({ from: from, text: text }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showMessage(msg)
{
    $('#messages').append('<tr>' +
        '<td>' + msg.from + '</td>' +
        '<td>' + msg.topic + '</td>' +
        '<td>' + msg.message + '</td>' +
        '<td>' + msg.time + '</td>' +
        '</tr>');
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { sendName(); });
    connect();
    $(window).bind('beforeunload', disconnect);
});


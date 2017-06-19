/**
 * Created by mshields on 2017-06-17.
 */
var stompClient = null,
    idOfCurrrentlySelectedChatRoom = -666,
    nameOfCurrrentlySelectedChatRoom = ""; // Globals === BAD, I know...

function setConnected(connected) {
    // TODO: Review whether these are needed/wanted anymore
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

        // TODO: Consider removing or re-using this as a signaling channel
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
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

    var topic = nameOfCurrrentlySelectedChatRoom,
        from = $("#name").text(),
        text = $('#msg').val();
    stompClient.send("/app/chat/" + topic, {}, JSON.stringify({ from: from, text: text }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showMessage(msg)
{
    $('#chatRoomHistoryTableBody_' + idOfCurrrentlySelectedChatRoom).append('<tr>' +
        '<td>' + msg.from + '</td>' +
        '<td>' + msg.topic + '</td>' +
        '<td>' + msg.message + '</td>' +
        '<td>' + msg.time + '</td>' +
        '</tr>');
}

$(".btn.btn-primary").on("click", function() {
    roomSelected($(this).children()); // i.e. button
});

function roomSelected(btn) {
    var id = btn.data("chatRoomId");
    idOfCurrrentlySelectedChatRoom = id;
    nameOfCurrrentlySelectedChatRoom = btn.data("chatRoomName");
    window.chatRooms.forEach(function(chatRoom) {
        if (id === chatRoom.id) {
            showRoom(chatRoom.id);
        } else {
            hideRoom(chatRoom.id);
        }
    });
}
function showRoom(roomId) {
    $("#chatRoomHistory_" + roomId).removeClass('hidden');
}
function hideRoom(roomId) {
    $("#chatRoomHistory_" + roomId).addClass('hidden');
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() {
        sendName();
        $("#msg").val("");
    });
    connect();
    $(window).bind('beforeunload', disconnect);
    if (window.chatRooms && window.chatRooms.length > 0) {
        idOfCurrrentlySelectedChatRoom = window.chatRooms[0].id;
        nameOfCurrrentlySelectedChatRoom = window.chatRooms[0].name;
    }
});


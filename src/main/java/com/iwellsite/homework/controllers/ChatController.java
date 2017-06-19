package com.iwellsite.homework.controllers;

import com.iwellsite.homework.messaging.domain.Greeting;
import com.iwellsite.homework.messaging.domain.HelloMessage;
import com.iwellsite.homework.messaging.domain.Message;
import com.iwellsite.homework.messaging.domain.OutputMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by mshields on 2017-06-17.
 *
 * Chat handling here.
 */
@Controller
public class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/messages")
    public OutputMessage send(@DestinationVariable("topic") String topic, Message message) throws Exception {
        return new OutputMessage(message.getFrom(), message.getText(), topic);
    }
}

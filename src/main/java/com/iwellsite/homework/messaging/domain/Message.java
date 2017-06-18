package com.iwellsite.homework.messaging.domain;

import lombok.Data;

/**
 * Created by mshields on 2017-06-17.
 */
@Data
public class Message {

    private String from;
    private String text;

    public Message() {}

}

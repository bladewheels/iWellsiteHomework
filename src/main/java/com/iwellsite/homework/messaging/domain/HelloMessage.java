package com.iwellsite.homework.messaging.domain;

import lombok.Data;

/**
 * Created by mshields on 2017-06-17.
 */
@Data
public class HelloMessage {

    private String name;

    public HelloMessage() {}
    public HelloMessage(String name) {
        this.name = name;
    }
}

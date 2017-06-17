package com.iwellsite.homework.messaging.domain;

import lombok.Data;

/**
 * Created by mshields on 2017-06-17.
 */
@Data
public class Greeting {

    private String content;

    public Greeting() {}
    public Greeting(String content) {
        this.content = content;
    }

}

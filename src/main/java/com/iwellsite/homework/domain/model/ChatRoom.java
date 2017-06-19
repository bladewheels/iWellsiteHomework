package com.iwellsite.homework.domain.model;

import lombok.Data;
import javax.persistence.*;

/**
 * Created by mshields on 2017-06-16.
 */
@Data
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String name;
    private String description;
}

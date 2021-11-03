package com.sgav.sgav.chat;

import javax.persistence.Id;

public class ChatHead {

    @Id
    private int id;
    private String sender;
    private String to;
    private ChatMessage chatMessage;

}

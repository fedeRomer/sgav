package com.sgav.sgav.chat;

import javax.persistence.Id;

public class ChatMessage {

    @Id
    private int id;
    private String message;
    private String from;
}

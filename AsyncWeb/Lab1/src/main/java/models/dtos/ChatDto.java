package models.dtos;

import java.security.MessageDigest;
import java.util.ArrayList;

public class ChatDto {
    private static final ChatDto instance = new ChatDto();
    ArrayList<MessageDto> messages = new ArrayList<>();

    ChatDto() {
        // no-args constructor
    }

    public static ChatDto getInstance() {
        return instance;
    }

    public void addMessage(String username, String msg) {
        messages.add(new MessageDto(username, msg));

    }
}

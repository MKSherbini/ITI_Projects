package models.dtos;

public class MessageDto {
    String userName;
    String msg;

    MessageDto() {
        // no-args constructor
    }

    public MessageDto(String userName, String msg) {
        this.userName = userName;
        this.msg = msg;
    }
}

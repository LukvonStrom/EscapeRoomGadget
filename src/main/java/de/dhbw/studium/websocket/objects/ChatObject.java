package de.dhbw.studium.websocket.objects;

public class ChatObject {
    private String userName;
    private String message;
    private String salt;

    public ChatObject(String userName, String message, String salt) {
        this.userName = userName;
        this.message = message;
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

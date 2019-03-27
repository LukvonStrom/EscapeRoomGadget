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

    public String getSalted() {
        return this.message + this.userName + "#ö#" + this.salt + "#ä#" + this.message + ".1";
    }
}

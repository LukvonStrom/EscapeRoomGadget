package de.dhbw.studium.websocket.objects;

import de.dhbw.studium.websocket.RandomChiffreHelper;

public class ChatObject {
    private String userName;
    private RandomChiffreHelper chiffre;
    private String salt;

    public ChatObject(String userName, String message, String salt) {
        this.userName = userName;
        this.chiffre = new RandomChiffreHelper(message);
        this.salt = salt;
    }

    public String getSalted() {
        return this.chiffre.getMessage() + '-' + this.userName + "-#ö#-" + this.salt + "-#ä#-" + this.chiffre.getMessageEnd() + "-.1";
    }
}

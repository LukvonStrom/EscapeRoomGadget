package de.dhbw.studium.websocket.objects;

public class ChatObject {
    private String userName;
    private String message;
    private String messageEnd;
    private String salt;

    public ChatObject(String userName, String message, String salt) {
        this.userName = userName;
            if(message.length() > 5){
                this.message = message.substring(0,5);
                this.messageEnd = message.substring(message.length() -5);
            }else{
                this.message = message;
                this.messageEnd = message;
            }

        this.salt = salt;
    }

    public String getSalted() {
        return this.message + '-' + this.userName + "-#ö#-" + this.salt + "-#ä#-" + this.messageEnd + "-.1";
    }
}

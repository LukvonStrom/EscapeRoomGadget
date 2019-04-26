package de.dhbw.studium.websocket;

public class RandomChiffreHelper {
    private String message;
    private String messageEnd;

    public RandomChiffreHelper(String message){
        if(message.length() > 5){
            this.message = message.substring(0,5);
            this.messageEnd = message.substring(message.length() -5);
        }else{
            this.message = message;
            this.messageEnd = message;
        }
    }

    public String getMessage() {
        return message;
    }

    public String getMessageEnd() {
        return messageEnd;
    }
}

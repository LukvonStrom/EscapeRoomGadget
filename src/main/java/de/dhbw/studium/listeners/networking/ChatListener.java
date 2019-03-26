package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.websocket.objects.ChatObject;

public class ChatListener implements DataListener<ChatObject> {
    ILog logger;

    public ChatListener(ILog logger) {
        this.logger = logger;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, ChatObject s, AckRequest ackRequest) throws Exception {
        this.logger.log("Received Chat: " + s);
        socketIOClient.sendEvent("chat", s + ": HI there");
    }
}

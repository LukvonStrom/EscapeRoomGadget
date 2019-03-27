package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.websocket.objects.ChatObject;
import org.apache.commons.lang3.RandomStringUtils;

public class ChatListener implements DataListener<String> {
    public static String secret;
    ILog logger;

    public ChatListener(ILog logger) {
        this.logger = logger;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, String str, AckRequest ackRequest) throws Exception {
        this.logger.log("Received Chat: " + str);
        ChatObject s = new ChatObject(socketIOClient.getSessionId().toString(), str, RandomStringUtils.randomAlphanumeric(10));
        ChatListener.secret = s.getSalted();
        socketIOClient.sendEvent("chat", ChatListener.secret);

    }
}

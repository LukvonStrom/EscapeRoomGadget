package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.websocket.objects.ChatObject;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class ChatListener implements DataListener<String> {
    public static String secret;
    private ILog logger;

    public ChatListener(ILog logger) {
        this.logger = logger;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, String str, AckRequest ackRequest) throws InterruptedException {
        this.logger.log("Received Chat: " + str);
        ChatObject s = new ChatObject(socketIOClient.getSessionId().toString().substring(0, 4), str, RandomStringUtils.randomAlphanumeric(5));
        ChatListener.secret = s.getSalted();
        int delay = (new Random()).ints(1000, (5000 + 1)).findFirst().getAsInt();
        Thread.sleep(delay);
        this.logger.log("Sent answer after " + delay + " ms");
        socketIOClient.sendEvent("chat", ChatListener.secret);

    }
}

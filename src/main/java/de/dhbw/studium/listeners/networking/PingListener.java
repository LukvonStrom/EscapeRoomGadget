package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.SocketIOClient;
import de.dhbw.studium.log.ILog;

public class PingListener implements com.corundumstudio.socketio.listener.PingListener {
    ILog logger;

    public PingListener(ILog logger) {
        this.logger = logger;
    }

    @Override
    public void onPing(SocketIOClient socketIOClient) {
        this.logger.log("PING " + socketIOClient.getRemoteAddress());
    }
}

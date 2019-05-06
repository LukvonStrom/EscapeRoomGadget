package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.SocketIOClient;
import de.dhbw.studium.log.ILog;

public class ConnectListener implements com.corundumstudio.socketio.listener.ConnectListener {
    private ILog logger;

    public ConnectListener(ILog logger) {
        this.logger = logger;
    }

    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        this.logger.log("Connection " + socketIOClient.getRemoteAddress());
    }
}

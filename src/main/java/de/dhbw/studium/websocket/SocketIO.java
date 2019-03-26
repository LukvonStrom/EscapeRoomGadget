package de.dhbw.studium.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import de.dhbw.studium.listeners.networking.ChatListener;
import de.dhbw.studium.listeners.networking.ConnectListener;
import de.dhbw.studium.listeners.networking.PingListener;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.websocket.objects.ChatObject;

public class SocketIO {
    private SocketIOServer server;
    private ILog logger;

    public boolean isRunning;


    public SocketIO(ILog logger) {
        this.logger = logger;
    }

    public void init() {
            Configuration config = new Configuration();
            config.setHostname("localhost");
            config.setPort(9092);

            server = new SocketIOServer(config);

            server.addEventListener("chat", ChatObject.class, new ChatListener(this.logger));
            server.addConnectListener(new ConnectListener(this.logger));
            server.addPingListener(new PingListener(this.logger));


            start();
    }

    public void start() {
        this.logger.log("Starte Socket Server...");
        server.start();
        this.logger.log("Socket.IO Server gestartet");
        isRunning = true;
    }

    public void stop(){
        server.stop();
        this.logger.log("Socket.IO Server gestoppt.");
        isRunning = false;
    }
}

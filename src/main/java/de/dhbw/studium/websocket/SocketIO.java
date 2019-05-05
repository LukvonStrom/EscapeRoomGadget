package de.dhbw.studium.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import de.dhbw.studium.listeners.networking.ChatListener;
import de.dhbw.studium.listeners.networking.ConnectListener;
import de.dhbw.studium.listeners.networking.ImageMysteryListener;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.ui.MainForm;

public class SocketIO {
    public boolean isRunning;
    private SocketIOServer server;
    private ILog logger;


    public SocketIO(ILog logger) {
        this.logger = logger;
    }

    public SocketIOServer getServer() {
        return server;
    }

    public void init() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        server = new SocketIOServer(config);

        server.addEventListener("chat", String.class, new ChatListener(this.logger));
        server.addEventListener("image-binary", String.class, new ImageMysteryListener(this.logger, ((MainForm) this.logger).tabbedPane1));
        server.addConnectListener(new ConnectListener(this.logger));


        start();
    }

    public void start() {
        this.logger.log("Starte Socket Server...");
        server.start();
        this.logger.log("Socket.IO Server gestartet");
        isRunning = true;
    }

    public void stop() {
        server.stop();
        this.logger.log("Socket.IO Server gestoppt.");
        isRunning = false;
    }
}

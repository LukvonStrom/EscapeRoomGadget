package de.dhbw.studium;

import de.dhbw.studium.log.ILog;
import de.dhbw.studium.ui.MainForm;
import de.dhbw.studium.websocket.SocketIO;

public class Main {
    public static void main(String[] args) {
        ILog logger = new MainForm();
        ((MainForm) logger).main(args);
        SocketIO socketInstance = new SocketIO(logger);
        socketInstance.init();
        ((MainForm) logger).setSocketInstance(socketInstance);

    }
}

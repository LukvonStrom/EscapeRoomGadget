package de.dhbw.studium;

import de.dhbw.studium.ui.MainForm;
import de.dhbw.studium.websocket.SocketIO;

public class Main {
    public static void main(String[] args) {
        MainForm logger = new MainForm();
        logger.main(args);
        try {
            SocketIO socketInstance = new SocketIO(logger);
            socketInstance.init();
            logger.setSocketInstance(socketInstance);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
}

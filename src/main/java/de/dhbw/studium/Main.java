package de.dhbw.studium;

import de.dhbw.studium.ui.MainForm;
import de.dhbw.studium.websocket.SocketIO;

public class Main {
    public static void main(String[] args) {
        MainForm ui = new MainForm();
        ui.main(args);
        try {
            SocketIO socketInstance = new SocketIO(ui);
            socketInstance.init();
            ui.setSocketInstance(socketInstance);
        } catch (Exception ex) {
            ui.error(ex.getMessage());
        }
    }
}

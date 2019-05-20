package de.dhbw.studium.listeners.ui;

import com.corundumstudio.socketio.SocketIOServer;
import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.listeners.networking.ChatListener;
import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mystery2Listener implements ActionListener {
    private ILog logger;
    private JTextField textField;
    private JTabbedPane tabbedPane;
    private SocketIOServer socketInstance;

    public Mystery2Listener(ILog logger, JTextField textField, JTabbedPane tabbedPane, SocketIOServer socketInstance) {
        this.logger = logger;
        this.textField = textField;
        this.tabbedPane = tabbedPane;
        this.socketInstance = socketInstance;
    }

    public void actionPerformed(ActionEvent e) {
        if (ChatListener.secret == null || ChatListener.secret.isEmpty()) {
            logger.error("Über den Socket wurde noch keine Nachricht empfangen, deshalb kann das hier noch gar nicht gelöst werden!");
            return;
        }
        logger.log("Rätsel 2 wurde mit folgender Lösung versucht: " + textField.getText());

        if (textField.getText().equals(ChatListener.secret)) {
            NavigatorHelper.navigate(tabbedPane);
            logger.log("Unlocked mystery 3");
            this.socketInstance.getBroadcastOperations().sendEvent("mystery2-unlocked", true);
        } else {
            logger.error("Falsche Eingabe!");
        }
    }
}

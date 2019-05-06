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
        System.out.println("Mistery 2 Listener fired!");
        if (ChatListener.secret == null || ChatListener.secret.isEmpty()) {
            logger.error("Socket Message not received yet, therefore no secret was computed.");
            return;
        }
        logger.log("Tried mystery 2 with answer: " + textField.getText());

        if (textField.getText().equals(ChatListener.secret)) {
            NavigatorHelper.navigate(tabbedPane);
            logger.log("Unlocked mystery 3");
            this.socketInstance.getBroadcastOperations().sendEvent("mystery2-unlocked", true);
        } else {
            logger.error("INCORRECT");
        }
    }
}

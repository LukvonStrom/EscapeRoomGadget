package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.websocket.SocketIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocketToggleListener implements ActionListener {

    JButton startServerButton;
    JButton stopServerButton;
    SocketIO socketInstance;

    public SocketToggleListener(JButton startServerButton, JButton stopServerButton, SocketIO socketInstance) {
        this.startServerButton = startServerButton;
        this.stopServerButton = stopServerButton;
        this.socketInstance = socketInstance;
    }

    public static void setButtonState(SocketIO socketInstance, JButton startServerButton, JButton stopServerButton) {
        System.out.println("Server running:" + socketInstance.isRunning);
        startServerButton.setEnabled(!socketInstance.isRunning);
        stopServerButton.setEnabled(socketInstance.isRunning);
    }

    public void actionPerformed(ActionEvent e) {
        if (socketInstance.isRunning) {
            System.exit(0);
            setButtonState(socketInstance, startServerButton, stopServerButton);
        } else {
            socketInstance.start();
            setButtonState(socketInstance, startServerButton, stopServerButton);
        }
    }
}

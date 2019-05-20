package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.http.EscapeRequests;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class NavigationListener implements ChangeListener {
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private TopListRefreshListener topListRefreshListener;
    private JTextField groupNameField;
    private EscapeRequests escapeRequests;
    private Timer topListRefreshTimer;

    public NavigationListener(JTabbedPane tabbedPane1, JTextField textField1, JTextField textField2, TopListRefreshListener topListRefreshListener, JTextField groupNameField, EscapeRequests escapeRequests) {
        this.tabbedPane1 = tabbedPane1;
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.topListRefreshListener = topListRefreshListener;
        this.groupNameField = groupNameField;
        this.escapeRequests = escapeRequests;
        this.topListRefreshTimer = new Timer(60000, topListRefreshListener);
    }

    public void stateChanged(ChangeEvent e) {
        int index = tabbedPane1.getSelectedIndex();
        if (topListRefreshTimer.isRunning()) topListRefreshTimer.stop();
        System.out.println("Navigiert zu Seite Nr." + index);
        switch (index) {
            case 3:
                EventQueue.invokeLater(() -> {
                    textField1.grabFocus();
                    textField1.requestFocusInWindow();//or inWindow
                });
                break;
            case 4:
                EventQueue.invokeLater(() -> {
                    textField2.grabFocus();
                    textField2.requestFocusInWindow();//or inWindow
                });
                break;
            case 7:
                if (groupNameField.getText().length() > 0) {
                    try {
                        escapeRequests.end(groupNameField.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                topListRefreshTimer.setInitialDelay(0);
                topListRefreshTimer.start();
                break;
        }
    }
}

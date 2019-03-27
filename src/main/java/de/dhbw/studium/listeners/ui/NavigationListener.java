package de.dhbw.studium.listeners.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class NavigationListener implements ChangeListener {
    JTabbedPane tabbedPane1;
    JTextField textField1;
    JTextField textField2;

    public NavigationListener(JTabbedPane tabbedPane1, JTextField textField1, JTextField textField2) {
        this.tabbedPane1 = tabbedPane1;
        this.textField1 = textField1;
        this.textField2 = textField2;
    }

    public void stateChanged(ChangeEvent e) {
        int index = tabbedPane1.getSelectedIndex();
        switch (index) {
            case 2:
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        textField1.grabFocus();
                        textField1.requestFocusInWindow();//or inWindow
                    }
                });
                break;
            case 3:
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        textField2.grabFocus();
                        textField2.requestFocusInWindow();//or inWindow
                    }
                });
                break;
            case 4:
                break;
        }
    }
}

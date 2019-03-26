package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
    private JTextField textField1;
    private JTextField textField2;
    private JTabbedPane jTabbedPane;
    private ILog logger;
    private JTextArea logArea;

    public ResetListener(JTextField textField1, JTextField textField2, JTabbedPane jTabbedPane, ILog logger, JTextArea logArea) {
        this.textField1 = textField1;
        this.textField2 = textField2;
        this.jTabbedPane = jTabbedPane;
        this.logger = logger;
        this.logArea = logArea;
    }

    public void actionPerformed(ActionEvent e) {
        textField1.setText("");
        textField2.setText("");
        logArea.setText("");
        jTabbedPane.setSelectedIndex(1);
        jTabbedPane.setEnabledAt(2, true);

        for (int i = 3; i < jTabbedPane.getTabCount(); i++) {
            jTabbedPane.setEnabledAt(i, false);
        }
        logger.log("Application Reset!");
    }
}

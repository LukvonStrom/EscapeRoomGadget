package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mystery2Listener implements ActionListener {
    ILog logger;
    JTextField textField;
    JTabbedPane tabbedPane;

    public Mystery2Listener(ILog logger, JTextField textField, JTabbedPane tabbedPane) {
        this.logger = logger;
        this.textField = textField;
        this.tabbedPane = tabbedPane;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Mistery 2 Listener fired!");
        System.out.println(textField.getText());
        logger.log("Tried mystery 2 with answer: " + textField.getText());
        if(textField.getText().equals("HI")){
            NavigatorHelper.navigate(tabbedPane);
            logger.log("Unlocked mystery 3");
        }
    }
}

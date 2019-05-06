package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mystery1Listener implements ActionListener {
    ILog logger;
    JTextField textField1;
    JTabbedPane tabbedPane1;

    public Mystery1Listener(ILog logger, final JTextField textField1, JTabbedPane tabbedPane1) {
        this.logger = logger;
        this.textField1 = textField1;
        this.tabbedPane1 = tabbedPane1;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Mistery1 Listener fired!");
        System.out.println(textField1.getText());
        logger.log("Tried mystery 1 with answer: " + textField1.getText());
        if (textField1.getText().equals("1142265")) {
            NavigatorHelper.navigate(tabbedPane1);
            logger.log("Unlocked mystery 2");
        } else {
            logger.error("Incorrect");
        }
    }

}

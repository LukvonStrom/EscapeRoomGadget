package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mystery1Listener implements ActionListener {
    private ILog logger;
    private JTextField textField1;
    private JTabbedPane tabbedPane1;

    public Mystery1Listener(ILog logger, final JTextField textField1, JTabbedPane tabbedPane1) {
        this.logger = logger;
        this.textField1 = textField1;
        this.tabbedPane1 = tabbedPane1;
    }

    public void actionPerformed(ActionEvent e) {
        logger.log("Rätsel 1 wurde mit folgender Lösung versucht: " + textField1.getText());
        if (textField1.getText().equals("1142265")) {
            NavigatorHelper.navigate(tabbedPane1);
            logger.log("Rätsel 2 freigeschalten");
        } else {
            logger.error("Falsche Eingabe!");
        }
    }

}

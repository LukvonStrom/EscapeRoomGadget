package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.log.ILog;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetListener implements ActionListener {
    private JTabbedPane jTabbedPane;
    private ILog logger;

    private JComponent[] components;

    public ResetListener(JTabbedPane jTabbedPane, ILog logger, JComponent[] components) {
        this.jTabbedPane = jTabbedPane;
        this.logger = logger;
        this.components = components;
    }

    public void actionPerformed(ActionEvent e) {
        for (JComponent component : components) {
            if (component instanceof JTextComponent) {
                ((JTextComponent) component).setText("");
            } else if (component instanceof JSlider) {
                ((JSlider) component).setValue(((JSlider) component).getMinimum());
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            }
        }
        jTabbedPane.setSelectedIndex(1);
        jTabbedPane.setEnabledAt(2, true);

        for (int i = 3; i < jTabbedPane.getTabCount(); i++) {
            jTabbedPane.setEnabledAt(i, false);
        }
        logger.success("Application Reset!");
    }
}

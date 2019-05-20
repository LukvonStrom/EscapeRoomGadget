package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.log.ILog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mystery3Listener implements ActionListener {
    private JSlider yearSlider;
    private JTextField arbeitsGruppeTextField;
    private JComboBox monthDropDown;
    private ILog logger;
    private JTabbedPane tabbedPane;

    public Mystery3Listener(JSlider yearSlider, JTextField arbeitsGruppeTextField, JComboBox monthDropDown, ILog logger, JTabbedPane tabbedPane) {
        this.yearSlider = yearSlider;
        this.arbeitsGruppeTextField = arbeitsGruppeTextField;
        this.monthDropDown = monthDropDown;
        this.logger = logger;
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (yearSlider.getValue() == 1980 && arbeitsGruppeTextField.getText().equals("11") && monthDropDown.getSelectedIndex() == 1) {
            NavigatorHelper.navigate(tabbedPane);
            logger.success("Richtig!");
            logger.log("Rätsel 3 abgeschlossen.");
        } else {
            logger.error("Falsche Eingabe!");
        }
    }
}

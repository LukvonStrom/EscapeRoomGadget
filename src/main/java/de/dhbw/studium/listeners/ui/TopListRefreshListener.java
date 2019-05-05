package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.http.EscapeRequests;
import de.dhbw.studium.http.TopListObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TopListRefreshListener implements ActionListener {
    JTextArea topListArea;
    EscapeRequests escapeRequests;
    JTable table;

    public TopListRefreshListener(JTextArea topListArea, EscapeRequests escapeRequests, JTable table) {
        this.topListArea = topListArea;
        this.escapeRequests = escapeRequests;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        System.out.println("Called Refresh Listener");
//        StringBuilder stringBuilder = new StringBuilder();
        try {
            /*for (TopListObject topListObject : this.escapeRequests.getTop()) {
                stringBuilder.append(topListObject.toString());
            }*/

            for (TopListObject topListObject : this.escapeRequests.getTop()) {
                model.addRow(topListObject.toArray());
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        this.topListArea.setText(stringBuilder.toString());
    }
}

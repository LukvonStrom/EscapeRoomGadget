package de.dhbw.studium.listeners.ui;

import de.dhbw.studium.http.EscapeRequests;
import de.dhbw.studium.http.TopListObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TopListRefreshListener implements ActionListener {
    JTextArea topListArea;
    EscapeRequests escapeRequests;

    public TopListRefreshListener(JTextArea topListArea, EscapeRequests escapeRequests) {
        this.topListArea = topListArea;
        this.escapeRequests = escapeRequests;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Called Refresh Listener");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (TopListObject topListObject : this.escapeRequests.getTop()) {
                stringBuilder.append(topListObject.toString());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.topListArea.setText(stringBuilder.toString());
    }
}

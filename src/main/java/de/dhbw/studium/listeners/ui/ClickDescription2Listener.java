package de.dhbw.studium.listeners.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClickDescription2Listener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://amritb.github.io/socketio-client-tool"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
    }
}

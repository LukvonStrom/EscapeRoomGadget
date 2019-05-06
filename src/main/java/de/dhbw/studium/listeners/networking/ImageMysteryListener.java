package de.dhbw.studium.listeners.networking;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.log.ILog;

import javax.swing.*;

public class ImageMysteryListener implements DataListener<String> {
    private ILog logger;
    private JTabbedPane jTabbedPane;

    public ImageMysteryListener(ILog logger, JTabbedPane jTabbedPane) {
        this.logger = logger;
        this.jTabbedPane = jTabbedPane;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, String string, AckRequest ackRequest) throws Exception {
        this.logger.log("Received Image Solution: " + string);
        boolean solved = string.equals("110011110");
        socketIOClient.sendEvent("image-binary", solved);
        if (solved) NavigatorHelper.navigate(jTabbedPane);

    }
}

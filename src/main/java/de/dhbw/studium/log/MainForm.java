package de.dhbw.studium.log;

import de.dhbw.studium.listeners.ui.*;
import de.dhbw.studium.websocket.SocketIO;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainForm implements ILog {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextArea logArea;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea ex2description;
    private JTextArea ex1Description;
    private JTextArea ex3Desc;
    private JButton resetButton;
    private SocketIO socketInstance;
    private SocketToggleListener socketToggleListener;

    public MainForm() {
        textField1.addActionListener(new Mystery1Listener(this, textField1, tabbedPane1));
        textField2.addActionListener(new Mystery2Listener(this, textField2, tabbedPane1));
        resetButton.addActionListener(new ResetListener(textField1, textField2, tabbedPane1, this, logArea));
        tabbedPane1.addChangeListener(new NavigationListener(tabbedPane1, textField1, textField2));
    }

    public void setSocketInstance(SocketIO socketInstance) {
        this.socketInstance = socketInstance;
        SocketToggleListener.setButtonState(socketInstance, startServerButton, stopServerButton);
        stopServerButton.addActionListener(new SocketToggleListener(startServerButton, stopServerButton, socketInstance));
        startServerButton.addActionListener(new SocketToggleListener(startServerButton, stopServerButton, socketInstance));
    }

    public void main(String[] args) {
        try {
            JFrame frame = new JFrame("Escape Room Gadget");
            frame.setContentPane(panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }catch (Exception e){
            System.out.println("Error while initializing");
            e.printStackTrace();
        }
    }

    public void log(String str) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateString = format.format(new Date());
        logArea.append("[" + dateString + "] " + str + " \n");
    }
}

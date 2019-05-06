package de.dhbw.studium.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.dhbw.studium.http.EscapeRequests;
import de.dhbw.studium.listeners.NavigatorHelper;
import de.dhbw.studium.listeners.ui.*;
import de.dhbw.studium.log.ILog;
import de.dhbw.studium.websocket.SocketIO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class MainForm implements ILog {
    public JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextArea logArea;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea ex2description;
    private JTextArea ex1Description;
    private JButton resetButton;
    private JComboBox monthDropDown;
    private JSlider yearSlider;
    private JTextField arbeitsgruppeTextField;
    private JButton submitMystery4Button;
    private JButton beginEscapeButton;
    private JTextField groupNameField;
    private JTextArea descriptionMystery4Area;
    private JTextArea topListArea;
    private JTextArea mystery3Description;
    private JTable topListTable;
    private SocketIO socketInstance;
    private SocketToggleListener socketToggleListener;
    private EscapeRequests escapeRequests = new EscapeRequests(this);

    public MainForm() {
        $$$setupUI$$$();
        textField1.addActionListener(new Mystery1Listener(this, textField1, tabbedPane1));
        JComponent[] resettableComponents = {textField1, textField2, logArea, monthDropDown, yearSlider, arbeitsgruppeTextField};
        resetButton.addActionListener(new ResetListener(tabbedPane1, this, resettableComponents));
        tabbedPane1.addChangeListener(new NavigationListener(tabbedPane1, textField1, textField2, new TopListRefreshListener(topListArea, escapeRequests, topListTable), groupNameField, escapeRequests));
        ex2description.addMouseListener(new ClickDescription2Listener());
        submitMystery4Button.addActionListener(new Mystery3Listener(yearSlider, arbeitsgruppeTextField, monthDropDown, this, tabbedPane1));
        beginEscapeButton.addActionListener(e -> {
            System.out.println("Pressed the button," + groupNameField.getText());
            try {
                escapeRequests.start(groupNameField.getText());
                NavigatorHelper.navigate(tabbedPane1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    public void setSocketInstance(SocketIO socketInstance) {
        this.socketInstance = socketInstance;
        SocketToggleListener.setButtonState(socketInstance, startServerButton, stopServerButton);
        stopServerButton.addActionListener(new SocketToggleListener(startServerButton, stopServerButton, socketInstance));
        startServerButton.addActionListener(new SocketToggleListener(startServerButton, stopServerButton, socketInstance));
        textField2.addActionListener(new Mystery2Listener(this, textField2, tabbedPane1, socketInstance.getServer()));
    }

    public void main(String[] args) {
        try {
            JFrame frame = new JFrame("Escape Room Gadget");
            frame.setContentPane(panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error while initializing");
            e.printStackTrace();
        }
    }

    @Override
    public void log(String str) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String dateString = format.format(new Date());
        logArea.append("[" + dateString + "] " + str + " \n");
    }

    @Override
    public void error(String errorMessage) {
        logArea.setBackground(Color.RED);
        logArea.setForeground(Color.WHITE);
        log(errorMessage);
        logArea.setForeground(Color.BLACK);
        logArea.setBackground(Color.WHITE);
        JOptionPane.showMessageDialog(tabbedPane1.getSelectedComponent(), errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void success(String successMessage) {
        log(successMessage);
        JOptionPane.showMessageDialog(tabbedPane1.getSelectedComponent(), successMessage, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createUIComponents() {
        Vector<String> columns = new Vector<String>();
        columns.add("Gruppen Name");
        columns.add("Zeit in Sekunden");
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> innerVector = new Vector<String>();
        innerVector.add("");
        innerVector.add("");
        data.add(innerVector);

        topListTable = new JTable(data, columns) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };


    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setEnabled(true);
        panel1.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(800, 750), new Dimension(800, 750), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(8, 8, 8, 8), -1, -1));
        tabbedPane1.addTab("Logs", panel2);
        logArea = new JTextArea();
        logArea.setEditable(false);
        panel2.add(logArea, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Controls", panel3);
        final Spacer spacer3 = new Spacer();
        panel3.add(spacer3, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        startServerButton = new JButton();
        startServerButton.setEnabled(false);
        startServerButton.setText("Start Server");
        panel3.add(startServerButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stopServerButton = new JButton();
        stopServerButton.setEnabled(false);
        stopServerButton.setText("Stop Server");
        panel3.add(stopServerButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel3.add(spacer4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel3.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel3.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel3.add(spacer7, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setToolTipText("Reset the Application");
        panel3.add(resetButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Gruppenname", panel4);
        groupNameField = new JTextField();
        panel4.add(groupNameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        beginEscapeButton = new JButton();
        beginEscapeButton.setText("Spiel beginnen.");
        panel4.add(beginEscapeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Rätsel 1", panel5);
        tabbedPane1.setEnabledAt(3, false);
        final Spacer spacer8 = new Spacer();
        panel5.add(spacer8, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textField1 = new JTextField();
        panel5.add(textField1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ex1Description = new JTextArea();
        ex1Description.setEditable(false);
        ex1Description.setText("Als erste Aufgabe soll bitte die Hexadezimalzahl 235FB in das 7er System umgerechnet werden.");
        panel5.add(ex1Description, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel5.add(spacer9, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panel5.add(spacer10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel5.add(spacer11, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setEnabled(true);
        tabbedPane1.addTab("Rätsel 2", panel6);
        tabbedPane1.setEnabledAt(4, false);
        textField2 = new JTextField();
        textField2.setText("");
        panel6.add(textField2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ex2description = new JTextArea();
        ex2description.setEditable(false);
        ex2description.setLineWrap(true);
        ex2description.setText("In dieser Aufgabe sollt ihr den Webclient benutzen welchen ihr via NFC aufrufen könnt.\n\nBitte benutzt den Chat um eine Nachricht an den Server zu senden, der Server wird mit einer einzigartigen Chiffre antworten, die ihr hier eingeben sollt.\n\nWebsocket Server läuft auf Port (1136,5*8)");
        panel6.add(ex2description, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(1199, 50), null, 0, false));
        final Spacer spacer12 = new Spacer();
        panel6.add(spacer12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panel6.add(spacer13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Rätsel 3", panel7);
        tabbedPane1.setEnabledAt(5, false);
        mystery3Description = new JTextArea();
        mystery3Description.setText("Bitte die  Webanwendung nutzen um das Ergebnis des\nBilderrätsels in binärer Form einzugeben. \n(Schaltet hier automatisch nächstes Rätsel frei)");
        panel7.add(mystery3Description, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer14 = new Spacer();
        panel7.add(spacer14, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(23, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setEnabled(true);
        tabbedPane1.addTab("Rätsel 4", panel8);
        tabbedPane1.setEnabledAt(6, false);
        final Spacer spacer15 = new Spacer();
        panel8.add(spacer15, new GridConstraints(0, 2, 13, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        yearSlider = new JSlider();
        yearSlider.setInverted(false);
        yearSlider.setMajorTickSpacing(1);
        yearSlider.setMaximum(1985);
        yearSlider.setMinimum(1970);
        yearSlider.setPaintLabels(true);
        yearSlider.setPaintTicks(true);
        yearSlider.setSnapToTicks(true);
        yearSlider.setValue(1970);
        panel8.add(yearSlider, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        arbeitsgruppeTextField = new JTextField();
        arbeitsgruppeTextField.setForeground(new Color(-4473925));
        arbeitsgruppeTextField.setText("");
        arbeitsgruppeTextField.setToolTipText("Arbeitsgruppe");
        panel8.add(arbeitsgruppeTextField, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descriptionMystery4Area = new JTextArea();
        descriptionMystery4Area.setEditable(false);
        descriptionMystery4Area.setText("Wlan Standard Arbeitsgruppennummer, Monat & Jahr der Verabschiedung");
        panel8.add(descriptionMystery4Area, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(75, 50), null, 0, false));
        final Spacer spacer16 = new Spacer();
        panel8.add(spacer16, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        panel8.add(spacer17, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        monthDropDown = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("1");
        defaultComboBoxModel1.addElement("2");
        defaultComboBoxModel1.addElement("3");
        defaultComboBoxModel1.addElement("4");
        defaultComboBoxModel1.addElement("5");
        defaultComboBoxModel1.addElement("6");
        defaultComboBoxModel1.addElement("7");
        defaultComboBoxModel1.addElement("8");
        defaultComboBoxModel1.addElement("9");
        defaultComboBoxModel1.addElement("10");
        defaultComboBoxModel1.addElement("11");
        defaultComboBoxModel1.addElement("12");
        monthDropDown.setModel(defaultComboBoxModel1);
        panel8.add(monthDropDown, new GridConstraints(3, 1, 4, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitMystery4Button = new JButton();
        submitMystery4Button.setText("Submit");
        panel8.add(submitMystery4Button, new GridConstraints(14, 1, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        panel8.add(spacer18, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(2);
        label1.setHorizontalTextPosition(2);
        label1.setText("Arbeitsgruppen Nr. der IEEE ");
        label1.setVerticalAlignment(1);
        label1.setVerticalTextPosition(1);
        panel8.add(label1, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Monat der Verabschiedung des WLAN Standards");
        panel8.add(label2, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Jahr der Verabschiedung des WLAN Standards");
        panel8.add(label3, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel8.add(separator1, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        final JSeparator separator2 = new JSeparator();
        panel8.add(separator2, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(-1, 20), new Dimension(-1, 20), 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setEnabled(true);
        tabbedPane1.addTab("Ende", panel9);
        tabbedPane1.setEnabledAt(7, false);
        topListArea = new JTextArea();
        topListArea.setEditable(false);
        topListArea.setText("Vielen Dank fürs Spielen!");
        panel9.add(topListArea, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer19 = new Spacer();
        panel9.add(spacer19, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        panel9.add(spacer20, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer21 = new Spacer();
        panel9.add(spacer21, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel9.add(scrollPane1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        topListTable.setAutoCreateRowSorter(true);
        topListTable.setFillsViewportHeight(false);
        topListTable.setShowVerticalLines(true);
        scrollPane1.setViewportView(topListTable);
        label1.setLabelFor(arbeitsgruppeTextField);
        label2.setLabelFor(monthDropDown);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

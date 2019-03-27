package de.dhbw.studium.listeners;

import javax.swing.*;

public class NavigatorHelper {
    public static void navigate(JTabbedPane tabbedPane1) {
        if (tabbedPane1.getTabCount() > tabbedPane1.getTabCount() + 1) {
            return;
        }
        int actualIndex = tabbedPane1.getSelectedIndex();
        tabbedPane1.setEnabledAt(actualIndex + 1, true);
        tabbedPane1.setSelectedIndex(actualIndex + 1);
        tabbedPane1.setEnabledAt(actualIndex, false);
    }
}

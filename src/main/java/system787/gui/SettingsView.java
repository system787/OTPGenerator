package system787.gui;

import system787.Application;
import system787.gui.models.OTPListModel;
import system787.gui.models.SettingsListCellRenderer;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SettingsView extends JFrame {
    private final Application context;
    private final List<OTPAccount> accountList;
    private JPanel settingsPanel;
    private JList settingsListPanel;
    private OTPListModel settingsListModel;
    private final SettingsListCellRenderer listCellRenderer;


    public SettingsView(Application appContext) {
        context = appContext;
        accountList = appContext.getAccountsList();
        listCellRenderer = new SettingsListCellRenderer(context);
        setUpFrame();
        setUpData();
        setUpList();
    }

    private void setUpData() {
        settingsListModel = new OTPListModel();
        for (OTPAccount account : accountList) {
            settingsListModel.add(account);
        }
        settingsListModel.add(new OTPAccount(-99, "", "", ""));
    }

    private void setUpFrame() {
        JFrame frame = new JFrame("");
        frame.setMinimumSize(new Dimension(470, 600));
        frame.setContentPane(this.settingsPanel);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", MainColors.APP_COLOR_BACKGROUND);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setUpList() {
        settingsListPanel.setFocusable(false);
        settingsListPanel.setModel(settingsListModel);
        settingsListPanel.setCellRenderer(listCellRenderer);
    }
}

package system787.gui;

import system787.Application;
import system787.gui.models.OTPListModel;
import system787.gui.models.SettingsListCellRenderer;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class SettingsView extends JFrame {
    private final Application context;
    private final List<OTPAccount> accountList;
    private JPanel settingsPanel;
    private JList deleteList;
    private JPanel addAccountPanel;
    private JTextField serviceTextField;
    private JTextField accountTextField;
    private JTextField keyTextField;
    private JLabel serviceLabel;
    private JLabel accountLabel;
    private JLabel keyLabel;
    private JScrollPane deleteScrollPane;
    private JButton cancelButton;
    private JButton showAddPanelButton;
    private JPanel addButtonPanel;
    private JButton saveButton;
    private OTPListModel settingsListModel;
    private final SettingsListCellRenderer listCellRenderer;


    public SettingsView(Application appContext) {
        context = appContext;
        accountList = appContext.getAccountsList();
        listCellRenderer = new SettingsListCellRenderer(context);
        setUpViews();
    }

    private void setUpViews() {
        setUpFrame();
        setUpData();
        setUpButtons();
    }

    private void setUpData() {
        settingsListModel = new OTPListModel();
        for (OTPAccount account : accountList) {
            settingsListModel.add(account);
        }

        deleteScrollPane.setBorder(null);
        deleteList.setModel(settingsListModel);
        deleteList.setCellRenderer(listCellRenderer);

        settingsListModel.add(new OTPAccount(1, "testservice.com", "system787", "123klsadgslnmui4g"));
    }

    private void setUpButtons() {
        setUpShowAddPanelButton();
        setUpCancelButton();
        setUpSaveButton();
        setUpCancelButton();
    }

    private void setUpShowAddPanelButton() {
        showAddPanelButton.addActionListener(e -> {
            addButtonPanel.setVisible(false);
            addAccountPanel.setVisible(true);
        });
    }

    private void setUpCancelButton() {
        cancelButton.addActionListener(e -> {
            serviceTextField.setText("");
            accountTextField.setText("");
            keyTextField.setText("");

            addAccountPanel.setVisible(false);
            addButtonPanel.setVisible(true);
        });
    }

    private void setUpSaveButton() {

    }

    private void setUpFrame() {
        JFrame frame = new JFrame("");
        frame.setMinimumSize(new Dimension(470, 600));
        frame.setContentPane(this.settingsPanel);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", MainColors.APP_COLOR_BACKGROUND);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                context.getMainView().setMainPanelVisible(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        frame.setVisible(true);
    }


}

package system787.gui;

import system787.Application;
import system787.FontSize;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;

public class SettingsRowView extends JPanel {
    private Application context;
    private OTPAccount account;
    private JPanel settingsRowPanel;
    private JLabel websiteLabel;
    private JLabel accountLabel;
    private JButton deleteButton;

    public SettingsRowView() {
    }

    public void init(Application appContext, OTPAccount acc) {
        context = appContext;
        account = acc;

        setUpViews();
        setUpButton();
    }

    private void setUpButton() {
        deleteButton.addActionListener(e -> {
            context.deleteAccount(account.getId());
            deleteButton.setEnabled(false);
        });
    }

    private void setUpViews() {
        websiteLabel.setText(account.getService());
        websiteLabel.setFont(context.getFont(FontSize.M));
        accountLabel.setText(account.getAccount());
        accountLabel.setFont(context.getFont(FontSize.M));
    }


    public JPanel getView() {
        return settingsRowPanel;
    }
}

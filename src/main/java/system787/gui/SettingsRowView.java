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

    public void initDelete(Application appContext, OTPAccount acc) {
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

    public void initAdd(Application appContext) {
        context = appContext;
        settingsRowPanel = new JPanel();
        settingsRowPanel.setMinimumSize(new Dimension(420, 80));
        settingsRowPanel.setPreferredSize(new Dimension(420, 80));
        settingsRowPanel.setBackground(MainColors.APP_COLOR_BACKGROUND);

        JPanel addPanel = new JPanel();
        addPanel.setSize(420, -1);
        BoxLayout boxLayout = new BoxLayout(addPanel, BoxLayout.Y_AXIS);
        addPanel.setLayout(boxLayout);
        addPanel.setBackground(MainColors.APP_COLOR_BACKGROUND);


        JPanel servicePanel = new JPanel();
        servicePanel.setLayout(new FlowLayout());
        servicePanel.setSize(420, -1);
        servicePanel.setBackground(MainColors.APP_COLOR_BACKGROUND);

        JLabel serviceLabel = new JLabel("Service :");
        serviceLabel.setFont(context.getFont(FontSize.S));
        serviceLabel.setForeground(Color.WHITE);
        servicePanel.add(serviceLabel);

        JTextField serviceTextField = new JTextField();
        serviceTextField.setSize(380, 30);
        serviceTextField.setFont(context.getFont(FontSize.S));
        serviceTextField.setBorder(null);
        serviceTextField.setEditable(true);
        serviceTextField.setEnabled(true);
        serviceTextField.setBackground(MainColors.APP_COLOR_FOREGROUND);
        servicePanel.add(serviceTextField);
        addPanel.add(servicePanel);

        JPanel accountPanel = new JPanel();
        accountPanel.setLayout(new FlowLayout());
        accountPanel.setSize(420, -1);
        accountPanel.setBackground(MainColors.APP_COLOR_BACKGROUND);

        JLabel accountLabel = new JLabel("Service :");
        accountLabel.setFont(context.getFont(FontSize.S));
        accountLabel.setForeground(Color.WHITE);
        accountPanel.add(serviceLabel);

        JTextField accountTextField = new JTextField();
        accountTextField.setSize(380, 30);
        accountTextField.setFont(context.getFont(FontSize.S));
        accountTextField.setBorder(null);
        accountTextField.setEditable(true);
        accountTextField.setEnabled(true);
        accountTextField.setBackground(MainColors.APP_COLOR_FOREGROUND);
        accountPanel.add(serviceTextField);
        addPanel.add(accountPanel);

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new FlowLayout());
        keyPanel.setSize(420, -1);
        keyPanel.setBackground(MainColors.APP_COLOR_BACKGROUND);

        JLabel keyLabel = new JLabel("Service :");
        keyLabel.setFont(context.getFont(FontSize.S));
        keyLabel.setForeground(Color.WHITE);
        keyPanel.add(serviceLabel);

        JTextField keyTextField = new JTextField();
        keyTextField.setSize(380, 30);
        keyTextField.setFont(context.getFont(FontSize.S));
        keyTextField.setBorder(null);
        keyTextField.setEditable(true);
        keyTextField.setEnabled(true);
        keyTextField.setBackground(MainColors.APP_COLOR_FOREGROUND);
        keyPanel.add(serviceTextField);
        addPanel.add(keyPanel);

        JButton addButton = new JButton("Save");
        addButton.setSize(40, 30);
        addButton.setFont(context.getFont(FontSize.S));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(MainColors.APP_COLOR_BACKGROUND_LIGHT);
        addButton.addActionListener(e -> {
            String service = serviceTextField.getText();
            String account = accountTextField.getText();
            String key = keyTextField.getText();
            if (!service.isEmpty() && !account.isEmpty() && !key.isEmpty()) {
                context.addAccount(service, account, key);
                addButton.setEnabled(false);
            }
        });

        addPanel.add(addButton);

        settingsRowPanel.add(addPanel);
    }

    public JPanel getView() {
        return settingsRowPanel;
    }
}

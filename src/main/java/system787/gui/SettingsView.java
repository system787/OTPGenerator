package system787.gui;

import system787.Application;
import system787.FontSize;
import system787.gui.models.OTPListModel;
import system787.gui.models.SettingsListCellRenderer;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SettingsView extends JDialog {
    private final Application context;
    private List<OTPAccount> accountList;
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
    private JButton closeButton;
    private OTPListModel settingsListModel;
    private final SettingsListCellRenderer listCellRenderer;


    public SettingsView(Application appContext) {
        context = appContext;
        listCellRenderer = new SettingsListCellRenderer(context);
        settingsListModel = new OTPListModel();
        setUpViews();
    }

    private void setUpViews() {
        setUpFrame();
        setUpData();
        setUpButtons();

        deleteScrollPane.setBorder(null);
        deleteScrollPane.setHorizontalScrollBar(null);
        deleteList.setModel(settingsListModel);
        deleteList.setCellRenderer(listCellRenderer);

        serviceLabel.setFont(context.getFont(FontSize.XS));
        accountLabel.setFont(context.getFont(FontSize.XS));
        keyLabel.setFont(context.getFont(FontSize.XS));

        serviceTextField.setFont(context.getFont(FontSize.XS));
        accountTextField.setFont(context.getFont(FontSize.XS));
        keyTextField.setFont(context.getFont(FontSize.XS));
    }

    private void setUpData() {
        accountList = context.getAccountsList();
        for (OTPAccount account : accountList) {
            settingsListModel.add(account);
        }
        deleteList.updateUI();
    }

    private void setUpButtons() {
        setUpShowAddPanelButton();
        setUpCancelButton();
        setUpSaveButton();
        setUpCancelButton();
        setUpCloseButton();
    }

    private void setUpShowAddPanelButton() {
        showAddPanelButton.addActionListener(e -> {
            showAddPanelButton();
        });
    }

    private void showAddPanelButton() {
        addButtonPanel.setVisible(false);
        addAccountPanel.setVisible(true);
    }

    private void setUpCancelButton() {
        cancelButton.addActionListener(e -> {
           cancelButton();
        });
    }

    private void cancelButton() {
        serviceTextField.setText("");
        accountTextField.setText("");
        keyTextField.setText("");

        addAccountPanel.setVisible(false);
        addButtonPanel.setVisible(true);
    }

    private void setUpCloseButton() {
        closeButton.addActionListener(e -> {
            closeButton();
        });
    }

    private void closeButton() {
        new MainView(context);
        this.dispose();
    }

    private void setUpSaveButton() {
        saveButton.addActionListener(e -> saveButton());
    }

    private void saveButton() {
        String service = serviceTextField.getText();
        String account = accountTextField.getText();
        String key = keyTextField.getText();

        if (!service.isEmpty()
                && !account.isEmpty()
                && !key.isEmpty()) {
            context.addAccount(service, account, key);
        }

        settingsListModel.clear();
        setUpData();
        cancelButton();
    }

    private void setUpFrame() {
        this.setMinimumSize(new Dimension(470, 600));
        this.setContentPane(this.settingsPanel);
        this.getRootPane().putClientProperty("JRootPane.titleBarBackground", MainColors.APP_COLOR_BACKGROUND);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        deleteList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listClicked(e.getPoint());
            }
        });
    }

    private void listClicked(Point point) {
        int index = deleteList.locationToIndex(point);
        OTPAccount account = (OTPAccount) deleteList.getModel().getElementAt(index);

        String deletePrompt = "Delete " + account.getAccount() + "?";
        Object[] options = { "Yes" , "No" };
        final int option = JOptionPane.showOptionDialog(this, deletePrompt, "", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[1]);

        if (option == 0) {
            settingsListModel.clear();
            context.deleteAccount(account);
            setUpData();
        }
    }
}

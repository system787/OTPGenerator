package system787.gui;

import com.formdev.flatlaf.ui.FlatScrollBarUI;
import system787.Application;
import system787.gui.models.OTPListCellRenderer;
import system787.gui.models.OTPListModel;
import system787.service.OTPAccount;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.List;


public class MainView extends JFrame {
    private final Application applicationContext;
    private final List<OTPAccount> accountsList;

    private JPanel mainPanel;
    private JScrollPane passwordScrollPane;
    private JPanel topPanel;

    private JList otpListPanel;
    private JProgressBar totpProgressBar;
    private JButton settingsButton;
    private OTPListModel otpListModel;
    private final OTPListCellRenderer otpListCellRenderer;

    public MainView(Application app) {
        applicationContext = app;
        accountsList = applicationContext.getAccountsList();
        otpListCellRenderer = new OTPListCellRenderer(applicationContext);
        setUpData();
        setUpViews();
    }

    private void setUpData() {
        otpListModel = new OTPListModel();
        for (OTPAccount a : accountsList) {
            otpListModel.add(a);
        }
    }

    private void setUpViews() {
        setUpFrame();
        setScrollPane();
        setUpList();
        setUpSettingsButton();
    }

    private void setUpSettingsButton() {
        settingsButton.addActionListener(e -> {
            applicationContext.getMainView().dispose();
            new SettingsView(applicationContext);
            setMainPanelVisible(false);
        });
    }

    private void setUpFrame() {
        JFrame frame = new JFrame("");
        frame.setMinimumSize(new Dimension(470, 600));
        frame.setContentPane(this.mainPanel);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", MainColors.APP_COLOR_BACKGROUND);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setScrollPane() {
        UIManager.put("ScrollBar.thumb", new ColorUIResource(MainColors.APP_COLOR_ACCENT));
        passwordScrollPane.setBorder(null);
        passwordScrollPane.getVerticalScrollBar().setUI(new FlatScrollBarUI());
        passwordScrollPane.getVerticalScrollBar().setBackground(MainColors.APP_COLOR_BACKGROUND);
    }

    private void setUpList() {
        otpListPanel.setModel(otpListModel);
        otpListPanel.setCellRenderer(otpListCellRenderer);
    }

    public void setMainPanelVisible(Boolean bool) {
        mainPanel.setVisible(bool);
    }
}

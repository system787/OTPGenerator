package system787.gui;

import com.formdev.flatlaf.ui.FlatScrollBarUI;
import system787.Application;
import system787.gui.models.OTPListCellRenderer;
import system787.gui.models.OTPListModel;
import system787.service.OTPAccount;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.InvalidKeyException;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


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

    private Timer timer;

    public MainView(Application app) {
        applicationContext = app;
        accountsList = applicationContext.getAccountsList();
        otpListCellRenderer = new OTPListCellRenderer(applicationContext);
        timer = new Timer();
        otpListModel = new OTPListModel();
        setUpData();
        setUpViews();
    }

    private void setUpData() {
        for (OTPAccount a : accountsList) {
            otpListModel.add(a);
        }
    }

    private void setUpViews() {
        setUpFrame();
        setScrollPane();
        setUpList();
        setUpSettingsButton();
        setUpProgressBar();
    }

    private void setUpProgressBar() {
        long currentTime = Instant.now().getEpochSecond() % 30;
        totpProgressBar.setValue((int) currentTime);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int current = totpProgressBar.getValue();
                System.out.println(current);
                if (current >= 30) {
                    totpProgressBar.setValue(0);
                    recalculateOTP();
                } else {
                    totpProgressBar.setValue(current + 1);
                }
            }
        };
        timer.scheduleAtFixedRate(task, Date.from(Instant.now().plusMillis(1000)), 1000);
    }

    private void setUpSettingsButton() {
        settingsButton.addActionListener(e -> {
            applicationContext.getMainView().dispose();
            new SettingsView(applicationContext);
            timer.cancel();
            this.dispose();
        });
    }

    private void setUpFrame() {
        this.setTitle("");
        this.setMinimumSize(new Dimension(470, 600));
        this.setContentPane(this.mainPanel);
        this.getRootPane().putClientProperty("JRootPane.titleBarBackground", MainColors.APP_COLOR_BACKGROUND);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
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

        otpListPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                copyToClipboard(e.getPoint());
            }
        });
    }

    private void copyToClipboard(Point point) {
        int index = otpListPanel.locationToIndex(point);
        OTPAccount account = (OTPAccount) otpListPanel.getModel().getElementAt(index);
        String otp = "";
        try {
            otp = applicationContext.getOTP(account.getKey());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        if (!otp.isEmpty()) {
            StringSelection ss = new StringSelection(otp);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, null);
        }
    }

    private void recalculateOTP() {
        otpListModel.clear();
        setUpData();
        otpListPanel.updateUI();
    }
}

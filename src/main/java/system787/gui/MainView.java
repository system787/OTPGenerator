package system787.gui;

import system787.Application;
import system787.gui.models.OTPListCellRenderer;
import system787.gui.models.OTPListModel;

import javax.swing.*;
import java.util.List;


public class MainView extends JFrame {
    private final Application applicationContext;
    private final List<String[]> accountsList;

    private JPanel mainPanel;
    private JScrollPane passwordScrollPane;
    private JPanel bottomPanel;

    private JList otpListPanel;
    private OTPListModel otpListModel;
    private DefaultListModel<String[]> listModel;
    private final OTPListCellRenderer otpListCellRenderer;

    public MainView(Application app) {
        applicationContext = app;
        accountsList = applicationContext.getAccountsList();
        otpListCellRenderer = new OTPListCellRenderer();
        setUpData();
        setUpViews();
    }

    private void setUpData() {
        otpListModel = new OTPListModel();
        listModel = new DefaultListModel<>();
        for (String[] s : accountsList) {
            otpListModel.add(s);
            listModel.addElement(s);
        }
    }

    private void setUpViews() {
        JFrame frame = new JFrame("javaOTP");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        passwordScrollPane.setBorder(null);

        otpListPanel.setModel(otpListModel);
        otpListPanel.setCellRenderer(otpListCellRenderer);
    }
}

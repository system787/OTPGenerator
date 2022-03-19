package system787.gui;

import javax.swing.*;
import java.awt.*;

public class OTPRowView extends JPanel {
    private JPanel rowPanel;
    private JLabel websiteLabel;
    private JPanel otpPanel;
    private JLabel otpLabel;
    private JLabel accountLabel;

    public void setWebsiteLabel(String text) {
        websiteLabel.setText(text);
    }

    public void setOtpLabel(String text) {
        otpLabel.setText(text);
    }

    public void setAccountLabel(String text) {
        accountLabel.setText(text);
    }

    public OTPRowView() {
        Font font = new Font("Jetbrains", Font.PLAIN, 20);
        otpLabel.setFont(font);
        websiteLabel.setFont(font);
        accountLabel.setFont(font);
    }

    public JPanel getView() {
        return rowPanel;
    }
}

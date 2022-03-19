package system787.gui;

import system787.Application;
import system787.FontSize;

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

    public void setCustomFont(Application context) {
        websiteLabel.setFont(context.getFont(FontSize.M));
        accountLabel.setFont(context.getFont(FontSize.S));
        otpLabel.setFont(context.getFont(FontSize.XL));
    }

    public JPanel getView() {
        return rowPanel;
    }
}

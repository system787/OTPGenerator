package system787.gui.models;

import system787.Application;
import system787.gui.OTPRowView;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;
import java.security.InvalidKeyException;

public class OTPListCellRenderer extends OTPRowView implements ListCellRenderer<OTPAccount> {
    private final Application applicationContext;

    public OTPListCellRenderer(Application context) {
        applicationContext = context;
    }

    @Override
    public Component getListCellRendererComponent(JList list, OTPAccount account, int index, boolean isSelected, boolean cellHasFocus) {
        OTPRowView rowView = new OTPRowView();
        rowView.setCustomFont(applicationContext);
        rowView.setWebsiteLabel(account.getService());
        rowView.setAccountLabel(account.getAccount());
        try {
            rowView.setOtpLabel(applicationContext.getOTP(account.getKey()));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return rowView.getView();
    }
}

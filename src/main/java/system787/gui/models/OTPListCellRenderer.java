package system787.gui.models;

import system787.Application;
import system787.gui.OTPRowView;

import javax.swing.*;
import java.awt.*;

public class OTPListCellRenderer extends OTPRowView implements ListCellRenderer<String[]> {
    private Application applicationContext;

    public OTPListCellRenderer(Application context) {
        applicationContext = context;
    }

    @Override
    public Component getListCellRendererComponent(JList list, String[] strings, int index, boolean isSelected, boolean cellHasFocus) {
        OTPRowView rowView = new OTPRowView();
        rowView.setCustomFont(applicationContext);
        rowView.setWebsiteLabel(strings[0]);
        rowView.setAccountLabel(strings[1]);
        rowView.setOtpLabel(strings[2]);
        return rowView.getView();
    }
}

package system787.gui.models;

import system787.gui.OTPRowView;

import javax.swing.*;
import java.awt.*;

public class OTPListCellRenderer extends OTPRowView implements ListCellRenderer<String[]> {
    @Override
    public Component getListCellRendererComponent(JList list, String[] strings, int index, boolean isSelected, boolean cellHasFocus) {
        OTPRowView rowView = new OTPRowView();
        rowView.setWebsiteLabel(strings[0]);
        rowView.setAccountLabel(strings[1]);
        rowView.setOtpLabel(strings[2]);
        return rowView.getView();
    }
}

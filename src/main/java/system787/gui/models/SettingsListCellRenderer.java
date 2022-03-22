package system787.gui.models;

import system787.Application;
import system787.gui.SettingsRowView;
import system787.service.OTPAccount;

import javax.swing.*;
import java.awt.*;

public class SettingsListCellRenderer extends SettingsRowView implements ListCellRenderer<OTPAccount> {
    private final Application applicationContext;

    public SettingsListCellRenderer(Application context) {
        applicationContext = context;
    }

    @Override
    public Component getListCellRendererComponent(JList list, OTPAccount acc, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        SettingsRowView settingsRowView = new SettingsRowView();
        if (acc.getId() == -99) {
            settingsRowView.initAdd(applicationContext);
        } else {
            settingsRowView.initDelete(applicationContext, acc);
        }

        return settingsRowView.getView();
    }
}

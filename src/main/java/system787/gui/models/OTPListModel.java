package system787.gui.models;

import system787.gui.OTPRowView;
import system787.service.OTPAccount;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class OTPListModel implements ListModel {
    private final List<OTPAccount> otpList;
    private final List<ListDataListener> listeners;

    public OTPListModel() {
        otpList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void add(OTPAccount a) {
        otpList.add(a);
    }

    public void clear() {
        otpList.clear();
    }

    @Override
    public int getSize() {
        return otpList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return otpList.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
}

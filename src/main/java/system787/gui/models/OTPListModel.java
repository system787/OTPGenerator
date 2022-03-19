package system787.gui.models;

import system787.gui.OTPRowView;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class OTPListModel implements ListModel {
    private List<String[]> otpList;
    private List<ListDataListener> listeners;

    public OTPListModel() {
        otpList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void add(String[] s) {
        otpList.add(s);
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

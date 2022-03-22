package system787;

import com.formdev.flatlaf.FlatDarkLaf;
import system787.flatlaf.Dark;
import system787.gui.MainColors;
import system787.gui.MainView;
import system787.service.DBManager;
import system787.service.OTPAccount;
import system787.service.OTPGenerator;
import system787.service.TimeService;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Application {

    private MainView mainView;

    private DBManager dbManager;
    private OTPGenerator otpGenerator;
    private TimeService timeService;

    private List<OTPAccount> accountsList;
    private Font font_24;
    private Font font_22;
    private Font font_20;
    private Font font_18;

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        otpGenerator = OTPGenerator.getInstance();
        timeService = TimeService.getInstance();
        dbManager = DBManager.getInstance(this);
        accountsList = dbManager.getAllAccounts();
        FlatDarkLaf.registerCustomDefaultsSource("system787.flatlaf");
        Dark.setup();

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(Application.class.getResourceAsStream("/fonts/Code-New-Roman.otf")));
            font_24 = font.deriveFont(Font.PLAIN, 24);
            font_22 = font.deriveFont(Font.PLAIN, 22);
            font_20 = font.deriveFont(Font.PLAIN, 20);
            font_18 = font.deriveFont(Font.PLAIN, 18);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        mainView = new MainView(this);
    }

    public Font getFont(FontSize size) {
        return switch (size) {
            case XL -> font_24;
            case L -> font_22;
            case M -> font_20;
            case S -> font_18;
        };
    }

    public String getOTP(String key) throws InvalidKeyException {
        return otpGenerator.getOTP(key);
    }

    public void deleteAccount(int id) {

    }

    public MainView getMainView() {
        return mainView;
    }

    public void addAccount(String service, String account, String key) {
        dbManager.insert(service, account, key);
    }

    public ArrayList<OTPAccount> getAccountsList() {
        return new ArrayList<>(accountsList);
    }

    public void exit() {
        System.exit(0);
    }
}

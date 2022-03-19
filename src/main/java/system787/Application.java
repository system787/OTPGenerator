package system787;

import system787.gui.MainView;
import system787.service.OTPGenerator;
import system787.service.TimeService;

import java.awt.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private MainView mainView;
    private OTPGenerator otpGenerator;
    private TimeService timeService;
    private List<String[]> accountsList;
    private Font font;

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        otpGenerator = OTPGenerator.getInstance();
        timeService = TimeService.getInstance();
        accountsList = otpGenerator.getAccounts();

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Application.class.getResourceAsStream("/fonts/Code-New-Roman.otf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        mainView = new MainView(this);
    }

    public Font getFont() {
        return font;
    }
    
    public String getOTP(String key) throws InvalidKeyException {
        return otpGenerator.getOTP(key);
    }

    public ArrayList<String[]> getAccountsList() {
        ArrayList<String[]> passwordList = new ArrayList<>(accountsList);
        for (String[] s : passwordList) {
            try {
                s[2] = getOTP(s[2]);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(accountsList);
    }

    private MainView getMainView() {
        return mainView;
    }

    public void exit() {
        System.exit(0);
    }
}

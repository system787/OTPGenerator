import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import org.apache.commons.codec.binary.Base32;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
    File format for keys:
        secret.keys placed in src/main/resources
        format: Site Account Key
        e.g.: Google GoogleGuy123 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */

public class OTPGenerator {
    final int passwordLength = 6;
    final TimeBasedOneTimePasswordGenerator totp;

    private List<String[]> keys;

    public OTPGenerator() throws NoSuchAlgorithmException {
        Duration timeStep = Duration.ofSeconds(30);
        totp = new TimeBasedOneTimePasswordGenerator(timeStep, passwordLength, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
        keys = getKeyStrings();
    }

    public void generateAllTOTP() {
        if (keys.isEmpty()) {
            throw new NoSuchElementException();
        }

        for (String[] s : keys) {
            System.out.println(s[0] + " | Account: " + s[1]);
            try {
                String key = generateTOTP(s[2]);
                String[] keySplit = key.split(" ");
                System.out.println("Current: " + keySplit[0] + " | Next: " + keySplit[1]);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String[]> getKeyStrings() {
        InputStream file = getClass().getClassLoader().getResourceAsStream("secret.keys");
        InputStreamReader in = new InputStreamReader(file, StandardCharsets.UTF_8);

        List<String[]> keys = new ArrayList<>();

        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String key = scanner.nextLine();
            String[] splitKey = key.split(" ");
            keys.add(splitKey);
        }

        return keys;
    }

    private String generateTOTP(String keyString) throws InvalidKeyException {
        Base32 base32 = new Base32();
        byte[] decoded = base32.decode(keyString);
        Key key = new SecretKeySpec(decoded, "AES");

        final Instant now = Instant.now();
        final Instant later = now.plus(totp.getTimeStep());

        String first = totp.generateOneTimePasswordString(key, now);
        String second = totp.generateOneTimePasswordString(key, later);

        return (first + " " + second);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        OTPGenerator test = new OTPGenerator();
        test.generateAllTOTP();
    }
}

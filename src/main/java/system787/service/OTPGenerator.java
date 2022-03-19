package system787.service;

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
import java.util.Scanner;

/**
    File format for keys:
        secret.keys placed in src/main/resources
        format: Site Account Key
        e.g.: Google GoogleGuy123 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 */

public class OTPGenerator {
    private static OTPGenerator INSTANCE = null;

    private final TimeBasedOneTimePasswordGenerator totp;
    private final Base32 base32;

    private static final String AES = "AES";

    private OTPGenerator() throws NoSuchAlgorithmException {
        Duration timeStep = Duration.ofSeconds(30);
        int passwordLength = 6;
        totp = new TimeBasedOneTimePasswordGenerator(timeStep, passwordLength, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
        base32 = new Base32();
    }

    public static OTPGenerator getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new OTPGenerator();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public String getOTP(String keyString) throws InvalidKeyException {
        byte[] decoded = base32.decode(keyString);
        Key key = new SecretKeySpec(decoded, AES);

        return totp.generateOneTimePasswordString(key, Instant.now());
    }
}

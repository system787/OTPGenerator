package system787.service;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService {
    private static TimeService instance = null;
    private Instant startTime;

    private TimeService() {
        startTime = Instant.now();
    }

    public static TimeService getInstance() {
        if (instance == null) {
            instance = new TimeService();
        }

        return instance;
    }


}

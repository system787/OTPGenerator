package system787.service;

public class TimeService {
    private static TimeService instance = null;

    private TimeService() {
    }

    public static TimeService getInstance() {
        if (instance == null) {
            instance = new TimeService();
        }

        return instance;
    }


}

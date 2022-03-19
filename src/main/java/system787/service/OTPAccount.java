package system787.service;

public class OTPAccount {
    private int id;
    private String service;
    private String account;
    private String key;

    public OTPAccount(int id, String service, String account, String key) {
        this.id = id;
        this.service = service;
        this.account = account;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public String getAccount() {
        return account;
    }

    public String getKey() {
        return key;
    }
}

package system787.service;

import system787.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private final Application context;
    private static DBManager INSTANCE = null;

    private final String database_name = "otp.db";
    private final String table_name = "accounts_table";

    private Connection connection;

    private DBManager(Application context) {
        this.context = context;
        startUpTasks();
    }

    public static DBManager getInstance(Application context) {
        if (INSTANCE == null) {
            INSTANCE = new DBManager(context);
        }
        return INSTANCE;
    }

    private void startUpTasks() {
        if (connection == null) {
            try {
                connection = getConnection();
                createTable(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite::resource:otp.db";
        connection = DriverManager.getConnection(dbUrl);
        connection.setCatalog(database_name);
        return connection;
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable(Connection con) throws SQLException {
        String sql =
                "CREATE TABLE IF NOT EXISTS " + table_name + " ("
                        + "id INTEGER PRIMARY KEY, "
                        + "service TEXT NOT NULL, "
                        + "account TEXT NOT NULL, "
                        + "key TEXT NOT NULL"
                        + ");";

        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String[] data) {
        insert(data[0], data[1], data[2]);
    }

    public List<OTPAccount> getAllAccounts() {
        List<OTPAccount> accountList = new ArrayList<>();
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM accounts_table;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                accountList.add(new OTPAccount(resultSet.getInt("id"),
                        resultSet.getString("service"),
                        resultSet.getString("account"),
                        resultSet.getString("key")));
            }
            resultSet.close();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public int insert(String service, String account, String key) {
        int tableSizeAfterInsertion = -1;
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO accounts_table (service, account, key) VALUES ('"
                    + service + "', '"
                    + account + "', '"
                    + key + "');";
            stmt.executeUpdate(sql);

            sql = "SELECT COUNT(DISTINCT id) AS total FROM accounts_table;";
            ResultSet result = stmt.executeQuery(sql);
            tableSizeAfterInsertion = result.getInt("total");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableSizeAfterInsertion;
    }

    public void delete(OTPAccount account) {
        delete(account.getId());
    }

    public void delete(int id) {
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM accounts_table where id = " + id + ";";
            stmt.executeUpdate(sql);
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

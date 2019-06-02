package part02.lesson02;

public class Queries {
    public static final String createLogsTable = "CREATE TABLE LOGS " +
            "(id VARCHAR(100), " +
            " date DATE, " +
            " log_level VARCHAR(10), " +
            " message VARCHAR(1000), " +
            " exception VARCHAR(1000), " +
            " PRIMARY KEY (id))";
    public static final String createTable1 = "CREATE TABLE \"USER\" " +
            "(id INTEGER, " +
            " name VARCHAR(255), " +
            " birthday DATE, " +
            " login_ID INTEGER, " +
            " city VARCHAR(255), " +
            " email VARCHAR(255), " +
            " description VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
    public static final String createTable2 = "CREATE TABLE ROLE " +
            "(id INTEGER, " +
            " name VARCHAR(255) CHECK (name IN ('Administration', 'Clients', 'Billing')), " +
            " description VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
    public static final String createTable3 = "CREATE TABLE USER_ROLE " +
            "(id INTEGER, " +
            " user_id INTEGER, " +
            " role_id INTEGER, " +
            " PRIMARY KEY ( id ))";
}

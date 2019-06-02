package part02.lesson02;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static BasicDataSource dataSource;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUsername("postgres");
            dataSource.setPassword("sferastok1");
        }
        return dataSource.getConnection();
    }
}

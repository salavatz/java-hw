package part02.lesson03.task01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private static Connection connection = null;
    private Main main = new Main();

    @BeforeAll
    public static void initAll() {
        try {
            connection = ConnectorDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void connectionTest() {
        assertTrue(connection != null);
    }

    @Test
    void createTableTest() {
        //main.createTable();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM \"USER\"");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] names = {"", "id", "name", "birthday", "login_id", "city", "email", "description"};
            for (int i = 1; i <= columnCount; i++) {
                assertEquals(names[i], rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readTable() {
        String insertTable2 = "INSERT INTO ROLE VALUES (2, 'Clients', 'description')";
        String selectTable2 = "SELECT * FROM ROLE WHERE id = 2";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertTable2);
            ResultSet rs = statement.executeQuery(selectTable2);
            rs.next();
            assertEquals(2, rs.getInt("id"));
            assertEquals("Clients", rs.getString("name"));
            assertEquals("description", rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void updateTable() {
        String updateTable2 = "UPDATE ROLE SET name = 'Administration', description = 'description new' WHERE id=2";
        String selectTable2 = "SELECT * FROM ROLE WHERE id = 2";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(updateTable2);
            ResultSet rs = statement.executeQuery(selectTable2);
            rs.next();
            assertEquals(2, rs.getInt("id"));
            assertEquals("Administration", rs.getString("name"));
            assertEquals("description new", rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Test
    void deleteTable() {
        String deleteTable2 = "DELETE FROM ROLE WHERE id=2";
        String selectTable2 = "SELECT * FROM ROLE WHERE id = 2";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteTable2);
            ResultSet rs = statement.executeQuery(selectTable2);
            assertFalse(rs.next());
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @AfterAll
    public static void tearDownAll() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

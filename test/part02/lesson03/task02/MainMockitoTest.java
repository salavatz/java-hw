package part02.lesson03.task02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MainMockitoTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private ResultSetMetaData rsmd;

    private Role role;

    private String[] columns = {"id", "name", "description"};

    @BeforeEach
    public void initAll() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

            role = new Role(2, "Clients", "description");
            when(resultSet.first()).thenReturn(true);
            when(resultSet.getInt(1)).thenReturn(role.getId());
            when(resultSet.getString(2)).thenReturn(role.getName());
            when(resultSet.getString(3)).thenReturn(role.getDescription());
            when(preparedStatement.executeQuery()).thenReturn(resultSet);

            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(any(String.class))).thenReturn(resultSet);
            when(resultSet.getMetaData()).thenReturn(rsmd);
            when(rsmd.getColumnCount()).thenReturn(3);
            when(rsmd.getColumnName(1)).thenReturn(columns[0]);
            when(rsmd.getColumnName(2)).thenReturn(columns[1]);
            when(rsmd.getColumnName(3)).thenReturn(columns[2]);

            when(preparedStatement.executeQuery(any(String.class))).thenReturn(resultSet);
            when(resultSet.getInt("id")).thenReturn(2);
            when(resultSet.getString("name")).thenReturn("Administration");
            when(resultSet.getString("description")).thenReturn("description new");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createRole() {
        Database database = new Database(connection);
        String[] columnsFromDB = database.createTable();
        for (int i = 0; i < columnsFromDB.length; i++) {
            assertEquals(columns[i], columnsFromDB[i]);
        }
        try {
            verify(statement, Mockito.times(1)).executeQuery(any(String.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void readRole() {
        Database database = new Database(connection);
        Role roleFromDB = database.readTable(2);
        assertEquals(role, roleFromDB);
    }

    @Test
    void updateRole() {
        Database database = new Database(connection);
        Role roleForUpdate = new Role(2, "Administration", "description new");
        Role roleFromDB = database.updateTable(roleForUpdate);
        assertEquals(roleForUpdate, roleFromDB);
    }

    @Test
    void spyTest() {
        Role role = spy(new Role(1,"ad","ds"));
        when(role.getId()).thenReturn(2);
        role.getId();
        verify(role).getId();

        assertEquals(2, role.getId());
        verify(role, atLeast(2)).getId();
    }
}

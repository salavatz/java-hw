package part02.lesson03.task02;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    String[] createTable() {
        String selectTable2 = "SELECT * FROM ROLE";
        String[] columns = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTable2);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            columns = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columns[i - 1] = rsmd.getColumnName(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    Role readTable(int id) {
        String selectTable2 = "SELECT * FROM ROLE WHERE id = ?";
        Role role = null;
        try {
            PreparedStatement statement = connection.prepareStatement(selectTable2);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.first()) {
                return null;
            }
            role = new Role(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return role;
    }

    Role updateTable(Role role) {
        String updateTable2 = "UPDATE ROLE SET name=?, description=? WHERE id=?";
        String selectTable2 = "SELECT * FROM ROLE WHERE id = 2";
        Role resultRole = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(updateTable2);
            stmt.setString(1, role.getName());
            stmt.setString(2, role.getDescription());
            stmt.setInt(3, role.getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.executeQuery(selectTable2);
            rs.next();
            resultRole = new Role(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultRole;
    }
}


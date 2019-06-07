package part02.lesson03.task01;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        createTable();
        //insertIntoTableWithBatch();
        //insertIntoTableWithParameters();
        //getTableColumns(1212, "Bob");
        //transactionWithSavePoints();
        //transactionWithSavePointsAndException();
    }

    public static void createTable() {
        Connection connection = null;
        try {
            connection = ConnectorDB.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(Queries.createTable1);
            statement.executeUpdate(Queries.createTable2);
            statement.executeUpdate(Queries.createTable3);
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
    }

    public static void insertIntoTableWithBatch() {
        Connection connection = null;
        try {
            connection = ConnectorDB.getConnection();
            Statement statement = connection.createStatement();
            statement.addBatch("INSERT INTO USER_ROLE VALUES (2, 2, 2)");
            statement.addBatch("INSERT INTO \"USER\" VALUES (2, 'Bob', '2001-02-04', 1212, 'Berlin', 'bob@gmail.com', 'description')");
            statement.addBatch("INSERT INTO ROLE VALUES (2, 'Clients', 'description')");
            statement.executeBatch();
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
    }

    public static void insertIntoTableWithParameters() {
        Connection connection = null;
        String insertTable3 = "INSERT INTO USER_ROLE (id, user_id, role_id) VALUES (?, ?, ?)";
        try {
            connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertTable3);
            statement.setInt(1, 4);
            statement.setInt(2, 4);
            statement.setInt(3, 4);
            statement.executeUpdate();
            statement.close();
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
    }

    public static void getTableColumns(int login_ID, String name) {
        Connection connection = null;
        String selectTable1 = "SELECT * FROM \"USER\" WHERE login_ID = ? AND name = ?";
        try {
            connection = ConnectorDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectTable1);
            statement.setInt(1, login_ID);
            statement.setString(2, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Birthday: " + rs.getDate("birthday"));
                System.out.println("Login_ID: " + rs.getInt("login_ID"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Description: " + rs.getString("description"));
            }
            rs.close();
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
    }

    public static void transactionWithSavePoints() {
        Connection connection = null;
        Savepoint savepoint = null;
        String name = "Alex";
        int login_ID = 1111;
        int id = 2;
        String updateTable1 = "UPDATE \"USER\" SET name=?, login_ID=? WHERE id=?";
        String insertTable1 = "INSERT INTO \"USER\" (id, name, birthday, login_ID, city, email, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = ConnectorDB.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(updateTable1);
            statement.setString(1, name);
            statement.setInt(2, login_ID);
            statement.setInt(3, id);
            statement.executeUpdate();
            savepoint = connection.setSavepoint("a");
            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            PreparedStatement statement = connection.prepareStatement(insertTable1);
            statement.setInt(1, 5);
            statement.setString(2, "Bill");
            statement.setDate(3, Date.valueOf("2005-01-02"));
            statement.setInt(4, 2412);
            statement.setString(5, "London");
            statement.setString(6, "bob@gmail.com");
            statement.setString(7, "description");
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transactionWithSavePointsAndException() {
        Connection connection = null;
        String name = "Billing";
        int id = 2;
        String updateTable2 = "UPDATE ROLE SET name=? WHERE id=?";
        String insertTable2 = "INSERT INTO ROLE (id, name, description) VALUES (?, ?, ?)";
        Savepoint savepoint = null;
        try {
            connection = ConnectorDB.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(updateTable2);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
            savepoint = connection.setSavepoint("a");
            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            PreparedStatement statement = connection.prepareStatement(insertTable2);
            statement.setInt(1, 3);
            statement.setInt(2, 24); //ОШИБКА, добавили число вместо строки
            statement.setString(3, "description");
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        try {
            connection.commit();
            connection.setAutoCommit(true);
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
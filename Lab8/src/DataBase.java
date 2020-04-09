import java.sql.*;

class Database {
    private static Connection connection = null;

    private void DataBase() {
    }

    public static Connection establishConnection() {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dba", "sql");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return connection;
    }
}
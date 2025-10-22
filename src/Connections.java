import java.sql.*;

public class Connections {
    static ResultSet JDBCConnection(String dbName, String password, String sql) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        String user = "root";
        //String password = "12345";  // ÄNDRA!

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    static ResultSet JDBCConnection(String sql) throws SQLException{

        String url = "jdbc:mysql://localhost:3306/mediatypetest";
        String user = "root";
        String password = "12345";

        Connection conn = DriverManager.getConnection(url, user, password);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
}

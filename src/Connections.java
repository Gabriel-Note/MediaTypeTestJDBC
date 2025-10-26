import java.sql.*;

public class Connections {
    static ResultSet JDBCQueryConnection(String dbName, String password, String sql) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        String user = "root";
        //String password = "12345";  // ÄNDRA!

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    static Connection JDBCConnection() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/mediatypetest";
        String user = "root";
        String password = "12345";

        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    static ResultSet JDBCQueryConnection(String sql) throws SQLException{

        String url = "jdbc:mysql://localhost:3306/mediatypetest";
        String user = "root";
        String password = "12345";

        Connection conn = DriverManager.getConnection(url, user, password);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    // Obsolete
    static ResultSet existingConnectionGetResultSet(Connection conn, String sql) throws SQLException{

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    // Obsolete
    static Statement JDBCUpdateConnection(String sql) throws SQLException{

        String url = "jdbc:mysql://localhost:3306/mediatypetest";
        String user = "root";
        String password = "12345";

        Connection conn = DriverManager.getConnection(url, user, password);

        Statement stmt = conn.createStatement();
        return stmt;
    }

    static PreparedStatement preparedJDBCUpdateConnection(String query) throws SQLException{

        String url = "jdbc:mysql://localhost:3306/mediatypetest";
        String user = "root";
        String password = "12345";

        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return pstmt;
    }

    static PreparedStatement existingConnectionGetPreparedStatement(Connection conn, String query) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        return pstmt;
    }
}

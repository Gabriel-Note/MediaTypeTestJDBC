import java.sql.ResultSet;
import java.sql.SQLException;

public class Helper {
    static int lastInsertedId(){
     String sql = "SELECT LAST_INSERT_ID();";
     int id = -1;
        try(ResultSet rs = Connections.JDBCQueryConnection(sql)){
            id = rs.getInt("LAST_INSERT_ID()");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }


}

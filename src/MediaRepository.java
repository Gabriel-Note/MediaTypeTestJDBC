import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class MediaRepository {
    public ArrayList<Media> showAllMedia(){
        String sql = "SELECT * FROM mediatypetest.media;";

        ArrayList<Media> media = new ArrayList<>();

        try(ResultSet rs = Connections.JDBCQueryConnection(sql)){

            while(rs.next()){
                int id = rs.getInt("media_id");
                String title = rs.getString("title");
                int publishingYear = rs.getInt("publishing_year");
//                System.out.println("pub year: " + publishingYear);
                double price = rs.getDouble("price");
                String language = rs.getString("language");
                String mediaType = rs.getString("media_type");


                media.add(new Media(id, title, mediaType, publishingYear));
            }
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
        return media;
    }

    public void insertNewMedia(String title, String mediaType){
        String sql =
                "INSERT INTO media (title, media_type) " +
                "VALUES('"+title+"', '"+mediaType+"')";

        try(Statement stmt = Connections.JDBCUpdateConnection(sql)){
            int rowsUpdated = stmt.executeUpdate(sql);
            System.out.println("rad uppdaterad " + rowsUpdated);
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
    }

    public int insertNewMediaGetKey(String title){
        int mediaId = -1;
        String sql = """
        INSERT INTO media (title, media_type)
        VALUES(?, 'E-book')
        """;

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){
            pstmt.setString(1, title);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("rad uppdaterad " + rowsUpdated);
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if(generatedKeys.next()){
                    mediaId = generatedKeys.getInt(1);
                }
            }

        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
        return mediaId;
    }
}

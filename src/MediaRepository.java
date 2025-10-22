import java.sql.*;
import java.util.ArrayList;

public class MediaRepository {
    public ArrayList<Media> showAllMedia(){
        String sql = "SELECT * FROM mediatypetest.media;";

        ArrayList<Media> media = new ArrayList<>();

        try(ResultSet rs = Connections.JDBCConnection(sql)){

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
    }
}

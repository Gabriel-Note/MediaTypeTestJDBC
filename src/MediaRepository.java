import java.sql.*;
import java.util.ArrayList;

public class MediaRepository {


    public void showMediaWithTitle(String search){
        String sql = """
                SELECT * FROM mediatypetest.media
                WHERE title LIKE ?;
                """;

//        ArrayList<Media> media = new ArrayList<>();

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setString(1, "%" + search + "%");
            ResultSet rs = pstmt.executeQuery();

            final int COL1 = 8, COL2 = 30, COL3 = 18, COL4 = 10, COL5 = 15, COL6 = 12;
            System.out.printf(
                "%-" + COL1 + "s " +
                "%-" + COL2 + "s " +
                "%-" + COL3 + "s " +
                "%-" + COL4 + "s " +
                "%-" + COL5 + "s " +
                "%-" + COL6 + "s ",
                "MediaID", "Title", "publishing Year", "Price", "Language", "Media Type"
            );
            System.out.println();

            while(rs.next()){
                int id = rs.getInt("media_id");
                String title = rs.getString("title");
                int publishingYear = rs.getInt("publishing_year");
                double price = rs.getDouble("price");
                String language = rs.getString("language");
                String mediaType = rs.getString("media_type");

                System.out.printf(
                    "%-" + COL1 + "s " +
                    "%-" + COL2 + "s " +
                    "%-" + COL3 + "s " +
                    "%-" + COL4 + "s " +
                    "%-" + COL5 + "s " +
                    "%-" + COL6 + "s\n",
                    id, title, publishingYear, price, language, mediaType
                );
//                media.add(new Media(id, title, mediaType, publishingYear));
            }
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
    }

    // Obsolete
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

    // Göra en insert i databasen och returnera id:et för den skapade raden för att kunna användas senare i andra klasser som Foreign key
    public int insertNewMediaGetKey(Connection conn, String title, MediaType mediaType){
        int mediaId = -1;
        String sql = """
        INSERT INTO media (title, media_type)
        VALUES(?, ?)
        
        """;

        try(PreparedStatement pstmt = Connections.existingConnectionGetPreparedStatement(conn, sql)){

            pstmt.setString(1, title);
            pstmt.setString(2, mediaType.getMediaType());



            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("Media uppdaterad");
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if(generatedKeys.next()){
                    mediaId = generatedKeys.getInt(1);
                }
            }
            System.out.println("Medianyckel: " + mediaId);
        }
        catch (SQLException e){
            System.out.println("Fel vid skapande av Media");
            e.printStackTrace();
        }
        return mediaId;
    }

    // Obsolete
    public int insertNewMediffffffaGetKey(String title, MediaType mediaType){
        int mediaId = -1;
        String sql = """
        INSERT INTO media (title, media_type)
        VALUES(?, ?)
        """;

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setString(1, title);
            pstmt.setString(2, mediaType.getMediaType());

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

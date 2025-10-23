import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    public void showAllBooks(){

        String sql = """
                SELECT *
                FROM mediatypetest.books
                JOIN media m ON m.media_id = books.media_id;
                """;

        try(ResultSet rs = Connections.JDBCQueryConnection(sql)){
            while(rs.next()){
                int bookId = rs.getInt("book_id");
                int mediaId = rs.getInt("media_id");
                int publishingYear = rs.getInt("publishing_year");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");

                System.out.println(
                    "BookID: " + bookId + "\n" +
                    "MediaID: " + mediaId + "\n" +
                    "Publish year : " + publishingYear + "\n" +
                    "title: " + title + "\n" +
                    "ISBN: " + isbn
                );

            }
        }
        catch (SQLException e){

        }
    }
}

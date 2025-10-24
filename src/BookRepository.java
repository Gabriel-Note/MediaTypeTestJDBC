import java.sql.*;

public class BookRepository extends MediaRepository{
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
            e.printStackTrace();
        }
    }

    public void insertNewBook(String isbn, int pages) {

        String sql = """
                INSERT INTO mediatypetest.books (media_id, ISBN, pages)
                VALUES (?, ?, ?);
                """;
        try(Connection conn = Connections.JDBCConnection();){
            conn.setAutoCommit(false); // kör vår BEGIN; i sql
            int mediaId = insertNewMediaGetKey(conn,"hungry henry 2", MediaType.BOOK);

            try(PreparedStatement pstmt = Connections.existingConnection(conn, sql)){
                pstmt.setInt(1, mediaId);
                pstmt.setString(2, isbn);
                pstmt.setInt(3, pages);

                int rowsUpdated = pstmt.executeUpdate();
                System.out.println("1st execute");

                /*pstmt.setInt(1, 14);
                pstmt.setString(2, "868591");
                pstmt.setInt(3, pages);

                int rowsUpdated = pstmt.executeUpdate();
                System.out.println("2nd execute");*/

                if (rowsUpdated > 0){
                    System.out.println("Book uppdaterad");
                }
                conn.commit();
            }
            catch(SQLException e){
                try{
                    conn.rollback(); // Rollback om något går fel
                    System.out.println("rollback lyckas");
                }
                catch (SQLException ex){
                    System.out.println("rollback misslyckades");
                    ex.printStackTrace();
                }
                System.out.println("Fel uppstod, återställer ändringar");
                e.printStackTrace();
//                throw e;
            }
            finally {
                conn.setAutoCommit(true);
            }
        }
        catch (SQLException e){
            System.out.println("Kan inte koppla till databasen?");
            e.printStackTrace();
        }
    }
}

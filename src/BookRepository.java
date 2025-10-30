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

    public void showBooksWithTitle(String search){

        String sql = """
                SELECT *
                FROM mediatypetest.books
                JOIN media m ON m.media_id = books.media_id
                WHERE m.title LIKE ?;
                """;

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setString(1, ("%" + search + "%"));
            ResultSet rs = pstmt.executeQuery();

            final int COL1 = 7, COL2 = 7, COL3 = 30, COL4 = 18, COL5 = 7, COL6 = 7, COL7 = 12, COL8 = 14;
            System.out.printf(
                    "%-" + COL1 + "s " +
                            "%-" + COL2 + "s " +
                            "%-" + COL3 + "s " +
                            "%-" + COL4 + "s " +
                            "%-" + COL5 + "s " +
                            "%-" + COL6 + "s " +
                            "%-" + COL7 + "s " +
                            "%-" + COL8 + "s ",
                    "BookID", "MediaID", "Title", "publishing Year", "Pages", "Price", "Language", "ISBN"
            );
            System.out.println();

            while(rs.next()){
                int bookId = rs.getInt("book_id");
                int mediaId = rs.getInt("media_id");
                int publishingYear = rs.getInt("publishing_year");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                int pages = rs.getInt("pages");
                double price = rs.getDouble("price");
                String language = rs.getString("language");

                System.out.printf(
                        "%-" + COL1 + "s " +
                                "%-" + COL2 + "s " +
                                "%-" + COL3 + "s " +
                                "%-" + COL4 + "s " +
                                "%-" + COL5 + "s " +
                                "%-" + COL6 + "s " +
                                "%-" + COL7 + "s " +
                                "%-" + COL8 + "s\n",
                        bookId, mediaId, title, publishingYear, pages, price, language, isbn
                );
            }

//                media.add(new Media(id, title, mediaType, publishingYear));
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
    }

    public void insertNewBook(String title, String isbn, int pages) {

        String sql = """
                INSERT INTO mediatypetest.books (media_id, ISBN, pages)
                VALUES (?, ?, ?);
                """;
        try(Connection conn = Connections.JDBCConnection();){
            conn.setAutoCommit(false); // kör vår BEGIN; i sql
            int mediaId = insertNewMediaGetKey(conn, title, MediaType.BOOK);

            try(PreparedStatement pstmt = Connections.existingConnectionGetPreparedStatement(conn, sql)){
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

    public void updateBook(int bookId, String isbn, int pages){
        int intPages = 1; // kommer ge false i sql IF
        int intIsbn = 1;
        String sql = """
                UPDATE books
                SET ISBN = IF(1>?, ISBN, ?),
                    pages = IF(1>?, pages, ?)
                WHERE books.book_id = ?;
                """;

        // kommer ge true i sql IF
        if (isbn.trim().equals("") || isbn == null){
            intIsbn = 0;
        }
        if (pages == 0){
            intPages = 0;
        }

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setInt(1, intIsbn);
            pstmt.setString(2,isbn);
            pstmt.setInt(3, intPages);
            pstmt.setInt(4, pages);
            pstmt.setInt(5, bookId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("book updated");
            }
        }
        catch (SQLException e){
            System.out.println("Error updating book");
            e.printStackTrace();
        }
    }

    public void deleteBook(int mediaId){
        String sql = """
                DELETE FROM books
                WHERE book_id = ?;
                """;

        try(Connection conn = Connections.JDBCConnection();){
            conn.setAutoCommit(false); // kör vår BEGIN; i sql
            deleteMedia(conn, mediaId);

            try(PreparedStatement pstmt = Connections.existingConnectionGetPreparedStatement(conn, sql)){
                pstmt.setInt(1, mediaId);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0){
                    System.out.println("Book deleted");
                }
                conn.commit();
            }
            catch(SQLException e){
                conn.rollback();
                System.out.println("Error deleting book, changes reverted");
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

import java.sql.*;
import java.util.ArrayList;

public class LoanRepository extends MediaRepository{

    public void showAllLoans(){
        String sql = """
                SELECT * FROM mediatypetest.loans;
                """;


        try(ResultSet rs = Connections.JDBCQueryConnection(sql)){

            final int COL1 = 4, COL2 = 30, COL3 = 40, COL4 = 20, COL5 = 15, COL6 = 12;

            System.out.printf(
                "%-" + COL1 + "s " +
                "%-" + COL2 + "s " +
                "%-" + COL3 + "s " +
                "%-" + COL4 + "s " +
                "%-" + COL5 + "s " +
                "%-" + COL6 + "s ",
                "LoanID", "memberID", "MediaID", "Loan Date", "Due Date", "Return Date"
            );
            System.out.println();

            while(rs.next()){
                int loanId = rs.getInt("laon_id");
                int memberId = rs.getInt("member_id");
                int mediaId = rs.getInt("media_id");
                Date loanDate = rs.getDate("loan_date");
                Date dueDate = rs.getDate("due_date");
                Date returnDate = rs.getDate("return_date");

                System.out.printf(
                    "%-" + COL1 + "s " +
                    "%-" + COL2 + "s " +
                    "%-" + COL3 + "s " +
                    "%-" + COL4 + "s " +
                    "%-" + COL5 + "s " +
                    "%-" + COL6 + "s\n",
                    loanId, memberId, mediaId, loanDate, dueDate, returnDate
                );
            }
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
    }

    public void loanMedia(int memberId, int mediaId){
        String sql = """
                INSERT INTO loans (member_id, media_id, loan_date, due_date, return_date)
                VALUES (?, ?, curdate(), date_add(curdate(), interval 30 DAY), NULL);
                """;

        try(Connection conn = Connections.JDBCConnection();){
            conn.setAutoCommit(false); // kör vår BEGIN; i sql
            int isSuccess = updateMedia(conn, mediaId);
            if (isSuccess == -1){
                throw new SQLException("Not available");
            }
            try(PreparedStatement pstmt = Connections.existingConnectionGetPreparedStatement(conn, sql)){
                pstmt.setInt(1, memberId);
                pstmt.setInt(2, mediaId);

                int rowsUpdated = pstmt.executeUpdate();

                /*pstmt.setInt(1, 14);
                pstmt.setString(2, "868591");
                pstmt.setInt(3, pages);

                int rowsUpdated = pstmt.executeUpdate();
                System.out.println("2nd execute");*/

                if (rowsUpdated > 0){
                    System.out.println("Loan added");
                }
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
                conn.commit();
                conn.setAutoCommit(true);
            }
        }
        catch (SQLException e){
            System.out.println("\u001B[31mNot available\u001B[0m");
//            e.printStackTrace();
        }
    }
}

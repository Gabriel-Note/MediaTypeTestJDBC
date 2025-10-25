import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanRepository {

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
}

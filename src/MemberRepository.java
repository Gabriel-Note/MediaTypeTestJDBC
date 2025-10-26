import com.mysql.cj.jdbc.exceptions.MySQLStatementCancelledException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberRepository {
    public ArrayList<Member> showAllMembersGetList(){
        String sql = "SELECT * FROM mediatypetest.members;";

        ArrayList<Member> members = new ArrayList<>();

        try(ResultSet rs = Connections.JDBCQueryConnection(sql)){

            while(rs.next()){
                int id = rs.getInt("member_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String city = rs.getString("city");
                Date memberSince = rs.getDate("member_since");
                int activeLoans = rs.getInt("active_loans");

                members.add(new Member(id, name, email, city, memberSince, activeLoans));
            }
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
        return members;
    }

    public void showAllMembers(){
        String sql = """
                SELECT * FROM mediatypetest.members;
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
                "ID", "Name", "Email", "City", "Member Since", "Active Loans");
            System.out.println();

            while(rs.next()){
                int id = rs.getInt("member_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String city = rs.getString("city");
                Date memberSince = rs.getDate("member_since");
                int activeLoans = rs.getInt("active_loans");

                System.out.printf(
                    "%-" + COL1 + "s " +
                    "%-" + COL2 + "s " +
                    "%-" + COL3 + "s " +
                    "%-" + COL4 + "s " +
                    "%-" + COL5 + "s " +
                    "%-" + COL6 + "s\n",
                    id, name, email, city, memberSince, activeLoans
                );
            }
        }
        catch (SQLException e){
            System.out.println("Något blev fel");
            e.printStackTrace();
        }
    }
    public void updateMember(int memberId, String name, String email, String city){
        int intName = 1;
        int intEmail = 1;
        int intCity = 1;
        String sql = """
                UPDATE members
                SET name = IF(1>?, name, ?),
                    email = IF(1>?, email, ?),
                    city = IF(1>?, city, ?)
                WHERE members.member_id = ?;
                """;

        if (name.trim().equals("") || name == null){
            intName = 0;
        }
        if (email.trim().equals("") || email == null){
            intEmail = 0;
        }
        if (city.trim().equals("") || city == null){
            intCity = 0;
        }

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setInt(1, intName);
            pstmt.setString(2,name);
            pstmt.setInt(3, intEmail);
            pstmt.setString(4, email);
            pstmt.setInt(5, intCity);
            pstmt.setString(6, city);
            pstmt.setInt(7, memberId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("member updated");
            }
        }
        catch (SQLException e){
            System.out.println("Error updating member");
            e.printStackTrace();
        }
    }

    public void deleteMember(){
        String sql = """
                
                """;
    }

    // Tillfällig metod utan säkerhet för att testa så att det funkar, selection borde hanteras på annat ställe
    public void updateMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("You will type in all values that you want to change, \u001B[31mLEAVE FIELD EMPTY TO KEEP CURRENT INFORMATION\u001B[0m");
        System.out.println();

        System.out.println("Write the memberID number for which you want to update(required): ");
        SelectionHandling selectionHandling = new SelectionHandling();
        int memberId = selectionHandling.positiveInt();

        System.out.println("New name: ");
        String name = scanner.nextLine();

        System.out.println("New email: ");
        String email = scanner.nextLine();

        System.out.println("New city: ");
        String city = scanner.nextLine();

        int intName = 1;
        int intEmail = 1;
        int intCity = 1;
        String sql = """
                UPDATE members
                SET name = IF(1>?, name, ?),
                    email = IF(1>?, email, ?),
                    city = IF(1>?, city, ?)
                WHERE members.member_id = ?;
                """;

        if (name.trim().equals("") || name == null){
            intName = 0;
        }
        if (email.trim().equals("") || email == null){
            intEmail = 0;
        }
        if (city.trim().equals("") || city == null){
            intCity = 0;
        }

        try(PreparedStatement pstmt = Connections.preparedJDBCUpdateConnection(sql)){

            pstmt.setInt(1, intName);
            pstmt.setString(2,name);
            pstmt.setInt(3, intEmail);
            pstmt.setString(4, email);
            pstmt.setInt(5, intCity);
            pstmt.setString(6, city);
            pstmt.setInt(7, memberId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("member updated");
            }
        }
        catch (SQLException e){
            System.out.println("Error updating member");
            e.printStackTrace();
        }
    }


}

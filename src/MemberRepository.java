import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public void deleteMemeber(){
        String sql = """
                
                """;
    }
}

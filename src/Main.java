import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        BookRepository br = new BookRepository();
        MemberRepository mr = new MemberRepository();
        LoanRepository lr = new LoanRepository();

//        mr.showAllMembers();
        lr.showAllLoans();

        /*ArrayList<Member> memberRepository = new ArrayList<>();
        memberRepository = mr.showAllMembersGetList();
        for (Member member : memberRepository){
            System.out.println(
                member.getMemberId() + "\n" +
                member.getName() + "\n" +
                member.getEmail()+ "\n" +
                member.getCity()+ "\n" +
                member.getMemberSince()
            );
        }*/

    }
}
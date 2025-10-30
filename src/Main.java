import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookRepository br = new BookRepository();
        MemberRepository mr = new MemberRepository();
        LoanRepository lr = new LoanRepository();
        MediaRepository mediaRepository = new MediaRepository();
        MemberRepository memberRepository = new MemberRepository();


//        memberRepository.updateMember(1, "Erik Andersson", "erik.andersson@email.se", "Stockholm");
//        memberRepository.updateMember(10, "Linda Pettersson", "linda.pettersson@email.se", "Jönköping");
        Menu menu = new Menu();
        menu.mainSelection();

//        mediaRepository.showMediaWithTitle("f");

//        mr.showAllMembers();
//        lr.showAllLoans();

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
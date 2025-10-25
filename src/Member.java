import javax.xml.crypto.Data;
import java.util.Date;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private String city;
    private Date memberSince;
    private int activeLoans;

    public Member(int memberId, String name, String email, String city, Date memberSince, int activeLoans){
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.city = city;
        this.memberSince = memberSince;
        this.activeLoans = activeLoans;
    }

    public Member(int memberId, String name, String email, int activeLoans) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.activeLoans = activeLoans;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }
}

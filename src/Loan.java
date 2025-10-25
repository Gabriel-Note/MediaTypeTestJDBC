import java.util.Date;

public class Loan {

    private int loanId;
    private int memberId;
    private int mediaId;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private int activeLoans;

    public Loan(int loanId, int memberId, int mediaId, Date loanDate, Date dueDate, Date returnDate, int activeLoans) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.mediaId = mediaId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.activeLoans = activeLoans;
    }

    public Loan(int loanId, int memberId, int mediaId, int activeLoans) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.mediaId = mediaId;
        this.activeLoans = activeLoans;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}

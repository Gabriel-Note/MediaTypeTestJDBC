public class Book {
    private int bookId;
    private int mediaId;
    private String isbn;
    private int pages;

    public Book(int bookId, int mediaId, String isbn, int pages) {
        this.bookId = bookId;
        this.mediaId = mediaId;
        this.isbn = isbn;
        this.pages = pages;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}

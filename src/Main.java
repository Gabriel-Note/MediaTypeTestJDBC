public class Main {
    public static void main(String[] args) {

        BookRepository br = new BookRepository();

        br.showAllBooks();
        br.insertNewBook("1869327368", 234);

    }
}
import java.awt.print.Book;
import java.util.Scanner;

public class Menu {
    MediaRepository mediaRepository = new MediaRepository();
    BookRepository bookRepository = new BookRepository();
    MemberRepository memberRepository = new MemberRepository();


    public void mainSelection(){
        int currentSelection;
        String searchWord;
        Scanner scan = new Scanner(System.in);
        SelectionHandling selectionHandling = new SelectionHandling();
        while(true) {
            selectionHandling.printMainSelection();
            currentSelection = selectionHandling.positiveInt();
//            scan.nextLine();
            switch (currentSelection) {
                case 1:
                    System.out.println("Write searchword to search on title");
                    searchWord = scan.nextLine();
                    mediaRepository.showMediaWithTitle(searchWord);
                    break;
                case 2:
                    boolean loopCheck = true;
                    while (loopCheck){
                        selectionHandling.printMediaTypeSelection();
                        currentSelection = selectionHandling.positiveInt();
                        switch (currentSelection) {
                            case 1:
                                System.out.println("Write searchword to search on title");
                                searchWord = scan.nextLine();
                                bookRepository.showBooksWithTitle(searchWord);
                                break;
                            case 2:
                                System.out.println("Work In Progress");
                                break;
                            case 3:
                                System.out.println("Work In Progress");
                                break;
                            case 4:
                                System.out.println("Work In Progress");
                                break;
                            case 5:
                                System.out.println("Work In Progress");
                                break;
                            case 0:
                                loopCheck = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case 3:
                    memberRepository.showAllMembers();
                    break;
                case 4:
                    System.out.println("Work In Progress");
                    break;
                case 5:
                    boolean addMediaBool = true;
                    while (addMediaBool) {
                        System.out.println("Select mediatype to add");
                        selectionHandling.printMediaTypeSelection();
                        currentSelection = selectionHandling.positiveInt();
                        switch (currentSelection) {
                            case 1:
                                System.out.println("Booktitle: ");
                                String title = scan.nextLine();
//                            System.out.println("titel blev: " + title);
                                System.out.println("ISBN: ");
                                String isbn = scan.nextLine();
//                            System.out.println("isbn blev: " + isbn);
                                System.out.println("Number of pages: ");
                                int pages = selectionHandling.positiveInt();
//                            System.out.println("pages blev: " + pages);
                                bookRepository.insertNewBook(title, isbn, pages);
                                break;
                            case 2:
                                System.out.println("Work In Progress");
                                break;
                            case 3:
                                System.out.println("Work In Progress");
                                break;
                            case 4:
                                System.out.println("Work In Progress");
                                break;
                            case 5:
                                System.out.println("Work In Progress");
                                break;
                            case 0:
                                addMediaBool = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case 6:
                    memberRepository.updateMember();
                    break;
                case 7:
                    System.out.println("Write the mediaId of the item you wish to delete");
                    int mediaIdToDelete = selectionHandling.positiveInt();
                    bookRepository.deleteBook(mediaIdToDelete);
                    break;
                case 8:
                    System.out.println("You will type in all values that you want to change, \u001B[31mLEAVE FIELD EMPTY TO KEEP CURRENT INFORMATION\u001B[0m");
                    System.out.println();

                    System.out.println("Write the bookID number for which you want to update(required): ");
                    int bookId = selectionHandling.positiveInt();

                    System.out.println("New ISBN: ");
                    String isbn = scan.nextLine();

                    System.out.println("New number of pages: ");
                    System.out.println("(To keep current amount of pages, assign 0)");
                    int pages = selectionHandling.positiveInt();

                    bookRepository.updateBook(bookId, isbn, pages);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println();
            }
        }
    }

}

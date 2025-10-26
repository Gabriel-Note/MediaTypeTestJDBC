import java.awt.print.Book;
import java.util.Scanner;

public class Menu {
    MediaRepository mediaRepository = new MediaRepository();
    BookRepository bookRepository = new BookRepository();


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
                    System.out.println("toString scanner: " + searchWord);
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
                            case 6:
                                loopCheck = false;
                                break;
                            default:
                                System.out.println("Invalid input");
                        }
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Select mediatype to add");
                    selectionHandling.printMediaTypeSelection();
                    currentSelection = selectionHandling.positiveInt();
                    switch (currentSelection) {
                        case 1:
                            System.out.println("Booktitle: ");
                            String title = scan.nextLine();
                            System.out.println("titel blev: " + title);
                            System.out.println("ISBN: ");
                            String isbn = scan.nextLine();
                            System.out.println("isbn blev: " + isbn);
                            System.out.println("Number of pages: ");
                            int pages = selectionHandling.positiveInt();
                            System.out.println("pages blev: " + pages);
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
                        case 6:
                            loopCheck = false;
                            break;
                        default:
                            System.out.println("Invalid input");
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println();
            }
        }
    }

}

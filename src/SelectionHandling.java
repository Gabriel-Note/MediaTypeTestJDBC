import java.util.Scanner;

public class SelectionHandling {


    // Validering användarinput som ett positivt heltal
    public int positiveInt(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                int input = scan.nextInt();
                if (input < 0){
                    System.out.println("\u001B[31m får inte vara negativa värden, försök igen \u001B[0m");
                    scan.nextLine();
                }
                else {
                    return input;
                }
            } catch (Exception e) {
                System.out.println("\u001B[31m Ogiltigt val, försök igen\u001B[0m");
                scan.nextLine();
            }
        }
    }
    public void printMainSelection(){
        System.out.println();
        System.out.println("1. Search all media");
        System.out.println("2. Select type of media(Book, DVD E-book...)");
        System.out.println("3. Show members");
        System.out.println("4. Show active loans");
        System.out.println("5. Add media");
        System.out.println("6. Update members");
        System.out.println("7. Delete book");
        System.out.println("8. Update book");
        System.out.println("0. Exit");
    }

    public void printMediaTypeSelection(){
        System.out.println();
        System.out.println("1. " + MediaType.BOOK.getMediaType());
        System.out.println("2. " + MediaType.E_BOOK.getMediaType());
        System.out.println("3. " + MediaType.AUDIOBOOK.getMediaType());
        System.out.println("4. " + MediaType.DVD.getMediaType());
        System.out.println("5. " + MediaType.BLU_RAY.getMediaType());
        System.out.println("0. Go back");
    }



}

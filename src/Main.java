public class Main {
    public static void main(String[] args) {

        MediaRepository mr = new MediaRepository();
        BookRepository br = new BookRepository();

        br.showAllBooks();

        for (Media m : mr.showAllMedia()){
            System.out.println(m.getMediaID() + m.getTitle() + m.getPublishingYear());
        }
//        mr.insertNewMedia("harry bottle", "Book");
//        int mediaKey = mr.insertNewMediaGetKey("harry snottle", MediaType.AUDIOBOOK);
        int mediaKey = mr.insertNewMediffffffaGetKey("harry snottle", MediaType.AUDIOBOOK);
        System.out.println("Medianyckel: " + mediaKey);



    }
}
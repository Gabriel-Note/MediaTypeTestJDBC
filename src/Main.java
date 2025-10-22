public class Main {
    public static void main(String[] args) {

        MediaRepository mr = new MediaRepository();

        for (Media m : mr.showAllMedia()){
            System.out.println(m.getMediaID() + m.getTitle() + m.getPublishingYear());
        }


    }
}
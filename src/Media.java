public class Media {
    private int mediaID;
    private String title;
    private int publishingYear;
    private double price;
    private String language;
    private String mediaType;

    public Media(int mediaID, String title, int publishingYear, double price, String language, String mediaType) {
        this.mediaID = mediaID;
        this.title = title;
        this.publishingYear = publishingYear;
        this.price = price;
        this.language = language;
        this.mediaType = mediaType;
    }

    public Media(int mediaID, String title, String mediaType, int publishingYear) {
        this.mediaID = mediaID;
        this.title = title;
        this.mediaType = mediaType;
        this.publishingYear = publishingYear;
    }

    public int getMediaID() {
        return mediaID;
    }

    public void setMediaID(int mediaID) {
        this.mediaID = mediaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}

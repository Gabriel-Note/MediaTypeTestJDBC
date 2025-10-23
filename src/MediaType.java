public enum MediaType{
    BOOK("Book"),
    E_BOOK("E-book"),
    AUDIOBOOK("Audiobook"),
    DVD("DVD"),
    BLU_RAY("Blu-ray");

    private final String type;

    MediaType(String type){
        this.type = type;
    }

    public String getMediaType() {
        return type;
    }
}
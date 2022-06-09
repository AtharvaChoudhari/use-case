import lombok.Getter;

public class Privilege {
    private int id;
    //Values {Music, Video, Ebooks}
    @Getter
    private String privilegeName;

    public Privilege(int id, String privilegeName) {
        this.id = id;
        this.privilegeName = privilegeName;
    }
}

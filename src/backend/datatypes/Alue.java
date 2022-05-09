package src.backend.datatypes;

public class Alue {

    private String id;
    private String nimi;
    public Alue(String id, String nimi) {
        this.id = id;
        this.nimi=nimi;
    }

    public String getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return "Alue{" +
                "id='" + id + '\'' +
                ", nimi='" + nimi + '\'' +
                '}';
    }
}

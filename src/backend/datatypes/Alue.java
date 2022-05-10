package src.backend.datatypes;

public class Alue {

    private Integer id;
    private String nimi;
    public Alue(String id, String nimi) {
        this.id = Integer.parseInt(id);
        this.nimi=nimi;
    }

    public Integer getId() {
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

package src.data;

import src.backend.ObjectGenerator;

import java.util.HashMap;

public class Mokki {

    private String mokki_id;
    private String alue_id;
    private String postinro;
    private String mokkinimi;
    private String katuosoite;
    private String hinta;
    private String kuvaus;
    private String henkilomaara;
    private String varustelu;

    public Mokki(String mokki_id, String alue_id, String postinro, String mokkinimi, String katuosoite, String hinta, String kuvaus, String henkilomaara, String varustelu) {
        this.mokki_id = mokki_id;
        this.alue_id = alue_id;
        this.postinro = postinro;
        this.mokkinimi = mokkinimi;
        this.katuosoite = katuosoite;
        this.hinta = hinta;
        this.kuvaus = kuvaus;
        this.henkilomaara = henkilomaara;
        this.varustelu = varustelu;
    }

    @Override
    public String toString() {
        return this.mokkinimi + ", " + this.kuvaus;
    }

    public static void main(String[] args) {

        ObjectGenerator og = new ObjectGenerator();

        HashMap<String, String> params = new HashMap<>();
        params.put("mokki_id", "2");
        System.out.println(og.getMokki(params));
    }
}

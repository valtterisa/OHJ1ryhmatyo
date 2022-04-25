package src.data;

import src.backend.ObjectGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Asiakas {

    private String asiakas_id;
    private String postinro;
    private String postitoimipaikka;
    private String etunimi;
    private String sukunimi;
    private String lahiosoite;
    private String email;
    private String puhelinnro;

    public Asiakas (String asiakas_id, String postinro, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro) {
        this.asiakas_id = asiakas_id;
        this.postinro = postinro;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    @Override
    public String toString() {
        return this.etunimi + " " + this.sukunimi + ", puh: " + this.puhelinnro;
    }

    public static void main(String[] args) {

        HashMap<String, String> params = new HashMap<>();
        params.put("asiakas_id", "57");

        ArrayList<Asiakas> asiakas = ObjectGenerator.getAsiakas(params);

        System.out.println(asiakas);

        HashMap<String, String> lisattavaAsiakas = new HashMap<>();
        lisattavaAsiakas.put("etunimi", "Testataan");
        lisattavaAsiakas.put("sukunimi", "Lisäystä");
        lisattavaAsiakas.put("puhelinnro", "tässä puhelin");
        lisattavaAsiakas.put("postinro", "70150");

        System.out.println(ObjectGenerator.postAsiakas(lisattavaAsiakas));
    }
}

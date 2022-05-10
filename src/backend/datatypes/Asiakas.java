package src.backend.datatypes;

import src.backend.api.BackendAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class Asiakas {

    private Integer asiakas_id;
    private String postinro;
    private String postitoimipaikka;
    private String etunimi;
    private String sukunimi;
    private String lahiosoite;
    private String email;
    private String puhelinnro;

    private ArrayList<Varaus> varaukset;

    public Asiakas (String asiakas_id, String postinro, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro) {
        this.asiakas_id = Integer.parseInt(asiakas_id);
        this.postinro = postinro;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    //Palauttaa postitoimipaikan. Jos ei ole, hakee sen postinumerolla
    public String getPostitoimipaikka() {
        if (this.postitoimipaikka == null) {
            this.postitoimipaikka = BackendAPI.getPosti(this.postinro);
        }
        return this.postitoimipaikka;
    }

    public ArrayList<Varaus> getVaraukset() {
        if (this.varaukset == null) {
            HashMap<String, String> params = new HashMap<String,String>();
            params.put("asiakas_id", this.asiakas_id + "");
            this.varaukset = BackendAPI.getVaraus(params);
        }
        return this.varaukset;
    }

    public Integer getAsiakas_id() {
        return asiakas_id;
    }

    public String getPostinro() {
        return postinro;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public String getEmail() {
        return email;
    }

    public String getPuhelinnro() {
        return puhelinnro;
    }

    @Override
    public String toString() {
        return "Asiakas{" +
                "asiakas_id='" + asiakas_id + '\'' +
                ", postinro='" + postinro + '\'' +
                ", postitoimipaikka='" + postitoimipaikka + '\'' +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", lahiosoite='" + lahiosoite + '\'' +
                ", email='" + email + '\'' +
                ", puhelinnro='" + puhelinnro + '\'' +
                '}';
    }
}

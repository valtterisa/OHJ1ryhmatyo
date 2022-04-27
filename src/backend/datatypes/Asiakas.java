package src.backend.datatypes;

import src.backend.api.BackendAPI;

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

    //Palauttaa postitoimipaikan. Jos ei ole, hakee sen postinumerolla
    public String getPostitoimipaikka() {
        if (this.postitoimipaikka == null) {
            this.postitoimipaikka = BackendAPI.getPosti(this.postinro);
        }
        return this.postitoimipaikka;
    }

    public String getAsiakas_id() {
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

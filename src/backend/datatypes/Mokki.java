package src.backend.datatypes;

import src.backend.api.BackendAPI;

public class Mokki {

    private String mokki_id;
    private String alue_id;
    private String alue;
    private String postinro;
    private String postitoimipaikka;
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

    public String getMokki_id() {
        return mokki_id;
    }

    public String getAlue_id() {
        return alue_id;
    }

    public String getPostinro() {
        return postinro;
    }

    public String getMokkinimi() {
        return mokkinimi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public String getHinta() {
        return hinta;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public String getHenkilomaara() {
        return henkilomaara;
    }

    public String getVarustelu() {
        return varustelu;
    }

    //Palauttaa alueen. Jos ei ole, hakee sen alue_id:llä
    public String getAlue() {
        if (this.alue == null) {
            this.alue = BackendAPI.getAlue(this.alue_id + "");
        }
        return alue;
    }

    //Palauttaa postitoimipaikan. Jos ei ole, hakee sen postinumerolla
    public String getPostitoimipaikka() {
        if (this.postitoimipaikka == null) {
            this.postitoimipaikka = BackendAPI.getPosti(this.postinro);
        }
        return postitoimipaikka;
    }

    @Override
    public String toString() {
        return "Mokki{" +
                "mokki_id='" + mokki_id + '\'' +
                ", alue_id='" + alue_id + '\'' +
                ", alue='" + alue + '\'' +
                ", postinro='" + postinro + '\'' +
                ", postitoimipaikka='" + postitoimipaikka + '\'' +
                ", mokkinimi='" + mokkinimi + '\'' +
                ", katuosoite='" + katuosoite + '\'' +
                ", hinta='" + hinta + '\'' +
                ", kuvaus='" + kuvaus + '\'' +
                ", henkilomaara='" + henkilomaara + '\'' +
                ", varustelu='" + varustelu + '\'' +
                '}';
    }
}

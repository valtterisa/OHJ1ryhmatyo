package src.backend.datatypes;

import src.backend.api.BackendAPI;

public class Mokki {

    private Integer mokki_id;
    private Integer alue_id;
    private String alue;
    private String postinro;
    private String postitoimipaikka;
    private String mokkinimi;
    private String katuosoite;
    private Double hinta;
    private String kuvaus;
    private Integer henkilomaara;
    private String varustelu;

    public Mokki(String mokki_id, String alue_id, String postinro, String mokkinimi, String katuosoite, String hinta, String kuvaus, String henkilomaara, String varustelu) {
        this.mokki_id = Integer.parseInt(mokki_id);
        this.alue_id = Integer.parseInt(alue_id);
        this.postinro = postinro;
        this.mokkinimi = mokkinimi;
        this.katuosoite = katuosoite;
        this.hinta = Double.parseDouble(hinta);
        this.kuvaus = kuvaus;
        this.henkilomaara = Integer.parseInt(henkilomaara);
        this.varustelu = varustelu;
    }

    public Integer getMokki_id() {
        return mokki_id;
    }

    public Integer getAlue_id() {
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

    public Double getHinta() {
        return hinta;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public Integer getHenkilomaara() {
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

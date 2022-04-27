package src.backend.datatypes;

import src.backend.api.BackendAPI;

public class Palvelu {

    private String palvelu_id;
    private String alue_id;
    private String alue;
    private String nimi;
    private String tyyppi;
    private String kuvaus;
    private String hinta;
    private String alv;

    public Palvelu (String palvelu_id, String alue_id, String nimi, String tyyppi, String kuvaus, String hinta, String alv) {
        this.palvelu_id = palvelu_id;
        this.alue_id = alue_id;
        this.nimi = nimi;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
    }

    public String getPalvelu_id() {
        return palvelu_id;
    }

    public String getAlue_id() {
        return alue_id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public String getHinta() {
        return hinta;
    }

    public String getAlv() {
        return alv;
    }

    //Palauttaa alueen. Jos ei ole, hakee sen alue_id:llä
    public String getAlue() {
        if (this.alue == null) {
            this.alue = BackendAPI.getAlue(this.alue_id);
        }
        return alue;
    }

    @Override
    public String toString() {
        return "Palvelu{" +
                "palvelu_id='" + palvelu_id + '\'' +
                ", alue_id='" + alue_id + '\'' +
                ", alue='" + getAlue() + '\'' +
                ", nimi='" + nimi + '\'' +
                ", tyyppi='" + tyyppi + '\'' +
                ", kuvaus='" + kuvaus + '\'' +
                ", hinta='" + hinta + '\'' +
                ", alv='" + alv + '\'' +
                '}';
    }
}

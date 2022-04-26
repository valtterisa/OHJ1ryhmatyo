package src.backend.datatypes;

import src.backend.api.BackendAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class Varaus {
    private String varaus_id;
    private String asiakas_id;
    private String mokki_mokki_id;
    private String varattu_pvm;
    private String vahvistus_pvm;
    private String varattu_alkupvm;
    private String varattu_loppupvm;

    private ArrayList<VarauksenPalvelu> palvelut;

    private Asiakas asiakas;
    private Mokki mokki;

    public Varaus (String varaus_id, String asiakas_id, String mokki_mokki_id, String varattu_pvm, String vahvistus_pvm, String varattu_alkupvm, String varattu_loppupvm) {
        this.varaus_id = varaus_id;
        this.asiakas_id = asiakas_id;
        this.mokki_mokki_id = mokki_mokki_id;
        this.varattu_pvm = varattu_pvm;
        this.vahvistus_pvm = vahvistus_pvm;
        this.varattu_alkupvm = varattu_alkupvm;
        this.varattu_loppupvm = varattu_loppupvm;
    }

    public String getVaraus_id() {
        return varaus_id;
    }

    public String getAsiakas_id() {
        return asiakas_id;
    }

    public String getMokki_mokki_id() {
        return mokki_mokki_id;
    }

    public String getVarattu_pvm() {
        return varattu_pvm;
    }

    public String getVahvistus_pvm() {
        return vahvistus_pvm;
    }

    public String getVarattu_alkupvm() {
        return varattu_alkupvm;
    }

    public String getVarattu_loppupvm() {
        return varattu_loppupvm;
    }

    //Palauttaa Asiakas-olion. Jos ei ole, hakee sen asiakas_id:llä
    public Asiakas getAsiakas() {
        if (asiakas == null) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("asiakas_id", this.asiakas_id);
            setAsiakas(BackendAPI.getAsiakas(params).get(0));
        }
        return asiakas;
    }

    //Palauttaa Mökki-olion. Jos ei ole, hakee sen mokki_id:llä
    public Mokki getMokki() {
        if (mokki == null) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("mokki_id", this.mokki_mokki_id);
            setMokki(BackendAPI.getMokki(params).get(0));
        }
        return mokki;
    }

    public ArrayList<VarauksenPalvelu> getPalvelut() {
        if (this.palvelut == null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("varaus_id", this.varaus_id);
            this.palvelut = BackendAPI.getVarauksenPalvelu(params);
        }
        return palvelut;
    }

    public void setMokki(Mokki mokki) {
        this.mokki = mokki;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    @Override
    public String toString() {
        return "Varaus{" +
                "varaus_id='" + varaus_id + '\'' +
                ", varattu_pvm='" + varattu_pvm + '\'' +
                ", vahvistus_pvm='" + vahvistus_pvm + '\'' +
                ", varattu_alkupvm='" + varattu_alkupvm + '\'' +
                ", varattu_loppupvm='" + varattu_loppupvm + '\'' +
                '}' +
                "\nVARAAJA: " + this.asiakas +
                "\nMÖKKI: " + this.mokki;
    }
}

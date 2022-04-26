package src.backend.datatypes;

import src.backend.api.BackendAPI;

import java.util.HashMap;

public class Lasku {

    private String lasku_id;
    private String varaus_id;
    private Varaus varaus;
    private String summa;
    private String alv;

    public Lasku (String lasku_id, String varaus_id, String summa, String alv) {
        this.lasku_id = lasku_id;
        this.varaus_id = varaus_id;
        this.summa = summa;
        this.alv = alv;
    }

    public String getLasku_id() {
        return lasku_id;
    }

    public String getVaraus_id() {
        return varaus_id;
    }

    public String getSumma() {
        return summa;
    }

    public String getAlv() {
        return alv;
    }

    public Varaus getVaraus() {
        if (varaus == null) {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("varaus_id", this.varaus_id);
            this.varaus = BackendAPI.getVaraus(params).get(0);
        }
        return varaus;
    }
}

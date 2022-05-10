package src.backend.datatypes;

import src.backend.api.BackendAPI;

import java.util.HashMap;

public class VarauksenPalvelu {

    private String varaus_id;
    private String palvelu_id;
    private Palvelu palvelu;
    private String lkm;

    public VarauksenPalvelu (String varaus_id, String palvelu_id, String lkm) {
        this.varaus_id = varaus_id;
        this.palvelu_id = palvelu_id;
        this.lkm = lkm;
    }

    public String getVaraus_id() {
        return varaus_id;
    }

    public String getPalvelu_id() {
        return palvelu_id;
    }

    public String getLkm() {
        return lkm;
    }

    //Palauttaa Palvelu-olion. Jos ei ole, hakee sen palvelu_id:llä
    public Palvelu getPalvelu() {
        if (this.palvelu == null) {
            HashMap<String, String> params = new HashMap<>();
            params.put("palvelu_id", this.palvelu_id);
            this.palvelu = BackendAPI.getPalvelu(params).get(0);
        }
        return palvelu;
    }

    public String toNeatString() {
        return getPalvelu().getNimi() + "(" + lkm + " kpl)";
    }

    @Override
    public String toString() {
        return toNeatString();
        /*
        return "VarauksenPalvelu{" +
                "varaus_id='" + varaus_id + '\'' +
                ", palvelu_id='" + palvelu_id + '\'' +
                ", palvelu=" + palvelu +
                ", lkm='" + lkm + '\'' +
                '}';*/
    }
}

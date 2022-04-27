package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.VarauksenPalvelu;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class VarausFunctions {

    public static ArrayList<Varaus> getVaraus(HashMap<String, String> params) {

        ArrayList<Varaus> varausList = new ArrayList<Varaus>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "varaus", params);

        return generateVaraus(response);
    }

    public static Varaus postVaraus(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "varaus", params);

        System.out.println("Generating objects from received data...");

        HashMap<String, String> varausParams = new HashMap<>();
        varausParams.put("varaus_id", response.get(0).get(2));
        return getVaraus(varausParams).get(0);
    }

    public static Varaus putVaraus(HashMap<String, String> params, String id) {

        String[] varausId = new String[]{"varaus_id", id};
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "varaus", params, varausId);

        System.out.println("Generating objects from received data...");

        HashMap<String, String> varausParams = new HashMap<>();
        varausParams.put("varaus_id", response.get(0).get(2));
        return getVaraus(varausParams).get(0);
    }

    public static String deleteVaraus(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "varaus", params);

        return response.get(0).get(1);
    }

    public static ArrayList<Varaus> getVarausTiedoilla(HashMap<String, String> params) {
        ArrayList<Varaus> varausList = getVaraus(params);
        for (Varaus x : varausList) {
            HashMap<String, String> asiakasParam = new HashMap<>();
            asiakasParam.put("asiakas_id", x.getAsiakas_id());

            HashMap<String, String> mokkiParam = new HashMap<>();
            mokkiParam.put("mokki_id", x.getMokki_mokki_id());

            x.setAsiakas(AsiakasFunctions.getAsiakas(asiakasParam).get(0));
            x.setMokki(MokkiFunctions.getMokki(mokkiParam).get(0));
        }

        return varausList;
    }

    public static ArrayList<VarauksenPalvelu> getVarauksenPalvelu(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "varauksen_palvelut", params);

        return generateVarauksenPalvelu(response);
    }

    public static VarauksenPalvelu postVarauksenPalvelu(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "varauksen_palvelut", params);

        return getVarauksenPalvelu(params).get(0);
    }

    public static VarauksenPalvelu putVarauksenPalvelu(HashMap<String, String> params, String varaus_id, String palvelu_id) {
        HashMap<String, String> varauksenPalveluId = new HashMap<>();
        varauksenPalveluId.put("varaus_id", varaus_id);
        varauksenPalveluId.put("palvelu_id", palvelu_id);

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "varauksen_palvelut", params, varauksenPalveluId);

        return generateVarauksenPalvelu(response).get(0);
    }

    public static String deleteVarauksenPalvelu(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "varauksen_palvelut", params);

        return response.get(0).get(1);
    }

    public static ArrayList<Varaus> generateVaraus(ArrayList<ArrayList<String>> data) {
        System.out.println("Generating objects from received data...");
        ArrayList<Varaus> varausList = new ArrayList<>();
        for (ArrayList<String> x : data) {
            Varaus uusiVaraus = new Varaus(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            varausList.add(uusiVaraus);
        }
        return varausList;
    }

    public static ArrayList<VarauksenPalvelu> generateVarauksenPalvelu(ArrayList<ArrayList<String>> data) {
        System.out.println("Generating objects from received data...");
        ArrayList<VarauksenPalvelu> varauspalveluList = new ArrayList<>();
        for (ArrayList<String> x : data) {
            VarauksenPalvelu uusiVarauksenPalvelu = new VarauksenPalvelu (x.get(0), x.get(1), x.get(2));
            varauspalveluList.add(uusiVarauksenPalvelu);
        }
        return varauspalveluList;
    }

}

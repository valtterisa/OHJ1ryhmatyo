package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Palvelu;

import java.util.ArrayList;
import java.util.HashMap;

public class PalveluFunctions {
    public static ArrayList<Palvelu> getPalvelu(HashMap<String, String> params) {

        ArrayList<Palvelu> palveluList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "palvelu", params);

        return generatePalvelu(response);
    }

    public static Palvelu postPalvelu(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "palvelu", params);

        return getPalvelu(params).get(0);                                                                               // Haetaan tässä suoraan syötetyillä parametreilla, koska palvelu_id
    }                                                                                                                   // ei ole kannassa autoincrement

    public static Palvelu putPalvelu(HashMap<String, String> params, String id) {

        String[] palveluId = new String[]{"palvelu_id", id};
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "palvelu", params, palveluId);

        return generatePalvelu(response).get(0);
    }

    public static String deletePalvelu(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "palvelu", params);

        return response.get(0).get(1);
    }

    public static ArrayList<Palvelu> generatePalvelu(ArrayList<ArrayList<String>> data) {                               // Muuntaa kaksiuloitteiden listan listaksi palveluja
        System.out.println("Generating objects from received data...");
        ArrayList<Palvelu> palveluList = new ArrayList<>();
        for (ArrayList<String> x : data) {
            Palvelu uusiPalvelu = new Palvelu(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            palveluList.add(uusiPalvelu);
        }
        return palveluList;
    }
}

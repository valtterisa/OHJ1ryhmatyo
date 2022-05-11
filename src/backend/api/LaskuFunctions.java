package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Lasku;

import java.util.ArrayList;
import java.util.HashMap;

public class LaskuFunctions {
    public static ArrayList<Lasku> getLasku(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "lasku", params);

        return generateLasku(response);
    }

    public static Lasku postLasku(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "lasku", params);

        //HashMap<String, String> laskuParams = new HashMap<>();
        //laskuParams.put("lasku_id", response.get(0).get(2));
        return getLasku(params).get(0);
    }

    public static Lasku putLasku(HashMap<String, String> params, String id) {
        String[] LaskuID = new String[]{"lasku_id", id};

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "lasku", params, LaskuID);

        return generateLasku(response).get(0);
    }

    public static String deleteLasku(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "lasku", params);

        return response.get(0).get(1);
    }

    // GENEROI ASIAKKAAN ANNETUSTA DATASTA
    public static ArrayList<Lasku> generateLasku(ArrayList<ArrayList<String>> data) {
        System.out.println("Generating objects from received data...");
        ArrayList<Lasku> laskuList = new ArrayList<Lasku>();
        for (ArrayList<String> x : data) {
            Lasku uusiLasku = new Lasku(x.get(0), x.get(1), x.get(2), x.get(3));
            laskuList.add(uusiLasku);
        }
        return laskuList;
    }
}


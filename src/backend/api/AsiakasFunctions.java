package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Asiakas;

import java.util.ArrayList;
import java.util.HashMap;

public class AsiakasFunctions {

    public static ArrayList<Asiakas> getAsiakas(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "asiakas", params);

        return generateAsiakas(response);
    }

    public static Asiakas postAsiakas(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "asiakas", params);

        HashMap<String, String> asiakasParams = new HashMap<>();
        asiakasParams.put("asiakas_id", response.get(0).get(2));
        return getAsiakas(asiakasParams).get(0);
    }

    public static Asiakas putAsiakas(HashMap<String, String> params, String id) {
        String[] AsiakasID = new String[]{"Asiakas_id", id};

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "asiakas", params, AsiakasID);

        return generateAsiakas(response).get(0);
    }

    public static String deleteAsiakas(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "asiakas", params);

        return response.get(0).get(1);
    }

    // GENEROI ASIAKKAAN ANNETUSTA DATASTA
    public static ArrayList<Asiakas> generateAsiakas(ArrayList<ArrayList<String>> data) {
        System.out.println("Generating objects from received data...");
        ArrayList<Asiakas> asiakasList = new ArrayList<Asiakas>();
        for (ArrayList<String> x : data) {
            Asiakas uusiAsiakas = new Asiakas(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            asiakasList.add(uusiAsiakas);
        }
        return asiakasList;
    }
}



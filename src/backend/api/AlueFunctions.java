package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Alue;

import java.util.ArrayList;
import java.util.HashMap;

public class AlueFunctions {

    public static String getAlue(String alue_id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("alue_id", alue_id);
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "alue", params);
        if (response.size() > 0) {
            return response.get(0).get(1);
        }
        else {
            return null;
        }
    }

    public static Alue postAlue(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "alue", params);

        HashMap<String, String> alueParams = new HashMap<String,String>();
        alueParams.put("alue_id",  response.get(0).get(2));
        return getAlue(alueParams).get(0);
    }

    public static Alue putAlue(HashMap<String, String> params, String id) {
        String[] idParam = new String[]{"alue_id", id};
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "alue", params, idParam);

        return generateAlue(response).get(0);
    }

    public static String deleteAlue(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "alue", params);

        return response.get(0).get(1);
    }

    public static ArrayList<Alue> getAlue(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "alue", params);

        return generateAlue(response);
    }

    public static ArrayList<Alue> generateAlue(ArrayList<ArrayList<String>> data) {
        System.out.println("Generating objects from received data...");
        ArrayList<Alue> alueList = new ArrayList<Alue>();
        for (ArrayList<String> x : data) {
            Alue uusiAlue = new Alue(x.get(0), x.get(1));
            alueList.add(uusiAlue);
        }
        return alueList;
    }
}

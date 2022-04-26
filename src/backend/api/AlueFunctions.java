package src.backend.api;

import src.backend.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashMap;

public class AlueFunctions {

    public static String getAlue(String alue_id) {
        HashMap<String, String> params = new HashMap<>();
        params.put("alue_id", alue_id);
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "alue", params);

        return response.get(0).get(1);
    }

    public static String postAlue(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "alue", params);

        return response.get(0).get(2);
    }

    public static String putAlue(HashMap<String, String> params, String id) {
        String[] idParam = new String[]{"alue_id", id};
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "alue", params, idParam);

        return response.get(0).get(1);
    }

    public static String deleteAlue(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "alue", params);

        return response.get(0).get(1);
    }
}

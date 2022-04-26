package src.backend.api;

import src.backend.DatabaseConnection;

import java.util.ArrayList;
import java.util.HashMap;

public class PostiFunctions {

    public static String getPosti(String postinro) {
        HashMap<String, String> params = new HashMap<>();
        params.put("postinro", postinro);
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "posti", params);

        return response.get(0).get(1);
    }
}

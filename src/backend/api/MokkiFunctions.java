package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Mokki;

import java.util.ArrayList;
import java.util.HashMap;

public class MokkiFunctions {

    public static ArrayList<Mokki> getMokki(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "mokki", params);

        return generateMokki(response);
    }

    public static Mokki postMokki(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "mokki", params);

        HashMap<String, String> postedId = new HashMap<>();
        postedId.put("mokki_id", response.get(0).get(2));
        return getMokki(postedId).get(0);
    }

    public static Mokki putMokki(HashMap<String, String> params, String id) {

        String[] editId = new String[]{"mokki_id", id};

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("put", "mokki", params, editId);

        return generateMokki(response).get(0);
    }

    public static String deleteMokki(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("delete", "mokki", params);

        return response.get(0).get(1);
    }

    // GENEROI MÖKIN ANNETUSTA DATASTA
    public static ArrayList<Mokki> generateMokki(ArrayList<ArrayList<String>> data) {
        ArrayList<Mokki> mokkiList = new ArrayList<>();
        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : data) {
            Mokki uusiMokki = new Mokki(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7), x.get(8));
            mokkiList.add(uusiMokki);
        }
        return mokkiList;
    }
}

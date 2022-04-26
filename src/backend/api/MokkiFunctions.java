package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Mokki;

import java.util.ArrayList;
import java.util.HashMap;

public class MokkiFunctions {

    //Palauttaa listan Mökkejä, jotka vastaavat parametreja
    public static ArrayList<Mokki> getMokki(HashMap<String, String> params) {

        ArrayList<Mokki> mokkiList = new ArrayList<Mokki>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "mokki", params);

        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : response) {
            Mokki uusiMokki = new Mokki(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6), x.get(7), x.get(8));
            mokkiList.add(uusiMokki);
        }
        return mokkiList;
    }

    //Palauttaa listan Mökkejä, jotka vastaavat parametreja
    public static Mokki postMokki(HashMap<String, String> params) {

        ArrayList<Mokki> mokkiList = new ArrayList<Mokki>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "mokki", params);

        System.out.println("Generating objects from received data...");
        HashMap<String, String> postedId = new HashMap<>();
        postedId.put("mokki_id", response.get(0).get(2));
        return getMokki(postedId).get(0);
    }

    //Palauttaa listan Mökkejä, jotka vastaavat parametreja
    public static Mokki putMokki(HashMap<String, String> params, String id) {
        String[] editId = new String[]{"mokki_id", id};

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "mokki", params, editId);

        System.out.println("Generating objects from received data...");

        HashMap<String, String> updatedId = new HashMap<>();
        updatedId.put("mokki_id", id);

        return getMokki(updatedId).get(0);
    }

    //Palauttaa listan Mökkejä, jotka vastaavat parametreja
    public static String deleteMokki(HashMap<String, String> params) {

        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "mokki", params);

        return response.get(0).get(1);
    }
}

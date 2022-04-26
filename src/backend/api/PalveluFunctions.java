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

        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : response) {
            Palvelu uusiPalvelu = new Palvelu(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            palveluList.add(uusiPalvelu);
        }
        return palveluList;
    }
}

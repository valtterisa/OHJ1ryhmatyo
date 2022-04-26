package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Asiakas;

import java.util.ArrayList;
import java.util.HashMap;

public class AsiakasFunctions {

    //Palauttaa listan Asiakkaita, jotka vastaavat parametreja
    public static ArrayList<Asiakas> getAsiakas(HashMap<String, String> params) {

        ArrayList<Asiakas> asiakasList = new ArrayList<Asiakas>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "asiakas", params);

        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : response) {
            Asiakas uusiAsiakas = new Asiakas(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            asiakasList.add(uusiAsiakas);
        }
        return asiakasList;
    }

    //Lähettää parametreina annetun Asiakkaan tietokantaan ja palauttaa
    //sen Asiakas-oliona.
    public static Asiakas postAsiakas(HashMap<String, String> params) {
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("post", "asiakas", params);

        HashMap<String, String> asiakasParams = new HashMap<>();
        asiakasParams.put("asiakas_id", response.get(0).get(2));
        return getAsiakas(asiakasParams).get(0);
    }
}



package src.backend;

import src.data.Asiakas;
import src.data.Mokki;
import src.data.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectGenerator {

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

    //Palauttaa listan Varauksia, jotka vastaavat parametreja
    public static ArrayList<Varaus> getVaraus(HashMap<String, String> params) {

        ArrayList<Varaus> varausList = new ArrayList<Varaus>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "varaus", params);

        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : response) {
            Varaus uusiVaraus = new Varaus(x.get(0), x.get(1), x.get(2), x.get(3), x.get(4), x.get(5), x.get(6));
            varausList.add(uusiVaraus);
        }
        return varausList;
    }

    //Palauttaa listan Varauksia, jotka vastaavat parametreja ja
    //joilla on valmiina tiedot varaajasta sekä varatusta mökistä.
    public static ArrayList<Varaus> getVarausTiedoilla(HashMap<String, String> params) {

        ArrayList<Varaus> varausList = getVaraus(params);

        for (Varaus x : varausList) {

            HashMap<String, String> asiakasParam = new HashMap<>();
            asiakasParam.put("asiakas_id", x.getAsiakas_id());

            HashMap<String, String> mokkiParam = new HashMap<>();
            mokkiParam.put("mokki_id", x.getMokki_mokki_id());

            x.setAsiakas(getAsiakas(asiakasParam).get(0));
            x.setMokki(getMokki(mokkiParam).get(0));
        }

        return varausList;
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



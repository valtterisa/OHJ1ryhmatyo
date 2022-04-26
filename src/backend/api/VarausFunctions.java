package src.backend.api;

import src.backend.DatabaseConnection;
import src.backend.datatypes.Palvelu;
import src.backend.datatypes.VarauksenPalvelu;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class VarausFunctions {

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

            x.setAsiakas(AsiakasFunctions.getAsiakas(asiakasParam).get(0));
            x.setMokki(MokkiFunctions.getMokki(mokkiParam).get(0));
        }

        return varausList;
    }

    public static ArrayList<VarauksenPalvelu> getVarauksenPalvelu(HashMap<String, String> params) {
        ArrayList<VarauksenPalvelu> palveluList = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        ArrayList<ArrayList<String>> response = db.doSQL("get", "varauksen_palvelut", params);

        System.out.println("Generating objects from received data...");
        for (ArrayList<String> x : response) {
            VarauksenPalvelu uusiVarauksenPalvelu = new VarauksenPalvelu(x.get(0), x.get(1), x.get(2));
            palveluList.add(uusiVarauksenPalvelu);
        }
        return palveluList;
    }

    }

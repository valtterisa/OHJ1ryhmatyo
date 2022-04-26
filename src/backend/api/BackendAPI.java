package src.backend.api;

import src.backend.datatypes.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BackendAPI {

    // ASIAKAS
    public static ArrayList<Asiakas> getAsiakas(HashMap<String, String> params) {                                       // Hakee parametreja vastaavat asiakkaat tietokannasta
        return AsiakasFunctions.getAsiakas(params);                                                                     // Palauttaa listan asiakkaita
    }
    public static Asiakas postAsiakas(HashMap<String, String> params) {                                                 // Lähettää asiakkaan tietokantaan annetuilla tiedoilla
        return AsiakasFunctions.postAsiakas(params);                                                                    // Palauttaa lisätyn asiakkaan
    }
    public static Asiakas updateAsiakas(HashMap<String, String> params, String id) {                                    // Päivittää annetut asiakkaan tiedot tietokantaan
        /* TODO */                                                                                                      // Palauttaa muokatun asiakkaan
        return null;
    }
    public static String deleteAsiakas(HashMap<String, String> params) {                                                // Lähettää asiakkaan tietokantaan annetuilla tiedoilla
        /* TODO */                                                                                                      // Palauttaa poistettujen asiakkaiden määrän
        return null;
    }


    // MÖKKI
    public static ArrayList<Mokki> getMokki(HashMap<String, String> params) {                                           // Hakee parametreja vastaavat mökit tietokannasta
        return MokkiFunctions.getMokki(params);                                                                         // Palauttaa listan mökkejä
    }
    public static Mokki postMokki(HashMap<String, String> params) {                                                     // Lähettää mökin tietokantaan annetuilla tiedoilla
        return MokkiFunctions.postMokki(params);                                                                        // Palauttaa lisätyn mökin
    }
    public static Mokki updateMokki(HashMap<String, String> params, String id) {                                        // Päivittää annetut mökin tiedot tietokantaan
        return MokkiFunctions.putMokki(params, id);                                                                     // Palauttaa muokatun mökin
    }
    public static String deleteMokki(HashMap<String, String> params) {                                                  // Poistaa mökit tetokannasta
        return MokkiFunctions.deleteMokki(params);                                                                      // Palauttaa poistettujen mökkien määrän
    }

    // POSTI
    public static String getPosti(String postinro) {                                                                    // Hakee postitoimipaikan postinumerolla
        return PostiFunctions.getPosti(postinro);                                                                       // Palauttaa postitoimipaikan merkkijonona
    }

    // ALUE
    public static String getAlue(String alue_id) {                                                                      // Hakee alueen nimen id:n avulla
        return AlueFunctions.getAlue(alue_id);                                                                          // Palauttaa alueen nimen merkkkijonona
    }
    public static String postAlue(HashMap<String, String> params) {                                                     // Lähettää alueen tietokantaan annetuilla tiedoilla
    return AlueFunctions.postAlue(params);                                                                              // Palauttaa lisätyn alueen id:n
    }
    public static String updateAlue(HashMap<String, String> params, String id) {                                        // Päivittää annetut alueen tiedot tietokantaan
        return AlueFunctions.putAlue(params, id);                                                                       // Palauttaa muokatun alueen nimen
    }
    public static String deleteAlue(HashMap<String, String> params) {                                                   // Poistaa alueet tetokannasta
        return AlueFunctions.deleteAlue(params);                                                                        // Palauttaa poistettujen alueiden määrän
    }

    // VARAUS
    public static ArrayList<Varaus> getVaraus(HashMap<String, String> params) {                                         // Hakee parametreja vastaavat varaukset tietokannasta
        return VarausFunctions.getVaraus(params);                                                                       // Palauttaa listan varauksia
    }
    public static ArrayList<Varaus> postVaraus(HashMap<String, String> params) {                                        // Lähettää varauksen tietokantaan annetuilla tiedoilla
        /*TODO*/
        return null;                                                                                                    // Palauttaa lisätyn varauksen
    }
    public static ArrayList<Varaus> updateVaraus(HashMap<String, String> params) {                                      // Päivittää annetut varauksen tiedot tietokantaan
        /*TODO*/
        return null;                                                                                                    // Palauttaa päivitetyn varauksen
    }
    public static ArrayList<Varaus> deletetVaraus(HashMap<String, String> params) {                                     // Poistaa mökit tetokannasta
        /*TODO*/
        return null;                                                                                                    // Palauttaa poistettujen varausten määrän.
    }

    // VARAUKSEN PALVELU
    public static ArrayList<VarauksenPalvelu> getVarauksenPalvelu(HashMap<String, String> params) {                     // Palauttaa palvelut annetuilla parametreilla
        return VarausFunctions.getVarauksenPalvelu(params);
    }

    // PALVELU
    public static ArrayList<Palvelu> getPalvelu(HashMap<String, String> params) {                                       // Palauttaa palvelut annetuilla parametreilla
        return PalveluFunctions.getPalvelu(params);
    }

    public static void main(String[] args) {
        /*
        HashMap<String, String> params = new HashMap<>();
        params.put("varaus_id", "1");
        Varaus varaus = getVaraus(params).get(0);
        System.out.println(varaus);
        System.out.println(varaus.getPalvelut());
        for (VarauksenPalvelu x : varaus.getPalvelut()) {
            System.out.println(x.getPalvelu());
        }

        System.out.println(getAlue("13"));

        HashMap<String, String> params = new HashMap<>();
        params.put("nimi", "Niitty");

        System.out.println(updateAlue(params, "12"));

        System.out.println(postAlue(params));

        //System.out.println(deleteAlue(params));
        */
    }
}

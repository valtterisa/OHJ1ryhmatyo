package src.backend.api;

import src.backend.datatypes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BackendAPI {

    // NÄKYMÄT?
    public static ArrayList<Mokki> getMokitAlueillaTAI_JOTAIN_VASTAAVAA() {
        /*
        * TODO
        *  @Valtterisa
        *  BackendAPI -luokkaan kans vaikka funktion/funktioita,
        *  joka kutsuu niitä tietokantanäkymiä.
        * */
        return null;
    }

    // ASIAKAS
    public static ArrayList<Asiakas> getAsiakas(HashMap<String, String> params) {                                       // Hakee parametreja vastaavat asiakkaat tietokannasta
        return AsiakasFunctions.getAsiakas(params);                                                                     // Palauttaa listan asiakkaita
    }
    public static Asiakas postAsiakas(HashMap<String, String> params) {                                                 // Lähettää asiakkaan tietokantaan annetuilla tiedoilla
        return AsiakasFunctions.postAsiakas(params);                                                                    // Palauttaa lisätyn asiakkaan
    }
    public static Asiakas updateAsiakas(HashMap<String, String> params, String id) {                                    // Päivittää annetut asiakkaan tiedot tietokantaan
        return AsiakasFunctions.putAsiakas(params, id);                                                                 // Palauttaa muokatun asiakkaan
    }
    public static String deleteAsiakas(HashMap<String, String> params) {                                                // Lähettää asiakkaan tietokantaan annetuilla tiedoilla
        return AsiakasFunctions.deleteAsiakas(params);                                                                  // Palauttaa poistettujen asiakkaiden määrän
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
    public static Varaus postVaraus(HashMap<String, String> params) {                                                   // Lähettää varauksen tietokantaan annetuilla tiedoilla
        return VarausFunctions.postVaraus(params);                                                                      // Palauttaa lisätyn varauksen
    }
    public static Varaus updateVaraus(HashMap<String, String> params, String id) {                                      // Päivittää annetut varauksen tiedot tietokantaan
        return VarausFunctions.putVaraus(params, id);                                                                   // Palauttaa päivitetyn varauksen
    }
    public static String deleteVaraus(HashMap<String, String> params) {                                                 // Poistaa varauksen tetokannasta
        return VarausFunctions.deleteVaraus(params);                                                                    // Palauttaa poistettujen varausten määrän.
    }

    // VARAUKSEN PALVELU
    public static ArrayList<VarauksenPalvelu> getVarauksenPalvelu(HashMap<String, String> params) {                     // Hakee parametreja vastaavat varauksen palvelut tietokannasta
        return VarausFunctions.getVarauksenPalvelu(params);                                                             // Palauttaa listan varauksen palveluja
    }
    public static VarauksenPalvelu postVarauksenPalvelu(HashMap<String, String> params) {                               // Lähettää varauksen palvelun tietokantaan annetuilla tiedoilla
        return VarausFunctions.postVarauksenPalvelu(params);                                                            // Palauttaa lisätyn varauksen palvelun
    }
    public static VarauksenPalvelu updateVarauksenPalvelu(HashMap<String, String> params, String varaus_id,
                                                                                        String palvelu_id) {            // Päivittää annetut palvelun tiedot tietokantaan
        return VarausFunctions.putVarauksenPalvelu(params, varaus_id, palvelu_id);                                      // Palauttaa päivitetyn palvelun
    }
    public static String deleteVarauksenPalvelu(HashMap<String, String> params) {                                       // Poistaa varauksen palvelun tetokannasta
        return VarausFunctions.deleteVarauksenPalvelu(params);                                                          // Palauttaa poistettujen palveluiden määrän.
    }

    // PALVELU
    public static ArrayList<Palvelu> getPalvelu(HashMap<String, String> params) {                                       // Hakee parametreja vastaavat palvelut tietokannasta
        return PalveluFunctions.getPalvelu(params);                                                                     // Palauttaa listan palveluja
    }
    public static Palvelu postPalvelu(HashMap<String, String> params) {                                                 // Lähettää palvelun tietokantaan annetuilla tiedoilla
        return PalveluFunctions.postPalvelu(params);                                                                    // Palauttaa lisätyn palvelun
    }
    public static Palvelu updatePalvelu(HashMap<String, String> params, String id) {                                    // Päivittää annetut palvelun tiedot tietokantaan
        return PalveluFunctions.putPalvelu(params, id);                                                                 // Palauttaa päivitetyn palvelun
    }
    public static String deletePalvelu(HashMap<String, String> params) {                                                // Poistaa palvelun tetokannasta
        return PalveluFunctions.deletePalvelu(params);                                                                  // Palauttaa poistettujen palveluiden määrän.
    }

    // LASKU
    /* TODO
    *   -Tähän frontendille näkyvät funktiot laskujen hallinnointiin.
    *   -Laskun paperisen / s.posti -version generoinnin voisi toteuttaa Lasku-luokan sisällä
    *    jonkinlaisella generateLasku() -metodilla?
    */

    public static void main(String[] args) {
    /*
        HashMap<String, String> palveluParams = new HashMap<>();
        palveluParams.put("palvelu_id", "1");
        System.out.println(getPalvelu(palveluParams));

        HashMap<String, String> lisattavaPalvelu = new HashMap<>();
        lisattavaPalvelu.put("palvelu_id", "17");
        lisattavaPalvelu.put("alue_id", "3");
        lisattavaPalvelu.put("nimi", "joku nimi");
        lisattavaPalvelu.put("tyyppi", "2");
        lisattavaPalvelu.put("kuvaus", "joku kuvaus");
        lisattavaPalvelu.put("hinta", "11");
        lisattavaPalvelu.put("alv", "22");

        HashMap<String, String> muokattavaPalvelu = new HashMap<>();
        muokattavaPalvelu.put("alue_id", "3");
        muokattavaPalvelu.put("nimi", "joku päivitetty nimi");
        muokattavaPalvelu.put("tyyppi", "2");
        muokattavaPalvelu.put("kuvaus", "joku päivitetty kuvaus");
        muokattavaPalvelu.put("hinta", "22");
        muokattavaPalvelu.put("alv", "33");

        HashMap<String, String> poistettavaPalvelu = new HashMap<>();
        poistettavaPalvelu.put("palvelu_id", "17");

        System.out.println(postPalvelu(lisattavaPalvelu));

        System.out.println(updatePalvelu(muokattavaPalvelu, "17"));

        System.out.println(deletePalvelu(poistettavaPalvelu));
    */
    /*
        HashMap<String, String> params = new HashMap<>();
        params.put("varaus_id", "1");
        Varaus varaus = getVaraus(params).get(0);
        System.out.println(varaus);
        System.out.println(varaus.getPalvelut());
        for (VarauksenPalvelu x : varaus.getPalvelut()) {
            System.out.println(x.getPalvelu());
        }

        HashMap<String, String> lisattavaVaraus = new HashMap<>();
        lisattavaVaraus.put("asiakas_id", "17");
        lisattavaVaraus.put("mokki_mokki_id", "3");
        lisattavaVaraus.put("varattu_pvm", LocalDateTime.now().toString());                                             // Puskee kantaan nykyisen Datetimen
        lisattavaVaraus.put("varattu_alkupvm", LocalDateTime.now().toString());
        lisattavaVaraus.put("varattu_loppupvm", LocalDateTime.now().toString());
        Varaus lisattuVaraus = postVaraus(lisattavaVaraus);
        System.out.println(lisattuVaraus);


        System.out.println(LocalDateTime.now());
    */

    /*
        System.out.println(getAlue("13"));

        HashMap<String, String> params = new HashMap<>();
        params.put("nimi", "Niitty");

        System.out.println(updateAlue(params, "12"));

        System.out.println(postAlue(params));

        //System.out.println(deleteAlue(params));
    */
    /*
        HashMap<String, String> uusipalvelu = new HashMap<>();
        uusipalvelu.put("varaus_id", "2");
        uusipalvelu.put("palvelu_id", "3");
        //uusipalvelu.put("lkm", "1");

        HashMap<String, String> uusipalvelutiedot = new HashMap<>();
        uusipalvelutiedot.put("lkm", "3");

        System.out.println(deleteVarauksenPalvelu(uusipalvelu));

    */
    /*
        HashMap<String, String> uusiMokki = new HashMap<>();
        uusiMokki.put("alue_id", "2");
        uusiMokki.put("postinro", "18890");
        uusiMokki.put("mokkinimi", "joku nimi");
        uusiMokki.put("katuosoite", "joku osoite");
        uusiMokki.put("kuvaus", "joku kuvaus");
        uusiMokki.put("hinta", "3000");

        HashMap<String, String> uusiHinta = new HashMap<>();
        uusiHinta.put("hinta", "3000");

        //System.out.println(getMokki(new HashMap<>()));
        //System.out.println(postMokki(uusiMokki));
        //System.out.println(updateMokki(uusiHinta, "6"));
        System.out.println(deleteMokki(uusiMokki));

    */
    }
}

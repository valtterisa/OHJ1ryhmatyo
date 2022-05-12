package src.frontend.ObjectUI.LaskuHallinta.LaskuGenerator;

import src.backend.api.BackendAPI;
import src.backend.datatypes.Lasku;
import src.backend.datatypes.VarauksenPalvelu;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

public class LaskuGenerator {

    public static void sendlasku(Lasku lasku) {
        try {
            Desktop.getDesktop().mail(new URI("mailto:" + lasku.getVaraus().getAsiakas().getEmail() + "?subject=VillageNewbies%20Lasku&body=" + generateSpostiLasku(lasku)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void openlasku(Lasku lasku) {
        try {
            File file = new File("lasku.html");
            FileWriter writer = new FileWriter(file);
            writer.write(generatePaperiLasku(lasku));
            writer.close();

            Desktop.getDesktop().browse(file.toURI());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generatePaperiLasku(Lasku lasku) {
        String body = "<div style=\"font-family: Calibri\">";
        body += "<h3>VillageNewbies Lasku</h3>";
        body += "<hr/>";
        body += "<h4>Vastaanottaja: </h4>";
        body += "<p>" + lasku.getVaraus().getAsiakas().getEtunimi() + " " + lasku.getVaraus().getAsiakas().getSukunimi() + "<br/>";
        body += "" + lasku.getVaraus().getAsiakas().getLahiosoite() + "<br/>";
        body += "" + lasku.getVaraus().getAsiakas().getPostinro() + " " + lasku.getVaraus().getAsiakas().getPostitoimipaikka() + "</p><br/>";
        body += "<hr/>";
        body += "<h4>Maksun erittely: </h4>";
        body += "<p>" + "Mökki (" + lasku.getVaraus().getMokki().getMokkinimi() + "): " + lasku.getVaraus().getMokki().getHinta() + "€ " + "<br/>";
        body += "<span style=\"padding-left: 20px\">" + "Palvelut:" + "</span><br/>";
        for (VarauksenPalvelu x : lasku.getVaraus().getPalvelut()) {
            body += "<span style=\"padding-left: 40px\">-" + x.getPalvelu().getNimi() + ": " + x.getPalvelu().getHinta() + "€ + alv " + x.getPalvelu().getAlv() + "€ (" + x.getLkm() +" kpl)" + "</span><br/>";
        }
        body += "</p><br/>";
        body += "<hr/>";
        body += "<h4>Lopullinen hinta:</h4>";
        body += "<p>";

        body += "<span style=\"padding-left: 30px\">Hinta: " + lasku.getSumma() + "€</span><br/>";
        body += "<span style=\"padding-left: 20px\">+ Alv: " + lasku.getAlv() + "€</span><br/>";
        body += "____________________<br/>";
        body += "<span style=\"padding-left: 0px\"> Yhteensä: </span><span style=\"padding-left: 20px\">" + (Double.parseDouble(lasku.getSumma()) + Double.parseDouble(lasku.getAlv())) + "€</span><br/>";
        body += "</p><hr/>";

        body += "<h4>Maksutiedot:</h4>" +
                "<p>Tilinumero: FI12345678910 <br/>" +
                "Viitenumero: 12345678910 <br/>";

        String erapvm = "null";
        if (lasku.getVaraus().getVarattu_loppupvm() != null) {
            erapvm = lasku.getVaraus().getVarattu_loppupvm();
        }

        body += "eräpäivä: " + erapvm.split(" ")[0];

        body += "<hr/>Onko jokin tiedoista väärin tai sinulla on kysyttävää? Ota meihin yhteyttä ja kerro varaustunnuksesi: " + lasku.getVaraus().getVaraus_id();
        body += "<br/><br/>Kiitoksia asioinnistanne ja tervetuloa uudelleen!<br/>" +
                "<br/>" +
                "Ystävällisin terveisin, VillageNewbies! </div>";


        return body;
    }

    public static String generateSpostiLasku(Lasku lasku) {
        try {
            FileInputStream pohja = new FileInputStream("src/frontend/ObjectUI/LaskuHallinta/LaskuGenerator/laskuPohja.txt");
            Scanner scanner = new Scanner(pohja);

            String body = "";

            int i = 1;
            while(scanner.hasNextLine()) {
                String rivi = scanner.nextLine();
                System.out.println(i + "    " + rivi);
                body += rivi + "%0D%0A";
                if (rivi.equals("Vastaanottaja:")) {
                    body += "    " + lasku.getVaraus().getAsiakas().getEtunimi() + " " + lasku.getVaraus().getAsiakas().getSukunimi() + "%0D%0A";
                    body += "    " + lasku.getVaraus().getAsiakas().getLahiosoite() + "%0D%0A";
                    body += "    " + lasku.getVaraus().getAsiakas().getPostinro() + " " + lasku.getVaraus().getAsiakas().getPostitoimipaikka() + "%0D%0A";
                }
                if (rivi.equals("Maksun erittely:")) {
                    body += "    " + "Mökki (" + lasku.getVaraus().getMokki().getMokkinimi() + "): " + lasku.getVaraus().getMokki().getHinta() + "€ " + "%0D%0A";
                    body += "    " + "Palvelut:" + "%0D%0A";
                    for (VarauksenPalvelu x : lasku.getVaraus().getPalvelut()) {
                        body += "    " + "    -" + x.getPalvelu().getNimi() + ": " + x.getPalvelu().getHinta() + "€ + alv " + x.getPalvelu().getAlv() + "€ (" + x.getLkm() +" kpl)" + "%0D%0A";
                    }
                    body += "%0D%0A";
                    body += "    " + "Lopullinen hinta:" + "%0D%0A";
                    body += "      " + "Hinta: " + lasku.getSumma() + "€ " + "%0D%0A";
                    body += "  +  " + "Alv: " + lasku.getAlv() + "€ " + "%0D%0A";
                    body += "_______________________________" + "%0D%0A";
                    body += "    " + "Yhteensä:          " + (Double.parseDouble(lasku.getSumma()) + Double.parseDouble(lasku.getAlv())) + "€ " + "%0D%0A";
                }
                if (rivi.equals("eräpäivä:")) {
                    String erapvm = "null";
                    if (lasku.getVaraus().getVarattu_loppupvm() != null) {
                        erapvm = lasku.getVaraus().getVarattu_loppupvm();
                    }
                    body = body.substring(0, body.length() - 6);
                    body += "    " + erapvm.split(" ")[0] + "%0D%0A";
                    body += "Onko jokin tiedoista väärin tai sinulla on kysyttävää? Ota meihin yhteyttä ja kerro varaustunnuksesi: " + lasku.getVaraus().getVaraus_id() + "%0D%0A";
                    body += "%0D%0A";
                }
                i++;
            }

            body += "%0D%0A";
            body += "%0D%0A";

            body = body.replaceAll(" ", "%20");
            body = body.replaceAll("\\.", "%2E");
            return body;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //sendlasku(BackendAPI.getLasku(new HashMap<>()).get(0));

        openlasku(BackendAPI.getLasku(new HashMap<>()).get(0));
    }
}

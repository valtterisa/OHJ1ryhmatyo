package src.frontend.ObjectUI.LaskuHallinta.LaskuGenerator;

import javafx.scene.web.WebView;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Lasku;
import src.backend.datatypes.VarauksenPalvelu;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;

public class LaskuGenerator {

    public static void sendlasku(Lasku lasku) {
        try {
            Desktop.getDesktop().mail(new URI("mailto:" + lasku.getVaraus().getAsiakas().getEmail() + "?subject=VillageNewbies%20Lasku&body=" + generateLasku(lasku)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static String generateLasku(Lasku lasku) {
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
                        body += "    " + "    -" + x.getPalvelu().getNimi() + ": " + x.getPalvelu().getHinta() + "€ + alv" + x.getPalvelu().getAlv() + "€ (" + x.getLkm() +" kpl)" + "%0D%0A";
                    }
                    body += "%0D%0A";
                    body += "    " + "Lopullinen hinta:" + "%0D%0A";
                    body += "      " + "Hinta: " + lasku.getSumma() + "€ " + "%0D%0A";
                    body += "  +  " + "Alv: " + lasku.getAlv() + "€ " + "%0D%0A";
                    body += "_______________________________" + "%0D%0A";
                    body += "    " + "Yhteensä:          " + (Double.parseDouble(lasku.getSumma()) + Double.parseDouble(lasku.getAlv())) + "€ " + "%0D%0A";
                }
                if (rivi.equals("eräpäivä:")) {
                    body = body.substring(0, body.length() - 6);
                    body += "    " + lasku.getVaraus().getVarattu_loppupvm().split(" ")[0] + "%0D%0A";
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
        WebView webView = new WebView();
        webView.getEngine().loadContent(generateLasku(BackendAPI.getLasku(new HashMap<>()).get(0)));
    }
}

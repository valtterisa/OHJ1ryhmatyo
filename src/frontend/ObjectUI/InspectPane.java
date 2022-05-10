package src.frontend.ObjectUI;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.backend.datatypes.*;

import java.util.ArrayList;


public class InspectPane extends VBox {

    private Varaus selectedVaraus;

    public InspectPane(Varaus selectedItem) {
        this.selectedVaraus = selectedItem;

        if (this.selectedVaraus != null) {

            this.setPadding(new Insets(10));

            VBox varausInfo = new VBox();

            Text varausTitle = new Text("Varauksen tiedot: ");
            Text varausId = new Text("Varaus-id: " + selectedVaraus.getVaraus_id());
            Text varausDate = new Text("Varattu: " + selectedVaraus.getVarattu_pvm());
            Text varausStartDate = new Text("Alkaa: " + selectedVaraus.getVarattu_alkupvm());
            Text varausEndtDate = new Text("Päättyy: " + selectedVaraus.getVarattu_alkupvm());
            Text varausconfirmationDate = new Text("Vahvistettu: " + selectedVaraus.getVahvistus_pvm());

            ArrayList<VarauksenPalvelu> palvelut = selectedItem.getPalvelut();

            Text varausPalvelut = new Text("Varauksen palvelut: +\n");
            for (VarauksenPalvelu vp : palvelut) {
                varausPalvelut.setText(varausPalvelut.getText() + "- " + vp.getPalvelu().getNimi() + "(" + vp.getLkm() + " kpl.)");
            }

            varausTitle.setFont(new Font(20));
            varausTitle.setUnderline(true);

            varausInfo.getChildren().add(varausTitle);
            varausInfo.getChildren().add(varausId);
            varausInfo.getChildren().add(varausDate);
            varausInfo.getChildren().add(varausStartDate);
            varausInfo.getChildren().add(varausEndtDate);
            varausInfo.getChildren().add(varausconfirmationDate);



            Asiakas varaaja = selectedItem.getAsiakas();

            VBox asiakasInfo = new VBox();

            Text asiakasTitle = new Text("Varaajan tiedot: ");
            Text asiakasId = new Text("Asiakas-id: " + varaaja.getAsiakas_id());
            Text asiakasName = new Text("Nimi: " + varaaja.getEtunimi() + " " + varaaja.getSukunimi());
            Text asiakasAddress = new Text("Lähiosoite: " + varaaja.getLahiosoite());
            Text asiakasPostal = new Text("Postinumero ja toimipaikka: " + varaaja.getPostinro() + ", " + varaaja.getPostitoimipaikka());
            Text asiakasPhone = new Text("Puhelinnumero: " + varaaja.getPuhelinnro());
            Text asiakasEmail = new Text("Sähköpostiosoite: " + varaaja.getEmail());

            asiakasTitle.setFont(new Font(20));
            asiakasTitle.setUnderline(true);


            asiakasInfo.getChildren().add(asiakasTitle);
            asiakasInfo.getChildren().add(asiakasId);
            asiakasInfo.getChildren().add(asiakasName);
            asiakasInfo.getChildren().add(asiakasAddress);
            asiakasInfo.getChildren().add(asiakasPostal);
            asiakasInfo.getChildren().add(asiakasPhone);
            asiakasInfo.getChildren().add(asiakasEmail);


            Mokki mokki = selectedItem.getMokki();

            VBox mokkiInfo = new VBox();

            Text mokkiTitle = new Text("Mökin tiedot: ");
            Text mokkiId = new Text("Mökki-id: " + mokki.getMokki_id());
            Text mokkiName = new Text("Nimi: " + mokki.getMokkinimi());
            Text mokkiKuvaus = new Text("Kuvaus: " + mokki.getKuvaus() + "\n(alue: " + mokki.getAlue() + ", hlömäärä: " + mokki.getHenkilomaara() + ")");
            Text mokkiVarustelu = new Text("Varustelu: " + mokki.getVarustelu());
            Text mokkiAddress = new Text("Katuosoite: " + mokki.getKatuosoite());
            Text mokkiPostal = new Text("Postinumero ja toimipaikka: " + mokki.getPostinro() + ", " + mokki.getPostitoimipaikka());


            mokkiTitle.setFont(new Font(20));
            mokkiTitle.setUnderline(true);


            mokkiInfo.getChildren().add(mokkiTitle);
            mokkiInfo.getChildren().add(mokkiId);
            mokkiInfo.getChildren().add(mokkiName);
            mokkiInfo.getChildren().add(mokkiKuvaus);
            mokkiInfo.getChildren().add(mokkiVarustelu);
            mokkiInfo.getChildren().add(mokkiAddress);
            mokkiInfo.getChildren().add(mokkiPostal);

            this.getChildren().addAll(varausInfo, asiakasInfo, mokkiInfo);
        } else {
            this.setPadding(new Insets(0));
        }
    }
}

package src.frontend.ObjectUI.VarausHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.VarauksenPalvelu;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.MokkiHallinta.MokkiDeleteNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiEditNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiHallintaNakyma;

import java.util.ArrayList;

public class VarausInspectPane extends VBox {

    private Varaus selectedVaraus;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public VarausInspectPane(Varaus selectedItem) {
        this.selectedVaraus = selectedItem;
        this.editButton = new Button("Muokkaa valittua varausta");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu varaus");

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new VarausEditNakyma(this.selectedVaraus, editWindow, (VarausHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan varausta: id = " + this.selectedVaraus.getVaraus_id());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new VarausDeleteNakyma(this.selectedVaraus, deleteWindow, (VarausHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan varaus: id = " + this.selectedVaraus.getVaraus_id());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedVaraus = null;
            this.getChildren().removeAll(this.getChildren());
            VarausHallintaNakyma parent = (VarausHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void construct() {
        if (this.selectedVaraus != null) {

            // VARAUS
            VBox varausInfo = new VBox();

            Text varausTitle = new Text("Varauksen tiedot: ");
            Text varausId = new Text("Varaus-id: " + selectedVaraus.getVaraus_id());
            Text varausDate = new Text("Varattu: " + selectedVaraus.getVarattu_pvm());
            Text varausStartDate = new Text("Alkaa: " + selectedVaraus.getVarattu_alkupvm());
            Text varausEndtDate = new Text("Päättyy: " + selectedVaraus.getVarattu_alkupvm());
            Text varausconfirmationDate = new Text("Vahvistettu: " + selectedVaraus.getVahvistus_pvm());

            ArrayList<VarauksenPalvelu> palvelut = selectedVaraus.getPalvelut();

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

            // ASIAKAS
            Asiakas varaaja = selectedVaraus.getAsiakas();

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

            // MÖKKI
            Mokki mokki = selectedVaraus.getMokki();

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

            this.getChildren().addAll(varausInfo, asiakasInfo, mokkiInfo, editButton, deleteButton, closeButton);
        }
    }

    private void applyStyle() {
        if (this.selectedVaraus == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}

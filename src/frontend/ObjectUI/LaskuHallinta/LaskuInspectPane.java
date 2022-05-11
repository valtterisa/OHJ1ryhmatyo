package src.frontend.ObjectUI.LaskuHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Lasku;
import src.backend.datatypes.VarauksenPalvelu;
import src.frontend.ObjectUI.AsiakasHallinta.AsiakasDeleteNakyma;
import src.frontend.ObjectUI.AsiakasHallinta.AsiakasEditNakyma;
import src.frontend.ObjectUI.AsiakasHallinta.AsiakasHallintaNakyma;

public class LaskuInspectPane extends VBox {

    private Lasku selectedLasku;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public LaskuInspectPane(Lasku selectedItem) {
        this.selectedLasku = selectedItem;
        this.editButton = new Button("Muokkaa valittua laskua");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu lasku");

        generateActions();
        construct();
        applyStyle();
    }
    private void construct() {
        if (this.selectedLasku != null) {
            Lasku lasku = selectedLasku;

            VBox laskuInfo = new VBox();

            Text laskuTitle = new Text("Laskun tiedot: ");
            Text laskuId = new Text("Lasku-id: " + lasku.getLasku_id());
            Text mokkiNimi = new Text("Mökki: " + lasku.getVaraus().getMokki().getMokkinimi());
            Text asiakasNimi = new Text("Asiakkaan nimi: " + lasku.getVaraus().getAsiakas().getEtunimi() + " " + lasku.getVaraus().getAsiakas().getSukunimi());
            Text asiakasOsoite = new Text("Laskutusosoite: " + lasku.getVaraus().getAsiakas().getLahiosoite());
            Text asiakasPostal = new Text("Postinumero ja toimipaikka: " + lasku.getVaraus().getAsiakas().getPostinro() + ", " + lasku.getVaraus().getAsiakas().getPostitoimipaikka());
            Text asiakasPhone = new Text("Puhelinnumero: " + lasku.getVaraus().getAsiakas().getPuhelinnro());
            Text asiakasEmail = new Text("Sähköpostiosoite: " + lasku.getVaraus().getAsiakas().getEmail());

            Text laskuSummaJaALv = new Text("Loppusumma: " + lasku.getSumma() + "€, Alv: " + lasku.getAlv() + "€ = " + (0.00 + Double.parseDouble(lasku.getSumma()) + Double.parseDouble(lasku.getAlv())) + "€");
            Text erittely = new Text("Erittely: ");

            erittely.setText("Mökki: " + lasku.getVaraus().getMokki().getHinta() + "€");
            for (VarauksenPalvelu x : lasku.getVaraus().getPalvelut()) {
                erittely.setText(erittely.getText() + "\n- Palvelu: " + x.getPalvelu().getNimi() + ", "+ x.getLkm() + "kpl x " + x.getPalvelu().getHinta() + " + Alv: " + x.getPalvelu().getAlv() + "€ per palvelu)");
            }

            laskuTitle.setFont(new Font(20));
            laskuTitle.setUnderline(true);


            laskuInfo.getChildren().add(laskuTitle);
            laskuInfo.getChildren().add(laskuId);
            laskuInfo.getChildren().add(mokkiNimi);
            laskuInfo.getChildren().add(asiakasNimi);
            laskuInfo.getChildren().add(asiakasOsoite);
            laskuInfo.getChildren().add(asiakasPostal);
            laskuInfo.getChildren().add(asiakasPhone);
            laskuInfo.getChildren().add(asiakasEmail);
            laskuInfo.getChildren().add(laskuSummaJaALv);
            laskuInfo.getChildren().add(erittely);


            //this.getChildren().addAll(laskuInfo, editButton, deleteButton, closeButton);
            this.getChildren().addAll(laskuInfo, deleteButton, closeButton);
        }
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new LaskuEditNakyma(this.selectedLasku, editWindow, (LaskuHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan laskua: id = " + this.selectedLasku.getLasku_id());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new LaskuDeleteNakyma(this.selectedLasku, deleteWindow, (LaskuHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan lasku: id = " + this.selectedLasku.getLasku_id());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedLasku = null;
            this.getChildren().removeAll(this.getChildren());
            LaskuHallintaNakyma parent = (LaskuHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void applyStyle() {
        if (this.selectedLasku == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}


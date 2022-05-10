package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Asiakas;

public class AsiakasInspectPane extends VBox {

    private Asiakas selectedAsiakas;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public AsiakasInspectPane(Asiakas selectedItem) {
        this.selectedAsiakas = selectedItem;
        this.editButton = new Button("Muokkaa valittua asiakasta");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu asiakas");

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        if (this.selectedAsiakas != null) {
            Asiakas asiakas = selectedAsiakas;

            VBox asiakasInfo = new VBox();

            Text asiakasTitle = new Text("asiakkaan tiedot: ");
            Text asiakasId = new Text("Asiakas-id: " + asiakas.getAsiakas_id());
            Text asiakasName = new Text("Nimi: " + asiakas.getEtunimi() + " " + asiakas.getSukunimi());
            Text asiakasAddress = new Text("Lähiosoite: " + asiakas.getLahiosoite());
            Text asiakasPostal = new Text("Postinumero ja toimipaikka: " + asiakas.getPostinro() + ", " + asiakas.getPostitoimipaikka());
            Text asiakasPhone = new Text("Puhelinnumero: " + asiakas.getPuhelinnro());
            Text asiakasEmail = new Text("Sähköpostiosoite: " + asiakas.getEmail());

            asiakasTitle.setFont(new Font(20));
            asiakasTitle.setUnderline(true);


            asiakasInfo.getChildren().add(asiakasTitle);
            asiakasInfo.getChildren().add(asiakasId);
            asiakasInfo.getChildren().add(asiakasName);
            asiakasInfo.getChildren().add(asiakasAddress);
            asiakasInfo.getChildren().add(asiakasPostal);
            asiakasInfo.getChildren().add(asiakasPhone);
            asiakasInfo.getChildren().add(asiakasEmail);


            asiakasTitle.setFont(new Font(20));
            asiakasTitle.setUnderline(true);

            this.getChildren().addAll(asiakasInfo, editButton, deleteButton, closeButton);
        }
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new AsiakasEditNakyma(this.selectedAsiakas, editWindow, (AsiakasHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan asiakasta: id = " + this.selectedAsiakas.getAsiakas_id());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new AsiakasDeleteNakyma(this.selectedAsiakas, deleteWindow, (AsiakasHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan asiakas: id = " + this.selectedAsiakas.getAsiakas_id());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedAsiakas = null;
            this.getChildren().removeAll(this.getChildren());
            AsiakasHallintaNakyma parent = (AsiakasHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void applyStyle() {
        if (this.selectedAsiakas == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}


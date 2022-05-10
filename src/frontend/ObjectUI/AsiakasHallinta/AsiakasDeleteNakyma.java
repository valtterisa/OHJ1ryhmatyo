package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Mokki;

import java.util.HashMap;

public class AsiakasDeleteNakyma extends VBox {

    private Text title = new Text();

    private Button confirmButton;
    private Button cancelButton;

    private Asiakas vanhaAsiakas;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private AsiakasHallintaNakyma parent;

    public AsiakasDeleteNakyma(Asiakas asiakas, Stage stage, AsiakasHallintaNakyma parent) {

        this.stage = stage;
        this.parent = parent;

        this.confirmButton = new Button("Kyllä");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Oletko varma, että halat poistaa asiakkaan: ID = " + asiakas.getAsiakas_id() + "\nTätä toimintoa EI VOI peruuttaa");

        this.vanhaAsiakas = asiakas;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        confirmButton.setOnAction(e -> {
            HashMap<String, String> params = new HashMap<>();
            params.put("asiakas_id", this.vanhaAsiakas.getAsiakas_id() + "");

            String deleteMSG = BackendAPI.deleteAsiakas(params);
            System.out.println(deleteMSG);

            buttonPane.getChildren().clear();
            cancelButton.setText("Sulje");
            this.title.setText("Poisto onnistui. Poistettujen asiakkaiden määrä: " + deleteMSG);
            buttonPane.getChildren().add(cancelButton);
            parent.getTable().getItems().removeAll(parent.getTable().getItems());
        });
        cancelButton.setOnAction(e -> {
            this.stage.close();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(confirmButton, cancelButton);
        this.getChildren().addAll(title, buttonPane);
    }

    private void applyStyle() {
        this.setPadding(new Insets(20));
        this.buttonPane.setSpacing(30);
        this.buttonPane.setPadding(new Insets(10));
    }
}

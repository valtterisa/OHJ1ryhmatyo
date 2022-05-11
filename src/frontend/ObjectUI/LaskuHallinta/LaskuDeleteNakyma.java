package src.frontend.ObjectUI.LaskuHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Lasku;
import src.frontend.ObjectUI.AsiakasHallinta.AsiakasHallintaNakyma;

import java.util.HashMap;

public class LaskuDeleteNakyma extends VBox {

    private Text title = new Text();

    private Button confirmButton;
    private Button cancelButton;

    private Lasku vanhaLasku;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private LaskuHallintaNakyma parent;

    public LaskuDeleteNakyma(Lasku lasku, Stage stage, LaskuHallintaNakyma parent) {

        this.stage = stage;
        this.parent = parent;

        this.confirmButton = new Button("Kyllä");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Oletko varma, että halat poistaa laskun: ID = " + lasku.getLasku_id() + "\nTätä toimintoa EI VOI peruuttaa");

        this.vanhaLasku = lasku;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        confirmButton.setOnAction(e -> {
            HashMap<String, String> params = new HashMap<>();
            params.put("lasku_id", this.vanhaLasku.getLasku_id() + "");

            String deleteMSG = BackendAPI.deleteLasku(params);
            System.out.println(deleteMSG);

            buttonPane.getChildren().clear();
            cancelButton.setText("Sulje");
            this.title.setText("Poisto onnistui. Poistettujen laskujen määrä: " + deleteMSG);
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

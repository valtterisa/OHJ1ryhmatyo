package src.frontend.ObjectUI.AlueHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Alue;

import java.util.HashMap;

public class AlueDeleteNakyma extends VBox {
    private Text title = new Text();

    private Button confirmButton;
    private Button cancelButton;

    private Alue vanhaAlue;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private AlueHallintaNakyma parent;

    public AlueDeleteNakyma(Alue alue, Stage stage, AlueHallintaNakyma parent) {
        this.stage = stage;
        this.parent = parent;

        this.confirmButton = new Button("Kyllä");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Oletko varma, että halat poistaa alueen: ID = " + alue.getId() + "\nTätä toimintoa EI VOI peruuttaa");

        this.vanhaAlue = alue;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        confirmButton.setOnAction(e -> {
            HashMap<String, String> params = new HashMap<>();
            params.put("alue_id", this.vanhaAlue.getId() + "");

            String deleteMSG = BackendAPI.deleteAlue(params);
            System.out.println(deleteMSG);

            buttonPane.getChildren().clear();
            cancelButton.setText("Sulje");
            this.title.setText("Poisto onnistui. Poistettujen alueiden määrä: " + deleteMSG);
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

package src.frontend.ObjectUI.AlueHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Mokki;

import java.util.HashMap;

public class AlueAdditionPane extends VBox {
    private Text title = new Text("Lisää uusi alue: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField alueIdTF = new TextField();
    private TextField alueTF = new TextField();
    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public AlueAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Alue alue = BackendAPI.postAlue(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Alueen lisäys onnistui \n" + alue);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
        clearButton.setOnAction(e -> {
            this.params.clear();
            alueIdTF.clear();
            alueTF.clear();
        });
    }

    private void generateParams() {
        params.clear();

        String alueId = alueIdTF.getText();
        String alue = alueTF.getText();

        if (alueId.length() > 0) {
            params.put("alue_id", alueId);
        }
        if (alue.length() > 0) {
            params.put("nimi", alue);
        }
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title, alueIdTF, alueTF, buttonPane);
    }

    private void applyStyle() {
        alueIdTF.setPromptText("Alue-Id");
        alueTF.setPromptText("alueen nimi");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

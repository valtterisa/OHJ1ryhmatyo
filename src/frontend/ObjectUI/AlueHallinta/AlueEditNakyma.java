package src.frontend.ObjectUI.AlueHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Mokki;

import java.util.ArrayList;
import java.util.HashMap;

public class AlueEditNakyma extends VBox {
    private HashMap<String, String> params = new HashMap<>();

    private Text title = new Text();

    private Button saveButton;
    private Button cancelButton;

    private Alue vanhaAlue;

    private TextField AlueTF;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private AlueHallintaNakyma parent;

    public AlueEditNakyma(Alue alue, Stage stage, AlueHallintaNakyma parent) {
        this.stage = stage;
        this.parent = parent;

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Muokataan aluetta: ID = " + alue.getId());

        this.vanhaAlue = alue;

        this.AlueTF = new TextField(alue.getNimi());

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        saveButton.setOnAction(e -> {
            generateParameters();

            Alue uusiAlue = BackendAPI.updateAlue(params, vanhaAlue.getId() + "");
            System.out.println("PÄIVITETTY!!! : " + uusiAlue);

            parent.getTable().getItems().removeAll(parent.getTable().getItems());

            HashMap<String, String> newParams = new HashMap<>();
            newParams.put("alue_id", this.vanhaAlue.getId() + "");

            ArrayList<Alue> alueet = BackendAPI.getAlue(newParams);
            System.out.println(alueet);
            for (Alue x : alueet) {
                parent.table.getItems().add(x);
            }

            this.stage.close();
        });
        cancelButton.setOnAction(e -> {
            this.stage.close();
        });
    }

    private void generateParameters() {
        this.params.clear();

        String mokkiAlueId = AlueTF.getText();
        this.params.put("nimi", mokkiAlueId);
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(title, AlueTF, buttonPane);
    }

    private void applyStyle() {

        AlueTF.setPromptText("Alueen nimi");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}



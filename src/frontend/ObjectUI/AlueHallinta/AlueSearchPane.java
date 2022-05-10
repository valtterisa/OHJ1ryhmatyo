package src.frontend.ObjectUI.AlueHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Mokki;
import src.frontend.ObjectUI.MokkiHallinta.MokkiTable;

import java.util.ArrayList;
import java.util.HashMap;

public class AlueSearchPane extends VBox {
    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private AlueTable table;

    private TextField AlueIdTF = new TextField();
    private TextField AlueTF = new TextField();

    private HBox buttonPane = new HBox();

    public AlueSearchPane(Button searchButton, Button clearButton, AlueTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }
    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);
        this.getChildren().addAll(title, AlueIdTF, AlueTF, buttonPane);
    }

    private void generateActions() {
        searchButton.setOnAction(e -> {
            generateParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Alue> alueet = BackendAPI.getAlue(params);
            System.out.println(alueet);
            for (Alue x : alueet) {
                table.getItems().add(x);
            }
            table.refresh();
        });
        clearButton.setOnAction(e -> {
            this.params.clear();
            AlueIdTF.clear();
            AlueTF.clear();
        });
    }

    private void generateParams() {
        this.params.clear();
        String alueId = AlueIdTF.getText();
        String alue = AlueTF.getText();

        if (alueId.length() > 0) {
            params.put("alue_id", alueId);
        }
        if (alue.length() > 0) {
            params.put("nimi", alue);
        }
    }

    private void applyStyle() {
        AlueIdTF.setPromptText("Alue-Id");
        AlueTF.setPromptText("Alueen nimi");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}


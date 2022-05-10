package src.frontend.ObjectUI.PalveluHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Palvelu;

import java.util.ArrayList;
import java.util.HashMap;

public class PalveluSearchPane extends VBox {

    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private PalveluTable table;

    private TextField palveluIdTF = new TextField();
    private TextField alueIdTF = new TextField();
    private TextField palveluNimiTF = new TextField();
    private TextField palveluTyyppiTF = new TextField();
    private TextField palveluKuvausTF = new TextField();
    private TextField palveluHintaTF = new TextField();
    private TextField palveluAlvTF = new TextField();

    private HBox buttonPane = new HBox();

    public PalveluSearchPane(Button searchButton, Button clearButton, PalveluTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);
        this.getChildren().addAll(title, palveluIdTF, alueIdTF, palveluNimiTF, palveluTyyppiTF, palveluKuvausTF, palveluHintaTF, palveluAlvTF, buttonPane);
    }

    private void generateActions() {
        searchButton.setOnAction(e -> {
            generateParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Palvelu> palvelut = BackendAPI.getPalvelu(params);
            System.out.println(palvelut);
            for (Palvelu x : palvelut) {
                table.getItems().add(x);
            }
            table.refresh();
        });
        clearButton.setOnAction(e -> {
            this.params.clear();
            palveluIdTF.clear();
            alueIdTF.clear();
            palveluNimiTF.clear();
            palveluTyyppiTF.clear();
            palveluKuvausTF.clear();
            palveluHintaTF.clear();
            palveluAlvTF.clear();
        });
    }

    private void generateParams() {
        this.params.clear();
        String palveluId = palveluIdTF.getText();
        String alueId = alueIdTF.getText();
        String palveluNimi = palveluNimiTF.getText();
        String palveluTyyppi =  palveluTyyppiTF.getText();
        String palveluKuvaus =  palveluKuvausTF.getText();
        String palveluHinta =  palveluHintaTF.getText();
        String palveluAlv = palveluAlvTF.getText();

        if (palveluId.length() > 0) {
            params.put("palvelu_id", palveluId);
        }
        if (alueId.length() > 0) {
            params.put("alue_id", alueId);
        }
        if (palveluNimi.length() > 0) {
            params.put("nimi", palveluNimi);
        }
        if (palveluTyyppi.length() > 0) {
            params.put("tyyppi", palveluTyyppi);
        }
        if (palveluKuvaus.length() > 0) {
            params.put("kuvaus", palveluKuvaus);
        }
        if (palveluHinta.length() > 0) {
            params.put("hinta", palveluHinta);
        }
        if (palveluAlv.length() > 0) {
            params.put("alv", palveluAlv);
        }
    }

    private void applyStyle() {
        palveluIdTF.setPromptText("Palvelu-Id");
        alueIdTF.setPromptText("Alue-Id");
        palveluNimiTF.setPromptText("Nimi");
        palveluTyyppiTF.setPromptText("Tyyppi");
        palveluKuvausTF.setPromptText("Kuvaus");
        palveluHintaTF.setPromptText("Hinta");
        palveluAlvTF.setPromptText("Alv");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

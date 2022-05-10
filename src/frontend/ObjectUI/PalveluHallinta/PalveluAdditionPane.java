package src.frontend.ObjectUI.PalveluHallinta;

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
import src.backend.datatypes.Palvelu;

import java.util.HashMap;

public class PalveluAdditionPane extends VBox {

    private Text title = new Text("Lisää uusi palvelu: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField palveluIdTF = new TextField();
    private TextField alueIDTF = new TextField();
    private TextField palveluNimiTF = new TextField();
    private TextField palveluTyyppiTF = new TextField();
    private TextField palveluKuvausTF = new TextField();
    private TextField palveluHintaTF = new TextField();
    private TextField palveluAlvTF = new TextField();

    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public PalveluAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Palvelu palvelu = BackendAPI.postPalvelu(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Mökin lisäys onnistui \n" + palvelu);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
        clearButton.setOnAction(e -> {
            params.clear();

            palveluIdTF.clear();
            alueIDTF.clear();
            palveluNimiTF.clear();
            palveluTyyppiTF.clear();
            palveluKuvausTF.clear();
            palveluHintaTF.clear();
            palveluAlvTF.clear();
        });
    }

    private void generateParams() {
        params.clear();

        String palveluId = palveluIdTF.getText();
        String alueId = alueIDTF.getText();
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

    private void construct() {
        this.buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title, palveluIdTF, alueIDTF, palveluNimiTF, palveluTyyppiTF, palveluKuvausTF, palveluHintaTF, palveluAlvTF, buttonPane);
    }

    private void applyStyle() {
        palveluIdTF.setPromptText("Palvelu-Id");
        alueIDTF.setPromptText("Alue-Id");
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

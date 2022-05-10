package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;

import java.util.HashMap;

public class AsiakasAdditionPane extends VBox {

    private Text title = new Text("Lisää uusi mökki: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField asiakasIdTF = new TextField();
    private TextField asiakasEtunimiTF = new TextField();
    private TextField asiakasSukunimiTF = new TextField();
    private TextField asiakasOsoiteTF = new TextField();
    private TextField asiakasPostinroTF = new TextField();
    private TextField asiakasEmailTF = new TextField();
    private TextField asiakasPuhTF = new TextField();

    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public AsiakasAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Asiakas asiakas = BackendAPI.postAsiakas(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Mökin lisäys onnistui \n" + asiakas);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
        clearButton.setOnAction(e -> {
            params.clear();

            this.params.clear();
            asiakasIdTF.clear();
            asiakasEtunimiTF.clear();
            asiakasSukunimiTF.clear();
            asiakasOsoiteTF.clear();
            asiakasPostinroTF.clear();
            asiakasEmailTF.clear();
            asiakasPuhTF.clear();
        });
    }

    private void generateParams() {
        params.clear();

        this.params.clear();
        String asiakasId = asiakasIdTF.getText();
        String etunimi = asiakasEtunimiTF.getText();
        String sukunimi = asiakasSukunimiTF.getText();
        String osoite =  asiakasOsoiteTF.getText();
        String postinro =  asiakasPostinroTF.getText();
        String email =  asiakasEmailTF.getText();
        String puh = asiakasPuhTF.getText();

        if (asiakasId.length() > 0) {
            params.put("asiakas_id", asiakasId);
        }
        if (etunimi.length() > 0) {
            params.put("etunimi", etunimi);
        }
        if (sukunimi.length() > 0) {
            params.put("sukunimi", sukunimi);
        }
        if (osoite.length() > 0) {
            params.put("lahiosoite", osoite);
        }
        if (postinro.length() > 0) {
            params.put("postinro", postinro);
        }
        if (email.length() > 0) {
            params.put("email", email);
        }
        if (puh.length() > 0) {
            params.put("puhelinnro", puh);
        }
    }

    private void construct() {
        buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title, asiakasIdTF, asiakasEtunimiTF, asiakasSukunimiTF, asiakasOsoiteTF, asiakasPostinroTF, asiakasEmailTF, asiakasPuhTF, buttonPane);
    }

    private void applyStyle() {
        asiakasIdTF.setPromptText("Asiakas-Id");
        asiakasEtunimiTF.setPromptText("Etunimi");
        asiakasSukunimiTF.setPromptText("Sukunimi");
        asiakasOsoiteTF.setPromptText("Lähiosoite");
        asiakasPostinroTF.setPromptText("Postinumero");
        asiakasEmailTF.setPromptText("S.posti");
        asiakasPuhTF.setPromptText("Puhelinnro");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}


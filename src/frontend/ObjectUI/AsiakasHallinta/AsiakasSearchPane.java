package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;

import java.util.ArrayList;
import java.util.HashMap;

public class AsiakasSearchPane extends VBox {

    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private AsiakasTable table;

    private TextField asiakasIdTF = new TextField();
    private TextField asiakasEtunimiTF = new TextField();
    private TextField asiakasSukunimiTF = new TextField();
    private TextField asiakasOsoiteTF = new TextField();
    private TextField asiakasPostinroTF = new TextField();
    private TextField asiakasEmailTF = new TextField();
    private TextField asiakasPuhTF = new TextField();

    private HBox buttonPane = new HBox();

    public AsiakasSearchPane(Button searchButton, Button clearButton, AsiakasTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);
        this.getChildren().addAll(title, asiakasIdTF, asiakasEtunimiTF, asiakasSukunimiTF, asiakasOsoiteTF, asiakasPostinroTF, asiakasEmailTF, asiakasPuhTF, buttonPane);
    }

    private void generateActions() {
        searchButton.setOnAction(e -> {
            generateParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Asiakas> asiakkaat = BackendAPI.getAsiakas(params);
            System.out.println(asiakkaat);
            for (Asiakas x : asiakkaat) {
                table.getItems().add(x);
            }
            table.refresh();
        });
        clearButton.setOnAction(e -> {
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

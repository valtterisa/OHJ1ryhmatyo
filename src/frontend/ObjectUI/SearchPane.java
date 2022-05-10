package src.frontend.ObjectUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchPane extends VBox {
    private Button searchButton;
    private Button clearButton;

    private HashMap<String, String> mokki_params = new HashMap<>();
    private HashMap<String, String> asiakas_params = new HashMap<>();
    private HashMap<String, String> varaus_params  = new HashMap<>();


    private TextField mokkiIdTF = new TextField();
    private TextField mokkiNimiTF = new TextField();
    private TextField asiakasEtunimiTF = new TextField();
    private TextField asiakasSukunimiF = new TextField();
    private TextField asiakasPuhelinnroTF = new TextField();
    private TextField asiakasEmailTF = new TextField();

    private ArrayList<TextField> tfList = new ArrayList<>();
    private VarausTable table = new VarausTable();

    HBox buttonBox = new HBox();

    public SearchPane(Button searchButton, Button clearButton,  VarausTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;
        this.table = table;

        this.mokkiIdTF.setPromptText("Mökin id");
        this.mokkiNimiTF.setPromptText("Mökin nimi");
        this.asiakasEtunimiTF.setPromptText("Asiakkaan etunimi");
        this.asiakasSukunimiF.setPromptText("Asiakkaan sukunimi");
        this.asiakasPuhelinnroTF.setPromptText("Asiakkaan puh");
        this.asiakasEmailTF.setPromptText("Asiakkaan email");

        this.tfList = new ArrayList<>();
        tfList.add(mokkiIdTF);
        tfList.add(mokkiNimiTF);
        tfList.add(asiakasEtunimiTF);
        tfList.add(asiakasSukunimiF);
        tfList.add(asiakasPuhelinnroTF);
        tfList.add(asiakasEmailTF);

        generateOnClicks();

        this.generate();

        this.style();
    }

    private void generate() {

        buttonBox.getChildren().addAll(this.searchButton, this.clearButton);
        this.getChildren().addAll(tfList);
        this.getChildren().add(buttonBox);
    }

    private void generateOnClicks() {
        clearButton.setOnMouseClicked(e -> {
            clearParams();

            for (TextField tf : tfList) {
                tf.clear();
            }
        });

        searchButton.setOnMouseClicked(e -> {
            clearParams();
            generateMokkiParams();
            generateAsiakasParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Varaus> varaukset = BackendAPI.getVarausTiedoilla(varaus_params, mokki_params, asiakas_params);
            System.out.println(varaukset);
            for (Varaus x : varaukset) {
                table.getItems().add(x);
            }
            table.refresh();

        });
    }

    private void clearParams() {
        mokki_params.clear();
        asiakas_params.clear();
        varaus_params.clear();
    }

    private void generateAsiakasParams() {
        String etunimi = tfList.get(2).getText();
        String sukunimi = tfList.get(3).getText();
        String puhelinnro = tfList.get(4).getText();
        String email = tfList.get(5).getText();

        if (etunimi.length() > 0) {
            asiakas_params.put("etunimi", etunimi);
        }
        if (sukunimi.length() > 0) {
            asiakas_params.put("sukunimi", sukunimi);
        }
        if (puhelinnro.length() > 0) {
            asiakas_params.put("puhelinnro", puhelinnro);
        }
        if (email.length() > 0) {
            asiakas_params.put("email", email);
        }
    }

    private void generateMokkiParams() {
        String mokkiId = tfList.get(0).getText();
        String mokkiNimi = tfList.get(1).getText();

        if (mokkiId.length() > 0) {
            mokki_params.put("mokki_id", mokkiId);
        }
        if (mokkiNimi.length() > 0) {
            mokki_params.put("mokkinimi", mokkiNimi);
        }
    }

    private void style() {
        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonBox.setSpacing(30);
    }
}

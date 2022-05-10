package src.frontend.ObjectUI.VarausHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class VarausSearchPane extends VBox {

    private HashMap<String, String> mokkiParams = new HashMap<>();
    private HashMap<String, String> asiakasParams = new HashMap<>();
    private HashMap<String, String> varausParams  = new HashMap<>();

    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private VarausTable table;

    private TextField mokkiIdTF = new TextField();
    private TextField mokkiNameTF = new TextField();
    private TextField mokkiAlueIdTF = new TextField();
    private TextField mokkiOsoiteTF = new TextField();
    private TextField mokkiPostinroTF = new TextField();
    private TextField mokkiHenkilomaaraTF = new TextField();
    private TextField mokkiHintaTF = new TextField();

    private TextField asiakasIdTF = new TextField();
    private TextField asiakasEtunimiTF = new TextField();
    private TextField asiakasSukunimiTF = new TextField();
    private TextField asiakasPuhelinnroTF = new TextField();
    private TextField asiakasEmailTF = new TextField();

    private TextField varausIdTF = new TextField();
    private TextField varattuTF = new TextField();

    private HBox buttonPane = new HBox();

    private VBox mokkiPane;
    private VBox asiakasPane;
    private VBox varausPane;

    public VarausSearchPane(Button searchButton, Button clearButton, VarausTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        clearButton.setOnMouseClicked(e -> {
            clearParams();
            clearFields();
        });

        searchButton.setOnMouseClicked(e -> {
            clearParams();
            generateVarausParams();
            generateMokkiParams();
            generateAsiakasParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Varaus> varaukset = BackendAPI.getVarausTiedoilla(varausParams, mokkiParams, asiakasParams);
            System.out.println(varaukset);
            for (Varaus x : varaukset) {
                table.getItems().add(x);
            }
            table.refresh();

        });
    }

    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);

        mokkiPane = new VBox(title, mokkiIdTF, mokkiNameTF, mokkiAlueIdTF, mokkiOsoiteTF, mokkiPostinroTF, mokkiHenkilomaaraTF, mokkiHintaTF);
        asiakasPane = new VBox(asiakasIdTF, asiakasEtunimiTF, asiakasSukunimiTF, asiakasPuhelinnroTF, asiakasEmailTF);
        varausPane = new VBox(varausIdTF, varattuTF);

        this.getChildren().addAll(mokkiPane, asiakasPane, varausPane, buttonPane);
    }

    private void clearParams() {
        this.mokkiParams.clear();
        this.asiakasParams.clear();
        this.varausParams.clear();
    }

    private void clearFields(){
        mokkiIdTF.clear();
        mokkiNameTF.clear();
        mokkiAlueIdTF.clear();
        mokkiOsoiteTF.clear();
        mokkiPostinroTF.clear();
        mokkiHenkilomaaraTF.clear();
        mokkiHintaTF.clear();
        asiakasIdTF.clear();
        asiakasEtunimiTF.clear();
        asiakasSukunimiTF.clear();
        asiakasPuhelinnroTF.clear();
        asiakasEmailTF.clear();
        varausIdTF.clear();
        varattuTF.clear();
    }

    private void generateAsiakasParams() {
        String asiakasId = asiakasIdTF.getText();
        String etunimi = asiakasEtunimiTF.getText();
        String sukunimi = asiakasSukunimiTF.getText();
        String puhelinnro = asiakasPuhelinnroTF.getText();
        String email = asiakasEmailTF.getText();

        if (asiakasId.length() > 0) {
            asiakasParams.put("asiakas_id", asiakasId);
        }
        if (etunimi.length() > 0) {
            asiakasParams.put("etunimi", etunimi);
        }
        if (sukunimi.length() > 0) {
            asiakasParams.put("sukunimi", sukunimi);
        }
        if (puhelinnro.length() > 0) {
            asiakasParams.put("puhelinnro", puhelinnro);
        }
        if (email.length() > 0) {
            asiakasParams.put("email", email);
        }
    }
    private void generateMokkiParams() {
        String mokkiId = mokkiIdTF.getText();
        String mokkiNimi = mokkiNameTF.getText();
        String mokkiAlueId = mokkiAlueIdTF.getText();
        String mokkiOsoite = mokkiOsoiteTF.getText();
        String mokkiPostinro = mokkiPostinroTF.getText();
        String MokkiHenkilomaara = mokkiHenkilomaaraTF.getText();
        String mokkiHinta = mokkiHintaTF.getText();

        if (mokkiId.length() > 0) {
            mokkiParams.put("mokki_id", mokkiId);
        }
        if (mokkiNimi.length() > 0) {
            mokkiParams.put("mokkinimi", mokkiNimi);
        }
        if (mokkiAlueId.length() > 0) {
            mokkiParams.put("alue_id", mokkiAlueId);
        }
        if (mokkiOsoite.length() > 0) {
            mokkiParams.put("katuosoite", mokkiOsoite);
        }
        if (mokkiPostinro.length() > 0) {
            mokkiParams.put("postinro", mokkiPostinro);
        }
        if (MokkiHenkilomaara.length() > 0) {
            mokkiParams.put("henkilomaara", MokkiHenkilomaara);
        }
        if (mokkiHinta.length() > 0) {
            mokkiParams.put("hinta", mokkiHinta);
        }
    }

    private void generateVarausParams() {

        String varausId = varausIdTF.getText();
        String varattuPVM = varattuTF.getText();

        if (varausId.length() > 0) {
            mokkiParams.put("varaus_id", varausId);
        }
        if (varattuPVM.length() > 0) {
            mokkiParams.put("varattu_pvm", varattuPVM);
        }
    }

    private void applyStyle() {
        mokkiIdTF.setPromptText("Mokin Id");
        mokkiNameTF.setPromptText("Mokin nimi");
        mokkiAlueIdTF.setPromptText("Alue Id");
        mokkiOsoiteTF.setPromptText("Osoite");
        mokkiPostinroTF.setPromptText("Postinumero");
        mokkiHenkilomaaraTF .setPromptText("Henkilömäärä");
        mokkiHintaTF.setPromptText("Hinta");

        asiakasIdTF.setPromptText("Asiakkaan Id");
        asiakasEtunimiTF.setPromptText("Etunimi");
        asiakasSukunimiTF.setPromptText("sukunimi");
        asiakasPuhelinnroTF.setPromptText("Puhelinnro");
        asiakasEmailTF.setPromptText("S. posti");

        varausIdTF.setPromptText("Varauksen Id");
        varattuTF.setPromptText("Varattu PVM");


        this.setSpacing(10);

        mokkiPane.setPadding(new Insets(10));
        mokkiPane.setSpacing(10);
        asiakasPane.setPadding(new Insets(10));
        asiakasPane.setSpacing(10);
        varausPane.setPadding(new Insets(10));
        varausPane.setSpacing(10);

        this.buttonPane.setPadding(new Insets(10));
        this.buttonPane.setSpacing(30);
    }

}

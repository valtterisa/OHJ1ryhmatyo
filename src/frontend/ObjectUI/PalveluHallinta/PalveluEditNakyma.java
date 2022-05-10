package src.frontend.ObjectUI.PalveluHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Palvelu;

import java.util.ArrayList;
import java.util.HashMap;

public class PalveluEditNakyma extends VBox {


    private HashMap<String, String> params = new HashMap<>();

    private Text title = new Text();

    private Button saveButton;
    private Button cancelButton;

    private Palvelu vanhaPalvelu;

    private TextField palveluIdTF = new TextField();
    private TextField alueIDTF = new TextField();
    private TextField palveluNimiTF = new TextField();
    private TextField palveluTyyppiTF = new TextField();
    private TextField palveluKuvausTF = new TextField();
    private TextField palveluHintaTF = new TextField();
    private TextField palveluAlvTF = new TextField();

    private HBox buttonPane = new HBox();

    private Stage stage;
    private PalveluHallintaNakyma parent;

    public PalveluEditNakyma(Palvelu palvelu, Stage stage, PalveluHallintaNakyma parent) {
        this.stage = stage;
        this.parent = parent;

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Muokataan mökkiä: ID = " + palvelu.getPalvelu_id());

        this.vanhaPalvelu = palvelu;

        this.palveluIdTF = new TextField(palvelu.getPalvelu_id());
        this.alueIDTF = new TextField(palvelu.getAlue_id() + "");
        this.palveluNimiTF = new TextField(palvelu.getNimi());
        this.palveluTyyppiTF = new TextField(palvelu.getTyyppi());
        this.palveluKuvausTF = new TextField(palvelu.getKuvaus());
        this.palveluHintaTF = new TextField(palvelu.getHinta());
        this.palveluAlvTF = new TextField(palvelu.getAlv());

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        saveButton.setOnAction(e -> {
            generateParameters();

            Palvelu uusiPalvelu = BackendAPI.updatePalvelu(params, vanhaPalvelu.getPalvelu_id() + "");
            System.out.println("PÄIVITETTY!!! : " + uusiPalvelu);

            parent.getTable().getItems().removeAll(parent.getTable().getItems());

            HashMap<String, String> newParams = new HashMap<>();
            newParams.put("palvelu_id", this.vanhaPalvelu.getPalvelu_id() + "");

            ArrayList<Palvelu> palvelut = BackendAPI.getPalvelu(newParams);
            System.out.println(palvelut);
            for (Palvelu x : palvelut) {
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

        String palveluId = palveluIdTF.getText();
        String alueId = alueIDTF.getText();
        String palveluNimi =  palveluNimiTF.getText();
        String palveluTyyppi =  palveluTyyppiTF.getText();
        String palveluKuvaus =  palveluKuvausTF.getText();
        String palveluHinta = palveluHintaTF.getText();
        String palveluAlv = palveluAlvTF.getText();

        this.params.put("palvelu_id", palveluId);
        this.params.put("alue_id", alueId);
        this.params.put("nimi", palveluNimi);
        this.params.put("tyyppi", palveluTyyppi);
        this.params.put("kuvaus", palveluKuvaus);
        this.params.put("hinta", palveluHinta);
        this.params.put("alv", palveluAlv);
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(title, palveluIdTF, alueIDTF, palveluNimiTF, palveluTyyppiTF, palveluKuvausTF, palveluHintaTF, palveluAlvTF, buttonPane);
    }

    private void applyStyle() {
        palveluIdTF.setPromptText("Mökin nimi");
        alueIDTF.setPromptText("Alue-Id");
        palveluNimiTF.setPromptText("Mökin osoite");
        palveluTyyppiTF.setPromptText("Postinumero");
        palveluKuvausTF.setPromptText("Henkilömäärä");
        palveluHintaTF.setPromptText("Hinta");
        palveluAlvTF.setPromptText("Kuvaus");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

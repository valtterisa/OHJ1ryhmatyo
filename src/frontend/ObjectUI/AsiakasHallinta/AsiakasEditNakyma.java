package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;

import java.util.ArrayList;
import java.util.HashMap;

public class AsiakasEditNakyma extends VBox {

    private HashMap<String, String> params = new HashMap<>();

    private Text title = new Text();

    private Button saveButton;
    private Button cancelButton;

    private Asiakas vanhaAsiakas;

    //private TextField mokkiIdTF;
    private TextField asiakasIdTF;
    private TextField asiakasEtunimiTF;
    private TextField asiakasSukunimiTF;
    private TextField asiakasOsoiteTF;
    private TextField asiakaspostinumeroTF;
    private TextField asiakasEmailTF;
    private TextField asiakasPuhTF;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private AsiakasHallintaNakyma parent;

    public AsiakasEditNakyma(Asiakas asiakas, Stage stage, AsiakasHallintaNakyma parent) {
        this.stage = stage;
        this.parent = parent;

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Muokataan Asiakasta: ID = " + asiakas.getAsiakas_id());

        this.vanhaAsiakas = asiakas;

        this.asiakasIdTF = new TextField(asiakas.getAsiakas_id() +"");
        this.asiakasEtunimiTF = new TextField(asiakas.getEtunimi());
        this.asiakasSukunimiTF = new TextField(asiakas.getSukunimi());
        this.asiakasOsoiteTF = new TextField(asiakas.getLahiosoite());
        this.asiakaspostinumeroTF = new TextField(asiakas.getPostinro() + "");
        this.asiakasEmailTF = new TextField(asiakas.getEmail());
        this.asiakasPuhTF = new TextField(asiakas.getPuhelinnro());

        generateActions();
        construct();
        applyStyle();
    }
    private void generateActions() {
        saveButton.setOnAction(e -> {
            generateParameters();

            Asiakas uusiAsiakas = BackendAPI.updateAsiakas(params, vanhaAsiakas.getAsiakas_id() + "");
            System.out.println("PÄIVITETTY!!! : " + uusiAsiakas);

            parent.getTable().getItems().removeAll(parent.getTable().getItems());

            HashMap<String, String> newParams = new HashMap<>();
            newParams.put("asiakas_id", this.vanhaAsiakas.getAsiakas_id() + "");

            ArrayList<Asiakas> asiakkaat = BackendAPI.getAsiakas(newParams);
            System.out.println(asiakkaat);
            for (Asiakas x : asiakkaat) {
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

        String asiakasId = asiakasIdTF.getText();
        String enimi = asiakasEtunimiTF.getText();
        String snimi =  asiakasSukunimiTF.getText();
        String osoite =  asiakasOsoiteTF.getText();
        String postinro =  asiakaspostinumeroTF.getText();
        String email = asiakasEmailTF.getText();
        String puh = asiakasPuhTF.getText();

        this.params.put("asiakas_id", asiakasId);
        this.params.put("etunimi", enimi);
        this.params.put("sukunimi", snimi);
        this.params.put("lahiosoite", osoite);
        this.params.put("postinro", postinro);
        this.params.put("email", email);
        this.params.put("puhelinnro", puh);
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(title, asiakasIdTF, asiakasEtunimiTF, asiakasSukunimiTF, asiakasOsoiteTF, asiakaspostinumeroTF, asiakasEmailTF, asiakasPuhTF, buttonPane);
    }

    private void applyStyle() {
        asiakasIdTF.setPromptText("Asiakas-Id");
        asiakasEtunimiTF.setPromptText("Etunimi");
        asiakasSukunimiTF.setPromptText("Sukunimi");
        asiakasOsoiteTF.setPromptText("Lähiosoite");
        asiakaspostinumeroTF.setPromptText("Postinro");
        asiakasEmailTF.setPromptText("S.posti");
        asiakasPuhTF.setPromptText("Puhelinnro");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

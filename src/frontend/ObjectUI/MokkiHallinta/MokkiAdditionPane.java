package src.frontend.ObjectUI.MokkiHallinta;

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
import src.backend.datatypes.Mokki;

import java.util.HashMap;

public class MokkiAdditionPane extends VBox {

    private Text title = new Text("Lisää uusi mökki: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField mokkiIdTF = new TextField();
    private TextField mokkiNameTF = new TextField();
    private TextField mokkiAlueIdTF = new TextField();
    private TextField mokkiOsoiteTF = new TextField();
    private TextField mokkiPostinroTF = new TextField();
    private TextField mokkiHenkilomaaraTF = new TextField();
    private TextField mokkiHintaTF = new TextField();
    private TextArea mokkiKuvausTA = new TextArea();
    private TextArea mokkiVarusteluTA = new TextArea();

    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public MokkiAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Mokki mokki = BackendAPI.postMokki(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Mökin lisäys onnistui \n" + mokki);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
        clearButton.setOnAction(e -> {
            params.clear();

            mokkiIdTF.clear();
            mokkiNameTF.clear();
            mokkiAlueIdTF.clear();
            mokkiOsoiteTF.clear();
            mokkiPostinroTF.clear();
            mokkiHenkilomaaraTF.clear();
            mokkiHintaTF.clear();
            mokkiKuvausTA.clear();
            mokkiVarusteluTA.clear();
        });
    }

    private void generateParams() {
        params.clear();

        String mokkiId = mokkiIdTF.getText();
        String mokkiNimi = mokkiNameTF.getText();
        String mokkiAlueId = mokkiAlueIdTF.getText();
        String mokkiOsoite =  mokkiOsoiteTF.getText();
        String mokkiPostinro =  mokkiPostinroTF.getText();
        String mokkiHenkilomaara =  mokkiHenkilomaaraTF.getText();
        String mokkiHinta = mokkiHintaTF.getText();
        String mokkiKuvaus = mokkiKuvausTA.getText();
        String mokkiVarustelu = mokkiVarusteluTA.getText();

        if (mokkiId.length() > 0) {
            params.put("mokki_id", mokkiId);
        }
        if (mokkiNimi.length() > 0) {
            params.put("mokkinimi", mokkiNimi);
        }
        if (mokkiAlueId.length() > 0) {
            params.put("alue_id", mokkiAlueId);
        }
        if (mokkiOsoite.length() > 0) {
            params.put("katuosoite", mokkiOsoite);
        }
        if (mokkiPostinro.length() > 0) {
            params.put("postinro", mokkiPostinro);
        }
        if (mokkiHenkilomaara.length() > 0) {
            params.put("henkilomaara", mokkiHenkilomaara);
        }
        if (mokkiHinta.length() > 0) {
            params.put("hinta", mokkiHinta);
        }
        if (mokkiKuvaus.length() > 0) {
            params.put("kuvaus", mokkiKuvaus);
        }
        if (mokkiVarustelu.length() > 0) {
            params.put("varustelu", mokkiVarustelu);
        }
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title, mokkiIdTF, mokkiNameTF, mokkiAlueIdTF, mokkiOsoiteTF, mokkiPostinroTF, mokkiHenkilomaaraTF, mokkiHintaTF, mokkiKuvausTA, mokkiVarusteluTA, buttonPane);
    }

    private void applyStyle() {
        mokkiIdTF.setPromptText("Mökki-Id");
        mokkiNameTF.setPromptText("Mökin nimi");
        mokkiAlueIdTF.setPromptText("Alue-Id");
        mokkiOsoiteTF.setPromptText("Mökin osoite");
        mokkiPostinroTF.setPromptText("Postinumero");
        mokkiHenkilomaaraTF.setPromptText("Henkilömäärä");
        mokkiHintaTF.setPromptText("Hinta");
        mokkiKuvausTA.setPromptText("Kuvaus");
        mokkiVarusteluTA.setPromptText("Varustelu");

        mokkiKuvausTA.setPrefColumnCount(5);
        mokkiKuvausTA.setPrefRowCount(3);

        mokkiVarusteluTA.setPrefColumnCount(5);
        mokkiVarusteluTA.setPrefRowCount(3);

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

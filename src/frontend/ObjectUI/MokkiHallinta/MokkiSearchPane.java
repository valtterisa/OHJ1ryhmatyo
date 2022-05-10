package src.frontend.ObjectUI.MokkiHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Mokki;

import java.util.ArrayList;
import java.util.HashMap;

public class MokkiSearchPane extends VBox {

    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private MokkiTable table;

    private TextField mokkiIdTF = new TextField();
    private TextField mokkiNameTF = new TextField();
    private TextField mokkiAlueIdTF = new TextField();
    private TextField mokkiOsoiteTF = new TextField();
    private TextField mokkiPostinroTF = new TextField();
    // private TextField mokkiPostitoimipaikkaTF = new TextField();
    private TextField mokkiHenkilomaaraTF = new TextField();
    private TextField mokkiHintaTF = new TextField();

    private HBox buttonPane = new HBox();

    public MokkiSearchPane (Button searchButton, Button clearButton, MokkiTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);
        this.getChildren().addAll(title, mokkiIdTF, mokkiNameTF, mokkiAlueIdTF, mokkiOsoiteTF, mokkiPostinroTF, mokkiHenkilomaaraTF, mokkiHintaTF, buttonPane);
    }

    private void generateActions() {
        searchButton.setOnAction(e -> {
            generateParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Mokki> mokit = BackendAPI.getMokki(params);
            System.out.println(mokit);
            for (Mokki x : mokit) {
                table.getItems().add(x);
            }
            table.refresh();
        });
        clearButton.setOnAction(e -> {
            this.params.clear();
            mokkiIdTF.clear();
            mokkiNameTF.clear();
            mokkiAlueIdTF.clear();
            mokkiOsoiteTF.clear();
            mokkiPostinroTF.clear();
            //mokkiPostitoimipaikkaTF.clear();
            mokkiHenkilomaaraTF.clear();
            mokkiHintaTF.clear();
        });
    }

    private void generateParams() {
        this.params.clear();
        String mokkiId = mokkiIdTF.getText();
        String mokkiNimi = mokkiNameTF.getText();
        String mokkiAlueId = mokkiAlueIdTF.getText();
        String mokkiOsoite =  mokkiOsoiteTF.getText();
        String mokkiPostinro =  mokkiPostinroTF.getText();
        // String mokkiPostitoimipaikka =  mokkiPostitoimipaikkaTF.getText();
        String mokkiHenkilomaara =  mokkiHenkilomaaraTF.getText();
        String mokkiHinta = mokkiHintaTF.getText();

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
        /*if (mokkiPostitoimipaikka.length() > 0) {
            params.put("postinro", "");
        }*/
        if (mokkiHenkilomaara.length() > 0) {
            params.put("henkilomaara", mokkiHenkilomaara);
        }
        if (mokkiHinta.length() > 0) {
            params.put("hinta", mokkiHinta);
        }
    }

    private void applyStyle() {
        mokkiIdTF.setPromptText("Mökki-Id");
        mokkiNameTF.setPromptText("Mökin nimi");
        mokkiAlueIdTF.setPromptText("Alue-Id");
        mokkiOsoiteTF.setPromptText("Mökin osoite");
        mokkiPostinroTF.setPromptText("Postinumero");
        //mokkiPostitoimipaikkaTF.setPromptText("Postitoimipaikka");
        mokkiHenkilomaaraTF.setPromptText("Henkilömäärä");
        mokkiHintaTF.setPromptText("Hinta");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

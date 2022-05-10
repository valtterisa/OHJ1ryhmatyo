package src.frontend.ObjectUI.MokkiHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Mokki;

import java.util.ArrayList;
import java.util.HashMap;

public class MokkiEditNakyma extends VBox {

    private HashMap<String, String> params = new HashMap<>();

    private Text title = new Text();

    private Button saveButton;
    private Button cancelButton;

    private Mokki vanhaMokki;

    //private TextField mokkiIdTF;
    private TextField mokkiNameTF;
    private TextField mokkiAlueIdTF;
    private TextField mokkiOsoiteTF;
    private TextField mokkiPostinroTF;
    private TextField mokkiHenkilomaaraTF;
    private TextField mokkiHintaTF;
    private TextArea mokkiKuvausTA;
    private TextArea mokkiVarusteluTA;

    private HBox buttonPane = new HBox();

    private Stage stage;
    private MokkiHallintaNakyma parent;

    public MokkiEditNakyma(Mokki mokki, Stage stage, MokkiHallintaNakyma parent) {

        this.stage = stage;
        this.parent = parent;

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Muokataan mökkiä: ID = " + mokki.getMokki_id());

        this.vanhaMokki = mokki;

        //this.mokkiIdTF = new TextField(mokki.getMokki_id());
        this.mokkiNameTF = new TextField(mokki.getMokkinimi());
        this.mokkiAlueIdTF = new TextField(mokki.getAlue_id() + "");
        this.mokkiOsoiteTF = new TextField(mokki.getKatuosoite());
        this.mokkiPostinroTF = new TextField(mokki.getPostinro());
        this.mokkiHenkilomaaraTF = new TextField(mokki.getHenkilomaara() + "");
        this.mokkiHintaTF = new TextField(mokki.getHinta() + "");
        this.mokkiKuvausTA = new TextArea(mokki.getKuvaus());
        this.mokkiVarusteluTA = new TextArea(mokki.getVarustelu());

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        saveButton.setOnAction(e -> {
            generateParameters();

            Mokki uusiMokki = BackendAPI.updateMokki(params, vanhaMokki.getMokki_id() + "");
            System.out.println("PÄIVITETTY!!! : " + uusiMokki);

            parent.getTable().getItems().removeAll(parent.getTable().getItems());

            HashMap<String, String> newParams = new HashMap<>();
            newParams.put("mokki_id", this.vanhaMokki.getMokki_id() + "");

            ArrayList<Mokki> mokit = BackendAPI.getMokki(newParams);
            System.out.println(mokit);
            for (Mokki x : mokit) {
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

        String mokkiNimi = mokkiNameTF.getText();
        String mokkiAlueId = mokkiAlueIdTF.getText();
        String mokkiOsoite =  mokkiOsoiteTF.getText();
        String mokkiPostinro =  mokkiPostinroTF.getText();
        String mokkiHenkilomaara =  mokkiHenkilomaaraTF.getText();
        String mokkiHinta = mokkiHintaTF.getText();
        String mokkiKuvaus = mokkiKuvausTA.getText();
        String mokkiVarustelu = mokkiVarusteluTA.getText();

        this.params.put("mokkinimi", mokkiNimi);
        this.params.put("alue_id", mokkiAlueId);
        this.params.put("katuosoite", mokkiOsoite);
        this.params.put("postinro", mokkiPostinro);
        this.params.put("henkilomaara", mokkiHenkilomaara);
        this.params.put("hinta", mokkiHinta);
        this.params.put("kuvaus", mokkiKuvaus);
        this.params.put("varustelu", mokkiVarustelu);
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(title, mokkiNameTF, mokkiAlueIdTF, mokkiOsoiteTF, mokkiPostinroTF, mokkiHenkilomaaraTF, mokkiHintaTF, mokkiKuvausTA, mokkiVarusteluTA, buttonPane);
    }

    private void applyStyle() {
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

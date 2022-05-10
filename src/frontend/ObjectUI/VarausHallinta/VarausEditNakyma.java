package src.frontend.ObjectUI.VarausHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class VarausEditNakyma extends VBox {

    private HashMap<String, String> params = new HashMap<>();

    private Text title = new Text();

    private Button saveButton;
    private Button cancelButton;

    private Varaus vanhaVaraus;

    private TextField mokkiIdTF = new TextField();

    private TextField asiakasIdTF = new TextField();

    private TextField varausIdTF = new TextField();
    private TextField varattuTF = new TextField();
    private TextField vahvistettuTF = new TextField();
    private TextField alkuPvmTF = new TextField();
    private TextField loppuPvmTF = new TextField();

    private HBox buttonPane = new HBox();

    private Stage stage;
    private VarausHallintaNakyma parent;

    public VarausEditNakyma(Varaus varaus, Stage stage, VarausHallintaNakyma parent) {
        this.stage = stage;
        this.parent = parent;

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        this.title = new Text("Muokataan varausta: ID = " + varaus.getVaraus_id());

        this.vanhaVaraus = varaus;

        //this.mokkiIdTF = new TextField(mokki.getMokki_id());
        this.mokkiIdTF = new TextField(varaus.getMokki_mokki_id());
        this.asiakasIdTF = new TextField(varaus.getAsiakas_id());
        this.varausIdTF = new TextField(varaus.getVaraus_id() + "");
        this.varattuTF = new TextField(varaus.getVarattu_pvm());
        this.vahvistettuTF = new TextField(varaus.getVahvistus_pvm());
        this.alkuPvmTF = new TextField(varaus.getVarattu_alkupvm());
        this.loppuPvmTF = new TextField(varaus.getVarattu_loppupvm());

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        saveButton.setOnAction(e -> {
            generateParameters();

            Varaus uusiVaraus = BackendAPI.updateVaraus(params, vanhaVaraus.getVaraus_id() + "");
            System.out.println("PÄIVITETTY!!! : " + uusiVaraus);

            parent.getTable().getItems().removeAll(parent.getTable().getItems());

            HashMap<String, String> newParams = new HashMap<>();
            newParams.put("varaus_id", this.vanhaVaraus.getVaraus_id() + "");

            ArrayList<Varaus> varaukset = BackendAPI.getVaraus(newParams);
            System.out.println(varaukset);
            for (Varaus x : varaukset) {
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

        String varausId = varausIdTF.getText();
        String mokkiId = mokkiIdTF.getText();
        String asiakasId =  asiakasIdTF.getText();
        String varattuPvm =  varattuTF.getText();
        String vahvistettuPvm =  vahvistettuTF.getText();
        String alkuPvm = alkuPvmTF.getText();
        String loppuPvm = loppuPvmTF.getText();

        this.params.put("varaus_id", varausId);
        this.params.put("mokki_mokki_id", mokkiId);
        this.params.put("asiakas_id", asiakasId);
        this.params.put("varattu_pvm", varattuPvm);
        this.params.put("vahvistus_pvm", vahvistettuPvm);
        this.params.put("varattu_alkupvm", alkuPvm);
        this.params.put("varattu_loppupvm", loppuPvm);
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(saveButton, cancelButton);
        this.getChildren().addAll(title, varausIdTF, mokkiIdTF, asiakasIdTF, varattuTF, vahvistettuTF, alkuPvmTF, loppuPvmTF, buttonPane);
    }

    private void applyStyle() {
        varausIdTF.setPromptText("Varaus-Id");
        mokkiIdTF.setPromptText("Mokki-Id");
        asiakasIdTF.setPromptText("Asiakas-Id");
        varattuTF.setPromptText("Varattu Pvm");
        vahvistettuTF.setPromptText("Vahvistettu Pvm");
        alkuPvmTF.setPromptText("Alkaa Pvm");
        loppuPvmTF.setPromptText("Loppuu Pvm");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}


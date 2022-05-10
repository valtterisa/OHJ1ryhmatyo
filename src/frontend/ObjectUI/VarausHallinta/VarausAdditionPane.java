package src.frontend.ObjectUI.VarausHallinta;

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
import src.backend.datatypes.Varaus;

import java.util.HashMap;

public class VarausAdditionPane extends VBox {

    private Text title = new Text("Lisää uusi varaus: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField VarausIdTF = new TextField();
    private TextField asiakasIdTF = new TextField();
    private TextField mokkiIdTF = new TextField();
    private TextField varattuPvmTF = new TextField();
    private TextField vahvistettuPvmTF = new TextField();
    private TextField alkuPvmTF = new TextField();
    private TextField loppuPvmTF = new TextField();

    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public VarausAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Varaus varaus = BackendAPI.postVaraus(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Varauksen lisäys onnistui \n" + varaus);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
    }

    private void generateParams() {
        params.clear();

        String varausId = VarausIdTF.getText();
        String asiakasId = asiakasIdTF.getText();
        String mokkiId = mokkiIdTF.getText();
        String varattuPvm =  varattuPvmTF.getText();
        String vahvistettuPvm =  vahvistettuPvmTF.getText();
        String alkuPvm =  alkuPvmTF.getText();
        String loppuPvm = loppuPvmTF.getText();

        if (varausId.length() > 0) {
            params.put("varaus_id", varausId);
        }
        if (asiakasId.length() > 0) {
            params.put("asiakas_id", asiakasId);
        }
        if (mokkiId.length() > 0) {
            params.put("mokki_mokki_id", mokkiId);
        }
        if (varattuPvm.length() > 0) {
            params.put("varattu_pvm", varattuPvm);
        }
        if (vahvistettuPvm.length() > 0) {
            params.put("vahvistettu_pvm", vahvistettuPvm);
        }
        if (alkuPvm.length() > 0) {
            params.put("varattu_alkupvm", alkuPvm);
        }
        if (loppuPvm.length() > 0) {
            params.put("varattu_loppupvm", loppuPvm);
        }
    }

    private void construct() {
        this.buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title, VarausIdTF, asiakasIdTF, mokkiIdTF, varattuPvmTF, vahvistettuPvmTF, alkuPvmTF, loppuPvmTF, buttonPane);
    }

    private void applyStyle() {
        VarausIdTF.setPromptText("Varaus-Id");
        asiakasIdTF.setPromptText("Asiakas-Id");
        mokkiIdTF.setPromptText("Mokki-Id");
        varattuPvmTF.setPromptText("Varattu Pvm");
        vahvistettuPvmTF.setPromptText("Vahvistettu Pvm");
        alkuPvmTF.setPromptText("alku Pvm");
        loppuPvmTF.setPromptText("Loppu Pvm");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

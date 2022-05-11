package src.frontend.ObjectUI.LaskuHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LaskuAdditionPane extends VBox {

    private Text title = new Text("Lisää uusi lasku: ");

    HashMap<String, String> params = new HashMap<>();

    private TextField laskuIdTF = new TextField();
    private TextField varausIdTF = new TextField();

    private Button addButton;
    private Button clearButton;

    private HBox buttonPane = new HBox();

    public LaskuAdditionPane(Button addButton, Button clearButton) {
        this.addButton = addButton;
        this.clearButton = clearButton;

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {
        addButton.setOnAction(e -> {
            generateParams();
            Lasku lasku = BackendAPI.postLasku(params);
            Stage lisattyStage = new Stage();
            Text lisaysviesti = new Text("Laskun lisäys onnistui \n" + lasku);

            lisattyStage.setScene(new Scene(new Pane(lisaysviesti)));
            lisattyStage.show();
        });
        clearButton.setOnAction(e -> {
            params.clear();
            varausIdTF.clear();
            laskuIdTF.clear();

        });
    }

    private void generateParams() {
        params.clear();

        String laskuId = laskuIdTF.getText();
        String varausId = varausIdTF.getText();

        if (laskuId.length() > 0) {
            params.put("lasku_id", laskuId);
        }
        if (varausId.length() > 0) {
            params.put("varaus_id", varausId);
        }

        double summa = 0.00;
        double alv = 0.00;

        HashMap<String, String> varausParams = new HashMap<>();
        varausParams.put("varaus_id", varausId);
        Varaus varaus = BackendAPI.getVaraus(varausParams).get(0);
        for (VarauksenPalvelu x : varaus.getPalvelut()) {
            summa += Double.parseDouble(x.getLkm()) * Double.parseDouble(x.getPalvelu().getHinta());
            alv += Double.parseDouble(x.getLkm()) * Double.parseDouble(x.getPalvelu().getAlv());
        }
        summa += Double.parseDouble(varaus.getMokki().getHinta());

        params.put("summa", summa + "");
        params.put("alv", alv + "");
    }

    private void construct() {
        buttonPane.getChildren().addAll(addButton, clearButton);
        this.getChildren().addAll(title,laskuIdTF, varausIdTF, buttonPane);
    }

    private void applyStyle() {
        varausIdTF.setPromptText("Varaus-Id");
        laskuIdTF.setPromptText("Lasku-Id");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}


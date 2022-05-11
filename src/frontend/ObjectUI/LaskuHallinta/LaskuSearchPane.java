package src.frontend.ObjectUI.LaskuHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Lasku;

import java.util.ArrayList;
import java.util.HashMap;

public class LaskuSearchPane extends VBox {

    private Text title = new Text("Rajaa hakua: ");

    private HashMap<String, String> params = new HashMap<>();

    private Button searchButton;
    private Button clearButton;

    private LaskuTable table;

    private TextField laskuIdTF = new TextField();
    private TextField varausIdTF = new TextField();
    private TextField summaTF = new TextField();
    private TextField alvTF = new TextField();

    private HBox buttonPane = new HBox();

    public LaskuSearchPane(Button searchButton, Button clearButton, LaskuTable table) {
        this.searchButton = searchButton;
        this.clearButton = clearButton;

        this.table = table;

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        buttonPane.getChildren().addAll(searchButton, clearButton);
        this.getChildren().addAll(title, laskuIdTF, varausIdTF, summaTF, alvTF, buttonPane);
    }

    private void generateActions() {
        searchButton.setOnAction(e -> {
            generateParams();

            table.getItems().removeAll(table.getItems());
            table.getItems().clear();

            ArrayList<Lasku> laskut = BackendAPI.getLasku(params);
            System.out.println(laskut);
            for (Lasku x : laskut) {
                table.getItems().add(x);
            }
            table.refresh();
        });
        clearButton.setOnAction(e -> {
            this.params.clear();
            laskuIdTF.clear();
            varausIdTF.clear();
            summaTF.clear();
            alvTF.clear();
        });
    }

    private void generateParams() {
        this.params.clear();
        String laskuid = laskuIdTF.getText();
        String varausid = varausIdTF.getText();
        String summa = summaTF.getText();
        String alv =  alvTF.getText();

        if (laskuid.length() > 0) {
            params.put("lasku_id", laskuid);
        }
        if (varausid.length() > 0) {
            params.put("varaus_id", varausid);
        }
        if (summa.length() > 0) {
            params.put("summa", summa);
        }
        if (alv.length() > 0) {
            params.put("alv", alv);
        }
    }


    private void applyStyle() {
        laskuIdTF.setPromptText("Lasku-Id");
        varausIdTF.setPromptText("Varaus-Id");
        summaTF.setPromptText("Summa");
        alvTF.setPromptText("Alv");

        this.setPadding(new Insets(10));
        this.setSpacing(10);

        this.buttonPane.setSpacing(30);
    }
}

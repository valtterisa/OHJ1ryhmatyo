package src.frontend.ObjectUI.VarausHallinta.VarausPalveluHallinta;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.VarauksenPalvelu;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.VarausHallinta.VarausHallintaNakyma;

import java.util.ArrayList;
import java.util.HashMap;

public class VarausEditPalveluNakyma extends BorderPane {

    private HashMap<String, String> params = new HashMap<>();
    private HashMap<String, String> editParams = new HashMap<>();

    private Varaus selectedVaraus;

    private VarausPalveluTable table = new VarausPalveluTable();

    private TextField palveluidTF = new TextField();
    private TextField palveluLkmTF = new TextField();

    private Button addButton;
    private Button deleteButton;
    private Button editButton;

    private Button saveButton;
    private Button cancelButton;

    private TextField editPalveluIdTF = new TextField();
    private TextField editPalveluLkmTF = new TextField();

    private HBox buttonBox = new HBox();

    public VarausEditPalveluNakyma(Varaus varaus, Stage stage, VarausHallintaNakyma parent) {
        this.selectedVaraus = varaus;
        this.params.put("varaus_id", this.selectedVaraus.getVaraus_id() + "");

        this.addButton = new Button("Lisää varaukselle uusi palvelu");
        this.deleteButton = new Button("Poista valittu");
        this.editButton = new Button("Muokkaa valittua");

        this.saveButton = new Button("Tallenna");
        this.cancelButton = new Button("Peruuta");

        generateActions();
        construct();
        applyStyle();
    }

    private void generateActions() {

        addButton.setOnAction(e -> {
            HashMap<String, String> uusiParams = new HashMap<>();
            uusiParams.put("varaus_id", this.selectedVaraus.getVaraus_id() + "");
            uusiParams.put("palvelu_id", palveluidTF.getText());
            uusiParams.put("lkm", palveluLkmTF.getText());
            VarauksenPalvelu uusiPalvelu = BackendAPI.postVarauksenPalvelu(uusiParams);
            this.table.getItems().clear();
            construct();
        });
        deleteButton.setOnAction(e -> {
            HashMap<String, String> deleteParams = new HashMap<>();
            deleteParams.put("varaus_id", this.selectedVaraus.getVaraus_id() + "");
            deleteParams.put("palvelu_id", this.table.getSelectedItem().getPalvelu_id());
            String deleteMSG = BackendAPI.deleteVarauksenPalvelu(deleteParams);
            this.table.getItems().clear();
            construct();
        });
        editButton.setOnAction(e -> {

            editPalveluIdTF = new TextField(this.table.getSelectedItem().getPalvelu_id());
            editPalveluLkmTF = new TextField(this.table.getSelectedItem().getLkm());

            editParams.put("varaus_id", this.selectedVaraus.getVaraus_id() + "");
            editParams.put("palvelu_id", editPalveluIdTF.getText());
            editParams.put("lkm", editPalveluLkmTF.getText());

            Text info = new Text("Anna uusi lkm (" + table.getSelectedItem().getPalvelu().getNimi() + ")" );

            VBox editBox = new VBox(info, editPalveluLkmTF, saveButton, cancelButton);
            this.setRight(editBox);
        });
        saveButton.setOnAction(e -> {
            editParams.put("varaus_id", this.selectedVaraus.getVaraus_id() + "");
            editParams.put("palvelu_id", editPalveluIdTF.getText());
            editParams.put("lkm", editPalveluLkmTF.getText());

            VarauksenPalvelu muokattuPalvelu = BackendAPI.updateVarauksenPalvelu(editParams, this.selectedVaraus.getVaraus_id()+"", this.table.getSelectedItem().getPalvelu_id());
            this.table.getItems().clear();
            this.setRight(new Pane());
            editParams.clear();
            construct();
        });
        cancelButton.setOnAction(e -> {
            this.table.getItems().clear();
            this.setRight(new Pane());
            editParams.clear();
            construct();
        });
    }

    public void construct() {
        ArrayList<VarauksenPalvelu> palvelut = BackendAPI.getVarauksenPalvelu(params);
        for (VarauksenPalvelu x : palvelut) {
            table.getItems().add(x);
        }

        this.buttonBox.getChildren().addAll(palveluidTF, palveluLkmTF, addButton);
        this.buttonBox.getChildren().addAll(deleteButton, editButton);

        this.setCenter(table);
        this.setBottom(buttonBox);
    }

    private void applyStyle() {
        this.palveluidTF.setPromptText("Palvelu-Id");
        this.palveluLkmTF.setPromptText("Lkm");
    }
}

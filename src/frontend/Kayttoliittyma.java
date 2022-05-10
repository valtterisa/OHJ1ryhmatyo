package src.frontend;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Varaus;

import java.util.ArrayList;
import java.util.HashMap;

public class Kayttoliittyma extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane pane = new BorderPane();
        VBox searchPane = new VBox();
        searchPane.setMinWidth(200);

        VBox inspectingPane = new VBox();

        // HAKUPARAMETRIT
        HashMap<String, String> mokki_params = new HashMap<>();
        HashMap<String, String> varaus_params = new HashMap<>();
        HashMap<String, String> asiakas_params = new HashMap<>();

        // HAKUKENTÄT
        TextField mokkiIdTF = new TextField();
        TextField mokkiNimiTF = new TextField();
        TextField asiakasEtunimiTF = new TextField();
        TextField asiakasSukunimiF = new TextField();
        TextField asiakasPuhelinnroTF = new TextField();
        TextField asiakasEmailTF = new TextField();

        mokkiIdTF.setPromptText("Mökin id");
        mokkiNimiTF.setPromptText("Mökin nimi");
        asiakasEtunimiTF.setPromptText("Asiakkaan etunimi");
        asiakasSukunimiF.setPromptText("Asiakkaan sukunimi");
        asiakasPuhelinnroTF.setPromptText("Asiakkaan puh");
        asiakasEmailTF.setPromptText("Asiakkaan email");

        searchPane.getChildren().add(mokkiIdTF);
        searchPane.getChildren().add(mokkiNimiTF);
        searchPane.getChildren().add(asiakasEtunimiTF);
        searchPane.getChildren().add(asiakasSukunimiF);
        searchPane.getChildren().add(asiakasPuhelinnroTF);
        searchPane.getChildren().add(asiakasEmailTF);

        // NAPIT
        HBox searchButtonPane = new HBox();
        Button searchButton = new Button("Hae");
        searchButtonPane.getChildren().add(searchButton);

        Button clearButton = new Button("Tyhjennä");
        searchButtonPane.getChildren().add(clearButton);

        searchPane.getChildren().add(searchButtonPane);

        // TAULUKKO
        TableView<Varaus> table = new TableView<>();

        TableColumn<Varaus, String> col1 = new TableColumn<>("mökin id");
        TableColumn<Varaus, String> col2 = new TableColumn<>("mökin nimi");
        TableColumn<Varaus, String> col3 = new TableColumn<>("etunimi");
        TableColumn<Varaus, String> col4 = new TableColumn<>("sukunimi");
        TableColumn<Varaus, String> col5 = new TableColumn<>("puhelinnro");
        TableColumn<Varaus, String> col6 = new TableColumn<>("sähköposti");

        col1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMokki_mokki_id()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMokki().getMokkinimi()));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEtunimi()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getSukunimi()));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getPuhelinnro()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEmail()));


        table.getColumns().add(col1);
        table.getColumns().add(col2);
        table.getColumns().add(col3);
        table.getColumns().add(col4);
        table.getColumns().add(col5);
        table.getColumns().add(col6);


        // BUTTON ACTIONS
        clearButton.setOnMouseClicked(e -> {
            mokki_params.clear();
            asiakas_params.clear();
            varaus_params.clear();

            mokkiIdTF.clear();
            mokkiNimiTF.clear();
            asiakasEtunimiTF.clear();
            asiakasSukunimiF.clear();
            asiakasPuhelinnroTF.clear();
            asiakasEmailTF.clear();
        });

        searchButton.setOnMouseClicked(e -> {
            mokki_params.clear();
            asiakas_params.clear();
            varaus_params.clear();

            String mokkiId = mokkiIdTF.getText();
            String mokkiNimi = mokkiNimiTF.getText();
            String etunimi = asiakasEtunimiTF.getText();
            String sukunimi = asiakasSukunimiF.getText();
            String puhelinnro = asiakasPuhelinnroTF.getText();
            String email = asiakasEmailTF.getText();

            if (mokkiId.length() > 0) {
                mokki_params.put("mokki_id", mokkiId);
            }
            if (mokkiNimi.length() > 0) {
                mokki_params.put("mokkinimi", mokkiNimi);
            }
            if (etunimi.length() > 0) {
                asiakas_params.put("etunimi", etunimi);
            }
            if (sukunimi.length() > 0) {
                asiakas_params.put("sukunimi", sukunimi);
            }
            if (puhelinnro.length() > 0) {
                asiakas_params.put("puhelinnro", puhelinnro);
            }
            if (email.length() > 0) {
                asiakas_params.put("email", email);
            }

            System.out.println(asiakas_params);

            table.getItems().clear();

            ArrayList<Varaus> varaukset = BackendAPI.getVarausTiedoilla(varaus_params, mokki_params, asiakas_params);
            System.out.println(varaukset);
            for (Varaus x : varaukset) {
                table.getItems().add(x);

            }
            table.refresh();
        });

        // TABLE ACTIONS
        table.setOnMouseClicked(e -> {
            Varaus selectedVaraus = table.getSelectionModel().getSelectedItem();
            TextArea varausInfo = new TextArea("ID: " + selectedVaraus.getVaraus_id());
            inspectingPane.getChildren().add(varausInfo);
        });

        pane.setLeft(searchPane);
        pane.setCenter(table);
        pane.setRight(inspectingPane);


        Scene startScene = new Scene(pane);
        stage.setScene(startScene);
        stage.show();
    }
}

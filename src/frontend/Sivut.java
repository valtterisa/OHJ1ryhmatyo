package src.frontend;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Mokki;
import src.frontend.ObjectUI.YleisNakyma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class Sivut extends Application {
    private Stage STAGE;
    /**
     * Ensimmäinen ikkuna.
     */
    private Scene SCENE1;
    /**
     * Toinen ikkuna.
     */
    private Scene SCENE2;
    /**
     * Kolmas ikkuna.
     */
    private Scene SCENE3;
    /**
     * Neljäs ikkuna.
     */
    private Scene SCENE4;

    /**
     * Metodi liittää neljä ikkunaa niille kuuluviin metodeihin sekä määrittelee käynnistyksen.
     */

    HashMap<String, String> asiakas_params = new HashMap<>();

    public void start(Stage paaIkkuna)  {
        STAGE = paaIkkuna;
        SCENE1 = EnsimmainenSivu();
        SCENE2 = ToinenSivu();
        SCENE3 = KolmasSivu();
        SCENE4 = NeljasSivu(paaIkkuna);

        paaIkkuna.setTitle("Mökkien varausjärjestelmä");
        paaIkkuna.setScene(SCENE4);
        // paaIkkuna.setScene(SCENE3);
        paaIkkuna.show();
    }
    private Scene EnsimmainenSivu() {

        DatePicker checkInDatePicker = new DatePicker();
        DatePicker checkOutDatePicker = new DatePicker();

        final String pattern = "YYYY-MM-dd";

        // varauksen aikaformaatin muuttaminen
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };             
        checkInDatePicker.setConverter(converter);
        checkInDatePicker.setPromptText(pattern.toLowerCase());
        checkOutDatePicker.setConverter(converter);
        checkOutDatePicker.setPromptText(pattern.toLowerCase());
        
        // varauksen ajankohdan valinta
        checkInDatePicker.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(
                                    checkInDatePicker.getValue().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: lightgrey;");
                            }
                    }
                };
            }
        };
        checkOutDatePicker.setDayCellFactory(dayCellFactory);
        checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));

        HBox paneeli = new HBox(5);  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli.setAlignment(Pos.TOP_CENTER);
        paneeli.setPadding(new Insets(5,0,0,0));

        /*
        Label tervetuloa = new Label("Mokin varausjärjestelmä");     //Otsikko ja sen määrittely.
        tervetuloa.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        paneeli.getChildren().add(tervetuloa);
        */

        ComboBox<String> location = new ComboBox<String>();
        location.setPromptText("Valitse mökin sijainti");
        
        // lisätään olemassa olevat paikkakunnat
        location.getItems().addAll("Kuusamo",
                                    "Nulla",
                                    "ante",
                                    "aliquam",
                                    "Cras",
                                    "eu",
                                    "Integer",
                                    "vitae",
                                    "dignissim",
                                    "Vivamus",
                                    "blandit",
                                    "Duis",
                                    "ligula",
                                    "Integer",
                                    "nulla",
                                    "Donec",
                                    "eu",
                                    "malesuada",
                                    "Donec",
                                    "Molestie"
        );

        // TODO
        // paikkakuntien haku comboboksiin (näkymä joka hakee kaikki paikkakunnat -> paikkakunnat listaan)
        // tieto hashmappiin ja haku kannasta tietojen täsmätessä
        // metodeihin pilkkominen

        TableView<Mokki> vapaatMokit = new TableView<Mokki>();

        TableColumn<Mokki, String> otsikko1 = new TableColumn<>("Mökki_id");
        otsikko1.setCellValueFactory(new PropertyValueFactory<>(""));
        otsikko1.setPrefWidth(30);

        TableColumn<Mokki, String> otsikko2 = new TableColumn<>("Alue-id");
        otsikko2.setCellValueFactory(new PropertyValueFactory<>("pelaajanNimi"));
        otsikko2.setPrefWidth(120);

        TableColumn<Mokki, String> otsikko3 = new TableColumn<>("Postinumero");
        otsikko3.setCellValueFactory(new PropertyValueFactory<>("pelaajanKansallisuus"));
        otsikko3.setPrefWidth(110);

        TableColumn<Mokki, String> otsikko4 = new TableColumn<>("Mökin nimi");
        otsikko4.setCellValueFactory(new PropertyValueFactory<>("pelaajanElo"));
        otsikko4.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko5 = new TableColumn<>("Hinta");
        otsikko5.setCellValueFactory(new PropertyValueFactory<>("pelaajanIka"));
        otsikko5.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko6 = new TableColumn<>("Kuvaus");
        otsikko5.setCellValueFactory(new PropertyValueFactory<>("pelaajanIka"));
        otsikko5.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko7 = new TableColumn<>("Henkilömäärä");
        otsikko5.setCellValueFactory(new PropertyValueFactory<>("pelaajanIka"));
        otsikko5.setPrefWidth(100);

        // lisätään luodut columnit/otsikot rankingLista-tableen
        vapaatMokit.getColumns().add(otsikko1);
        vapaatMokit.getColumns().add(otsikko2);
        vapaatMokit.getColumns().add(otsikko3);
        vapaatMokit.getColumns().add(otsikko4);
        vapaatMokit.getColumns().add(otsikko5);
        vapaatMokit.getColumns().add(otsikko6);
        vapaatMokit.getColumns().add(otsikko7);

        paneeli.getChildren().add(location);

        // check-in
        Label from = new Label("Mistä");
        from.setPadding(new Insets(5,0,0,0));
        paneeli.getChildren().addAll(from,checkInDatePicker);

        // check-out
        Label to = new Label("Mihin");
        to.setPadding(new Insets(5,0,0,0));
        paneeli.getChildren().addAll(to,checkOutDatePicker);

        Button searchButton = new Button("Haku");
        paneeli.getChildren().add(searchButton);

        searchButton.setOnAction(e -> {
            System.out.println("check-in: " + checkInDatePicker.getValue());
            System.out.println("check-out: " + checkOutDatePicker.getValue());
            System.out.println("Sijainti: " + location.getValue());
        });

        // tableview tässä boksissa
        HBox aa = new HBox();
        aa.getChildren().add(vapaatMokit);

        // paneeli jossa yhdistetään kaksi HBoxia
        VBox root = new VBox(5);
        root.getChildren().addAll(paneeli,aa);

        SCENE1 = new Scene(root, 700,400);
        return SCENE1;
    }

    private Scene ToinenSivu() {
        GridPane paneeli2 = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli2.setMinSize(200, 200);
        paneeli2.setAlignment(Pos.TOP_CENTER);
        paneeli2.setHgap(10);
        paneeli2.setVgap(20);

        Label tervetuloa = new Label("Mokin varausjärjestelmä");     //Otsikko ja sen määrittely.
        tervetuloa.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        paneeli2.add(tervetuloa, 1,0);

        Button varaa = new Button("Varaa");
        paneeli2.add(varaa, 3, 3);

        BorderPane mokintiedot = new BorderPane();
        paneeli2.add(mokintiedot, 3, 3);

        TableView tb = new TableView<>();

        mokintiedot.setCenter(tb);

        varaa.setOnAction(e -> {

        });

        Button nappainSEURAAVA = new Button("Seuraava");
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE3));
        paneeli2.add(nappainSEURAAVA, 1,9);

        Button nappainEDELLINEN = new Button("Edellinen");
        nappainEDELLINEN.setOnAction(e -> switchScenes(SCENE1));
        paneeli2.add(nappainEDELLINEN, 0, 9);

        SCENE2 = new Scene(paneeli2, 600,400);
        return SCENE2;
    }

    private Scene KolmasSivu() {
        Pane paneeli3 = new Pane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli3.setMinSize(200, 200);


        TextField tekstikentta1  = new TextField();
        tekstikentta1.setLayoutX(105.0);
        tekstikentta1.setLayoutY(50.0);


        TextField tekstikentta2  = new TextField();
        tekstikentta2.setLayoutX(105.0);
        tekstikentta2.setLayoutY(100.0);
        tekstikentta2.setPromptText("Etunimi");

        TextField tekstikentta3  = new TextField();
        tekstikentta3.setLayoutX(105.0);
        tekstikentta3.setLayoutY(135.0);
        tekstikentta3.setPromptText("Sukunimi");

        TextField tekstikentta4  = new TextField();
        tekstikentta4.setLayoutX(105.0);
        tekstikentta4.setLayoutY(170.0);
        tekstikentta4.setPromptText("Postinumero");

        TextField tekstikentta5  = new TextField();
        tekstikentta5.setLayoutX(105.0);
        tekstikentta5.setLayoutY(205.0);
        tekstikentta5.setPromptText("Osoite");

        TextField tekstikentta6  = new TextField();
        tekstikentta6.setLayoutX(105.0);
        tekstikentta6.setLayoutY(240.0);
        tekstikentta6.setPromptText("Puhelinnumero");

        TextField tekstikentta7  = new TextField();
        tekstikentta7.setLayoutX(105.0);
        tekstikentta7.setLayoutY(275.0);
        tekstikentta7.setPromptText("Sähköposti");

        TextField tekstikenttä_hinta = new TextField();
        tekstikenttä_hinta.setLayoutX(375);
        tekstikenttä_hinta.setLayoutY(205);

        Button maksa = new Button();
        maksa.setLayoutX(420);
        maksa.setLayoutY(240);
        maksa.setText("Maksa");


        maksa.setOnAction(e-> {
            String Etunimi = tekstikentta2.getText();
            String Sukunimi = tekstikentta3.getText();
            String Postinumero = tekstikentta4.getText();
            String Osoite = tekstikentta5.getText();
            String Puhelinnumero = tekstikentta6.getText();
            String Sahkoposti = tekstikentta7.getText();

            if (Etunimi.length() > 0) {
                asiakas_params.put("etunimi", Etunimi);
            }
            if (Sukunimi.length() > 0) {
                asiakas_params.put("sukunimi", Sukunimi);
            }
            if (Postinumero.length() > 0) {
                asiakas_params.put("postinro", Postinumero);
            }
            if (Osoite.length() > 0) {
                asiakas_params.put("lahiosoite", Osoite);
            }
            if (Puhelinnumero.length() > 0) {
                asiakas_params.put("puhelinnro", Puhelinnumero);
            }
            if (Sahkoposti.length() > 0) {
                asiakas_params.put("email", Sahkoposti);
            }
        BackendAPI.postAsiakas(asiakas_params);

        });






        paneeli3.getChildren().addAll(tekstikentta1,tekstikentta2,tekstikentta6
                ,tekstikentta3,tekstikentta4,tekstikentta5,tekstikentta7,tekstikenttä_hinta,maksa);


        SCENE3 = new Scene(paneeli3, 600,400);
        return SCENE3;
    }

    private Scene NeljasSivu(Stage stage) {
        YleisNakyma paneeli4 = new YleisNakyma(stage);
        //GridPane paneeli4 = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli4.setMinSize(200, 200);
        //paneeli4.setAlignment(Pos.TOP_CENTER);
        //paneeli4.setHgap(10);
        //paneeli4.setVgap(20);

        SCENE4 = new Scene(paneeli4, 600,400);
        return SCENE4;
    }
    public void switchScenes(Scene scene) {
        STAGE.setScene(scene);
    }
    public static void main(String[] args) {
        launch();
    }
}

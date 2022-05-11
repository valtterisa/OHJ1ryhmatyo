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
import src.backend.api.AlueFunctions;
import src.backend.api.BackendAPI;
import src.backend.api.MokkiFunctions;
import src.backend.api.VarausFunctions;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.YleisNakyma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        paaIkkuna.setScene(SCENE1);
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

        TableView<Mokki> vapaatMokit = new TableView<Mokki>();

        TableColumn<Mokki, String> otsikko1 = new TableColumn<>("Mökki_id");
        otsikko1.setCellValueFactory(new PropertyValueFactory<>("mokki_id"));
        otsikko1.setPrefWidth(30);

        TableColumn<Mokki, String> otsikko2 = new TableColumn<>("Alue-id");
        otsikko2.setCellValueFactory(new PropertyValueFactory<>("alue_id"));
        otsikko2.setPrefWidth(120);

        TableColumn<Mokki, String> otsikko3 = new TableColumn<>("Postinumero");
        otsikko3.setCellValueFactory(new PropertyValueFactory<>("postinro"));
        otsikko3.setPrefWidth(110);

        TableColumn<Mokki, String> otsikko4 = new TableColumn<>("Mökin nimi");
        otsikko4.setCellValueFactory(new PropertyValueFactory<>("mokkinimi"));
        otsikko4.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko5 = new TableColumn<>("Hinta");
        otsikko5.setCellValueFactory(new PropertyValueFactory<>("hinta"));
        otsikko5.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko6 = new TableColumn<>("Kuvaus");
        otsikko6.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));
        otsikko6.setPrefWidth(100);

        TableColumn<Mokki, String> otsikko7 = new TableColumn<>("Henkilömäärä");
        otsikko7.setCellValueFactory(new PropertyValueFactory<>("henkilomaara"));
        otsikko7.setPrefWidth(100);

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

            // valitut arvot parametreiksi ja haku kannasta
            HashMap<String, String> alue = new HashMap<String, String>();
            alue.put("nimi", location.getValue());
            
            // haetaan alue ja otetaan alue_id talteen
            ArrayList<Alue> haettavaAlue = AlueFunctions.getAlue(alue);
            String haluttuId = haettavaAlue.get(0).getId();

            HashMap<String, String> mokitAlue = new HashMap<String, String>();
            mokitAlue.put("alue_id", haluttuId);

            // haetaan mökit tietyllä alued_id ja laitetaan TableViewiin
            ArrayList<Mokki> mokit = MokkiFunctions.getMokki(mokitAlue);
            
            HashMap<String, String> varauksenParam = new HashMap<String, String>();



            for (Mokki x : mokit) {
                vapaatMokit.getItems().add(x);
            }

            // checkInDatePicker = alkamispäivä ja checkOutDatePicker = päättymispäivä --> jos
            // alkupvm mokki_id varauksella sama kuin alkamispäivä -> ei näytä mökkiä
            // valittuja mokki_id ei voi olla varauksessa aikavälillä xxxx-xx-xx - xxxx-xx-xx
            // pitää hakea kaikkien varauksien mokki_mokki_id ja verrata haluttuihin mökkeihin aikavälillä
            // hakee vain sellaiset mökit jotka toteuttavat ehdon

        });

        Button nappainSEURAAVA = new Button("Seuraava");
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE2));

        // tableview tässä boksissa
        HBox tableview = new HBox();
        tableview.getChildren().add(vapaatMokit);

        // paneeli jossa yhdistetään kaksi HBoxia
        VBox paneeli1 = new VBox(5);
        paneeli1.setMinSize(700, 400);
        paneeli1.getChildren().addAll(paneeli,tableview,nappainSEURAAVA);

        SCENE1 = new Scene(paneeli1, 700,400);
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
        // TODO näille napeille välit
        Button nappainSEURAAVA = new Button("Seuraava");
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE4));
        paneeli3.getChildren().add(nappainSEURAAVA);

        Button nappainEDELLINEN = new Button("Edellinen");
        nappainEDELLINEN.setOnAction(e -> switchScenes(SCENE2));
        paneeli3.getChildren().add(nappainEDELLINEN);

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

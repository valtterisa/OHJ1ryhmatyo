package src.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import src.backend.api.AlueFunctions;
import src.backend.api.BackendAPI;
import src.backend.api.MokkiFunctions;
import src.backend.api.VarausFunctions;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Asiakas;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.MokkiHallinta.MokkiInspectPane;
import src.frontend.ObjectUI.YleisNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private Mokki valittuMokki;

    HashMap<String, String> asiakas_params = new HashMap<>();

    public void start(Stage paaIkkuna)  {

        STAGE = paaIkkuna;
        SCENE1 = EnsimmainenSivu();
        SCENE2 = ToinenSivu();
        SCENE3 = KolmasSivu();
        SCENE4 = NeljasSivu(paaIkkuna);

        Pane paneeli5 = new Pane();
        Scene aloitus = new Scene(paneeli5);
        VBox box = new VBox();
        box.setPadding(new Insets(65));
        box.setAlignment(Pos.CENTER);

        HBox napit_paneeli5 = new HBox(15);
        napit_paneeli5.setAlignment(Pos.CENTER);
        napit_paneeli5.setPadding(new Insets(15,5,5,15));
        Button sivu1 = new Button("Tee uusi varaus");
        Button sivu2 = new Button("Sivu 2 ");
        Button sivu3 = new Button("Sivu 3");
        Button sivu4 = new Button("Admin");
        napit_paneeli5.getChildren().addAll(sivu1, sivu4);
        box.getChildren().add(napit_paneeli5);
        paneeli5.getChildren().add(box);

        sivu1.setOnAction(e-> paaIkkuna.setScene(SCENE1));
        sivu2.setOnAction(e-> paaIkkuna.setScene(SCENE2));
        sivu3.setOnAction(e-> paaIkkuna.setScene(SCENE3));
        sivu4.setOnAction(e-> paaIkkuna.setScene(SCENE4));



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
        
        // varauksen alku ajankohdan valinta
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

        // varauksen loppu ajankohdan valinta
        checkOutDatePicker.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory2 = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                    }
                };
            }
        };

        checkInDatePicker.setDayCellFactory(dayCellFactory2);
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
        otsikko1.setCellValueFactory(new PropertyValueFactory<>("mokki_id"));
        otsikko1.setPrefWidth(100);

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

            // tyhjentää kolumnit haku-nappia painaessa
            vapaatMokit.getItems().clear();

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

            // kerätään haettujen mökkien ID:t
            ArrayList<String> mokkiId = new ArrayList<String>();
            for (Mokki i : mokit) {
                mokkiId.add(i.getMokki_id());
            }
            HashMap<String, String> mokkiparams = new HashMap<>();


            // haetaan mokki_id perusteella varauksia

            HashMap<String, String> varausParam = new HashMap<String, String>();
            for (String id : mokkiId) {
                varausParam.put("mokki_mokki_id", id);

            }

            ArrayList<Varaus> varauksetJotkaSopii = VarausFunctions.getVaraus(varausParam);
            
            ArrayList<String> alku_pvm = new ArrayList<String>(); 
            //pvm = {alkupvm,loppupvm,alkupvm,loppupvm...}
            for (Varaus i : varauksetJotkaSopii) {
                alku_pvm.add(i.getVarattu_alkupvm());
            }

            ArrayList<String> loppu_pvm = new ArrayList<String>(); 
            //pvm = {alkupvm,loppupvm,alkupvm,loppupvm...}
            for (Varaus i : varauksetJotkaSopii) {
                loppu_pvm.add(i.getVarattu_loppupvm());
            }
            
            for (Mokki x : mokit) {

                String alkupvm;
                String loppupvm;

                for (int i = 0; i < varauksetJotkaSopii.size(); i++) {
                    
                    // ottaa aina kaksi ensimmäistä päivämäärää ja asettaa ne muuttujiin
                    alkupvm = alku_pvm.get(i);
                    loppupvm = loppu_pvm.get(i);

                    System.out.println("alkupvm" + alkupvm);
                    System.out.println("loppupvm " + loppupvm);
                    
                    // jos alkupvm on ennen mökin varauksen loppupäivämäärää TAI loppupvm ennen mökin varauksen alkupäivämäärää
                    // lisää mökki TableViewiin
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        if (!(sdf.parse(checkInDatePicker.getValue().toString()).before(sdf.parse(loppupvm)))) {
                            if ((sdf.parse(checkOutDatePicker.getValue().toString()).after(sdf.parse(alkupvm)))) {
                                vapaatMokit.getItems().add(x);
                            } 
                        } else {
                            System.out.println("Else-lausekkeessa");
                            vapaatMokit.setPlaceholder(new Label("Mökkejä ei ole vapaana valitsemallasi ajanjaksolla")); 
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
            
            }

            // tyhjennetään listat, jotta voi hakea uusia mökkejä
            mokit.removeAll(mokit);
            loppu_pvm.removeAll(loppu_pvm);
            alku_pvm.removeAll(alku_pvm);
            varauksetJotkaSopii.removeAll(varauksetJotkaSopii);
            

        });

        // nappi SCENE:n vaihtoon
        Button nappainSEURAAVA = new Button("Seuraava");
        
        nappainSEURAAVA.setOnAction(e -> {
            valittuMokki = vapaatMokit.getSelectionModel().getSelectedItem();
            System.out.println(valittuMokki);
            SCENE2 = ToinenSivu();
            switchScenes(SCENE2);
        });

        // tableview tässä boksissa
        HBox tableview = new HBox();
        tableview.setPadding(new Insets(0,0,0,10));
        tableview.getChildren().add(vapaatMokit);

        // paneeli jossa yhdistetään kaksi HBoxia
        VBox paneeli1 = new VBox(5);
        paneeli1.setMinSize(700, 400);
        paneeli1.getChildren().addAll(paneeli,tableview,nappainSEURAAVA);

        SCENE1 = new Scene(paneeli1, 750,400);
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
        //paneeli2.add(varaa, 2, 9);

        MokkiTable table = new MokkiTable();

        if (valittuMokki != null) {
            HashMap<String, String> mokinTiedot = new HashMap<String, String>();
            mokinTiedot.put("mokki_id", valittuMokki.getMokki_id());

            ArrayList<Mokki> Mokit = BackendAPI.getMokki(mokinTiedot);
            System.out.println(Mokit);
            for (Mokki x : Mokit) {
                table.getItems().add(x);
            }
            paneeli2.add(table, 0, 0);
            paneeli2.add(new MokkiInspectPane(valittuMokki, "plain"), 1, 1);
            paneeli2.add(new Label("Tässä voisi olla kuva mökistä"), 0, 1);
        }



        varaa.setOnAction(e -> {
            switchScenes(SCENE3);
        });

        Button nappainSEURAAVA = new Button("Seuraava");
        SCENE3 = KolmasSivu();
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE3));
        paneeli2.add(nappainSEURAAVA, 1,3);

        Button nappainEDELLINEN = new Button("Edellinen");
        nappainEDELLINEN.setOnAction(e -> switchScenes(SCENE1));
        paneeli2.add(nappainEDELLINEN, 0, 3);

        SCENE2 = new Scene(paneeli2, 600,400);
        return SCENE2;
    }


    private Scene KolmasSivu() {
        HashMap<String, String> varaus_params1 = new HashMap<>();
        Pane paneeli3 = new Pane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli3.setMinSize(200, 200);

        /**
         * Luodaan tekstikentät
         */
        TextField tekstikentta1  = new TextField();
        tekstikentta1.setLayoutX(105.0);
        tekstikentta1.setLayoutY(50.0);
        try {
            tekstikentta1.setText("Sijainti: "+valittuMokki.getAlue()+ "  Nimi: "+valittuMokki.getMokkinimi());
        }
        catch(Exception e) {
            tekstikentta1.setText("Sijainti:    "+"Nimi:        ");
        }



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

        TextField tekstikentta_hinta = new TextField();
        tekstikentta_hinta.setLayoutX(375);
        tekstikentta_hinta.setLayoutY(205);
        try {
            tekstikentta_hinta.setText("Nimi: " + valittuMokki.getMokkinimi()+ " " +valittuMokki.getHinta() +
                    "Muut palvelu maksut.");
        }
        catch(Exception e) {
            tekstikentta_hinta.setText("Mökkiä ei valittu!");
        }


        /**
         * Nappien luonti
         */
        Button maksa = new Button("Maksa");
        maksa.setLayoutX(420);
        maksa.setLayoutY(240);


        Button nappainSEURAAVA3 = new Button("Seuraava");
        nappainSEURAAVA3.setOnAction(e -> switchScenes(SCENE4));

        Button nappainEDELLINEN3 = new Button("Edellinen");
        nappainEDELLINEN3.setOnAction(e -> switchScenes(SCENE2));

        nappainEDELLINEN3.setLayoutX(0);
        nappainEDELLINEN3.setLayoutY(375);

        nappainSEURAAVA3.setLayoutX(535);
        nappainSEURAAVA3.setLayoutY(375);

        /**
         * Maksa napin toiminnalisuus.
         */
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
            try {
                Asiakas uusiAsiakas = BackendAPI.postAsiakas(asiakas_params);
                varaus_params1.put("asiakas_id", uusiAsiakas.getAsiakas_id());
                varaus_params1.put("mokki_mokki_id", valittuMokki.getMokki_id());
                BackendAPI.postVaraus(varaus_params1);
            }
            catch(Exception b) {
                System.out.println("Jotain meni pieleen" +
                        "Tarkista postinumeron oikeellisuus ja muut tiedot");
            }



        });




        paneeli3.getChildren().addAll(tekstikentta1,tekstikentta2,tekstikentta6
                ,tekstikentta3,tekstikentta4,tekstikentta5,tekstikentta7,tekstikentta_hinta,maksa);
        paneeli3.getChildren().addAll(nappainEDELLINEN3,nappainSEURAAVA3);
        /**
         * Lisätään objektit Pane "paneeli3".
         */


        SCENE3 = new Scene(paneeli3, 600,400);
        return SCENE3;
        /**
         * Palauttaa ikkuna 3:sen
         * @return
         */
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

package src.frontend;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import src.frontend.ObjectUI.YleisNakyma;


public class Sivut extends Application {
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
     * Metodi liittää kolme ikkunaa niille kuuluviin metodeihin sekä määrittelee käynnistyksen.
     */
    public void start(Stage paaIkkuna)  {

        SCENE1 = EnsimmainenSivu();
        SCENE2 = ToinenSivu();
        SCENE3 = KolmasSivu();
        SCENE4 = NeljasSivu(paaIkkuna);

        paaIkkuna.setTitle("Mökkien varausjärjestelmä");
        paaIkkuna.setScene(SCENE4);
        paaIkkuna.show();
    }
    private Scene EnsimmainenSivu() {
        GridPane paneeli = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli.setMinSize(200, 200);
        paneeli.setAlignment(Pos.TOP_CENTER);
        paneeli.setHgap(10);
        paneeli.setVgap(20);

        Label tervetuloa = new Label("Mokin varausjärjestelmä");     //Otsikko ja sen määrittely.
        tervetuloa.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        paneeli.add(tervetuloa, 1,0);

        Label logo = new Label("logo");
        paneeli.add(logo,0,0);

        TextField teksti1 = new TextField();
        paneeli.add(teksti1,0,1);
        teksti1.setPrefWidth(10);


        TextField teksti2 = new TextField();
        paneeli.add(teksti2,1,1);

        TextField teksti3 = new TextField();
        paneeli.add(teksti3,2,1);

        Button teksti4 = new Button("Haku");
        paneeli.add(teksti4,3,1);

        GridPane aa = new GridPane();
        GridPane.setHalignment(aa, HPos.CENTER);
        GridPane.setValignment(aa, VPos.CENTER);
        aa.setGridLinesVisible(true);
        paneeli.getChildren().add(aa);

        SCENE1 = new Scene(paneeli, 600,400);
        return SCENE1;
    }

    private Scene ToinenSivu() {
        GridPane paneeli2 = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli2.setMinSize(200, 200);
        paneeli2.setAlignment(Pos.TOP_CENTER);
        paneeli2.setHgap(10);
        paneeli2.setVgap(20);

        SCENE2 = new Scene(paneeli2, 600,400);
        return SCENE2;
    }

    private Scene KolmasSivu() {
        GridPane paneeli3 = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli3.setMinSize(200, 200);
        paneeli3.setAlignment(Pos.TOP_CENTER);
        paneeli3.setHgap(10);
        paneeli3.setVgap(20);

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

    public static void main(String[] args) {
        launch();
    }
}

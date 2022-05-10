package src.frontend.ObjectUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import src.frontend.ObjectUI.AlueHallinta.AlueHallintaNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiHallintaNakyma;
import src.frontend.ObjectUI.PalveluHallinta.PalveluHallintaNakyma;
import src.frontend.ObjectUI.VarausHallinta.VarausHallintaNakyma;

public class NavBar extends HBox {

    private Button yleisnakymaButton;
    private Button mokkiHallintaNakymaButton;
    private Button alueidenHallintaNakymaButton;
    private Button varaustenHallintaNakymaButton;
    private Button palveluidenHallintaNakymaButton;

    private Stage stage;

    public NavBar(Stage stage) {
        this.stage = stage;

        this.setPadding(new Insets(15));

        this.yleisnakymaButton = new Button("Yleisnäkymä");
        this.mokkiHallintaNakymaButton = new Button("Mökkien hallinta");
        this.alueidenHallintaNakymaButton = new Button("Alueiden hallinta");
        this.varaustenHallintaNakymaButton = new Button("Varausten hallinta");
        this.palveluidenHallintaNakymaButton = new Button("Palveluiden hallinta");

        this.setSpacing(10);

        this.getChildren().addAll(yleisnakymaButton, mokkiHallintaNakymaButton, alueidenHallintaNakymaButton, varaustenHallintaNakymaButton, palveluidenHallintaNakymaButton);

        generateActions();
    }

    private void generateActions() {
        yleisnakymaButton.setOnAction(e -> {
            stage.setScene(new Scene(new YleisNakyma(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            stage.setTitle("Yleisnäkymä");
        });
        mokkiHallintaNakymaButton.setOnAction(e -> {
            stage.setScene(new Scene(new MokkiHallintaNakyma(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            stage.setTitle("Mökkien hallinta");
        });
        alueidenHallintaNakymaButton.setOnAction(e -> {
            stage.setScene(new Scene(new AlueHallintaNakyma(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            stage.setTitle("Alueiden hallinta");
        });
        varaustenHallintaNakymaButton.setOnAction(e -> {
            stage.setScene(new Scene(new VarausHallintaNakyma(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            stage.setTitle("Varausten hallinta");
        });
        palveluidenHallintaNakymaButton.setOnAction(e-> {
            stage.setScene(new Scene(new PalveluHallintaNakyma(stage), stage.getScene().getWidth(), stage.getScene().getHeight()));
            stage.setTitle("Palveluiden hallinta");
        });
    }
}

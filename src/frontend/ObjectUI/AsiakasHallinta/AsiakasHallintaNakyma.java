package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.frontend.ObjectUI.NavBar;

public class AsiakasHallintaNakyma extends BorderPane {

    private Stage stage;

    Button searchButton;
    Button clearButton;

    Button addButton;
    Button clearButton2;

    private ScrollPane sidePanel = new ScrollPane();

    AsiakasTable table = new AsiakasTable();

    public AsiakasHallintaNakyma(Stage stage) {
        this.stage = stage;

        this.searchButton = new Button("Hae");
        this.clearButton = new Button("Tyhjennä");

        this.addButton = new Button("Lisää");
        this.clearButton2 = new Button("Tyhjennä");

        this.construct();
        this.applyStyle();
    }

    public void construct() {
        sidePanel.setContent(
                new VBox(
                        new AsiakasSearchPane(searchButton, clearButton, table),
                        new AsiakasAdditionPane(addButton, clearButton2))
        );

        this.setLeft(sidePanel);
        this.setTop(new NavBar(stage));
        this.setCenter(table);
        this.setRight(new AsiakasInspectPane(table.getSelectedItem()));
    }

    public AsiakasTable getTable() {
        return table;
    }


    private void applyStyle() {
        sidePanel.setPrefViewportWidth(180);
        /*this.setPrefWidth(1980);
        this.setPrefHeight(1080);*/
    }
}

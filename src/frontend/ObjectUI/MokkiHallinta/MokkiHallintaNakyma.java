package src.frontend.ObjectUI.MokkiHallinta;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.frontend.ObjectUI.NavBar;


public class MokkiHallintaNakyma extends BorderPane {

    private Stage stage;

    Button searchButton;
    Button clearButton;

    Button addButton;
    Button clearButton2;
    MokkiTable table = new MokkiTable("interactive");

    private ScrollPane sidePanel = new ScrollPane();

    public MokkiHallintaNakyma(Stage stage) {
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
                    new MokkiSearchPane(searchButton, clearButton, table),
                    new MokkiAdditionPane(addButton, clearButton2))
                );

        this.setLeft(sidePanel);
        this.setTop(new NavBar(stage));
        this.setCenter(table);
        this.setRight(new MokkiInspectPane(table.getSelectedItem()));
    }

    public MokkiTable getTable() {
        return table;
    }


    private void applyStyle() {
        sidePanel.setPrefViewportWidth(180);
        /*this.setPrefWidth(1980);
        this.setPrefHeight(1080);*/
    }
}

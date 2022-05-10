package src.frontend.ObjectUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class YleisNakyma extends BorderPane {

    private VarausTable table = new VarausTable();

    private Button searchButton;
    private Button clearButton;

    private Stage stage;

    public YleisNakyma(Stage stage) {
        this.stage = stage;

        this.searchButton = new Button("Hae");
        this.clearButton = new Button("Tyhjennä");

        this.construct();
    }

    public Stage getStage() {
        return stage;
    }

    public void construct() {
        this.setLeft(new SearchPane(searchButton, clearButton, table));
        this.setCenter(table);
        this.setRight(new InspectPane(table.getSelectedItem()));
        this.setTop(new NavBar(stage));
    }
}

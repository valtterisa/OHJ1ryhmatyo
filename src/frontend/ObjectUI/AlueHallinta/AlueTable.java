package src.frontend.ObjectUI.AlueHallinta;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Alue;

public class AlueTable extends TableView<Alue> {

    private Alue selectedItem;

    public AlueTable() {
        TableColumn<Alue, Number> col1 = new TableColumn<>("Alue_Id");
        TableColumn<Alue, String> col2 = new TableColumn<>("Alue");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNimi()));

        this.getColumns().addAll(col1, col2);

        generateActions();
        applyStyle();
    }

    public Alue getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            AlueHallintaNakyma parent = (AlueHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä alueita, tai hakukriteereillä ei löytynyt yhtäkään aluetta"));
    }
}


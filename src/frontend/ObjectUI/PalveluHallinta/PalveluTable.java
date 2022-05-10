package src.frontend.ObjectUI.PalveluHallinta;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Palvelu;

public class PalveluTable extends TableView<Palvelu> {

    private Palvelu selectedItem;

    public PalveluTable() {
        TableColumn<Palvelu, Number> col1 = new TableColumn<>("Palvelu_Id");
        TableColumn<Palvelu, Number> col2 = new TableColumn<>("Alue_Id");
        TableColumn<Palvelu, String> col3 = new TableColumn<>("Alue");
        TableColumn<Palvelu, String> col4 = new TableColumn<>("Nimi");
        TableColumn<Palvelu, Number> col5 = new TableColumn<>("Tyyppi");
        TableColumn<Palvelu, String> col6 = new TableColumn<>("Kuvaus");
        TableColumn<Palvelu, Number> col7 = new TableColumn<>("Hinta");
        TableColumn<Palvelu, Number> col8 = new TableColumn<>("Alv");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getPalvelu_id())));
        col2.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getAlue_id())));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlue()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNimi()));
        col5.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getTyyppi())));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKuvaus()));
        col7.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getHinta())));
        col8.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getAlv())));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);

        generateActions();
        applyStyle();
    }

    public Palvelu getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            PalveluHallintaNakyma parent = (PalveluHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä palveluja, tai hakukriteereillä ei löytynyt yhtäkään palvelua"));
    }
}

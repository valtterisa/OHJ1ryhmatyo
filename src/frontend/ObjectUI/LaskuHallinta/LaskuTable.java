package src.frontend.ObjectUI.LaskuHallinta;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Lasku;

public class LaskuTable extends TableView<Lasku> {

    private Lasku selectedItem;

    public LaskuTable() {
        TableColumn<Lasku, Number> col1 = new TableColumn<>("Lasku_Id");
        TableColumn<Lasku, Number> col2 = new TableColumn<>("Varaus_Id");
        TableColumn<Lasku, String> col3 = new TableColumn<>("Etunimi");
        TableColumn<Lasku, String> col4 = new TableColumn<>("Sukunimi");
        TableColumn<Lasku, String> col5 = new TableColumn<>("Osoite");
        TableColumn<Lasku, String> col6 = new TableColumn<>("Postinumero");
        TableColumn<Lasku, String> col7 = new TableColumn<>("Postitoimipaikka");
        TableColumn<Lasku, String> col8 = new TableColumn<>("Puhelinnro");
        TableColumn<Lasku, String> col9 = new TableColumn<>("Email");
        TableColumn<Lasku, String> col10 = new TableColumn<>("Palvelut");
        TableColumn<Lasku, Number> col11 = new TableColumn<>("Hinta");
        TableColumn<Lasku, Number> col12 = new TableColumn<>("Alv");
        TableColumn<Lasku, Number> col13 = new TableColumn<>("Loppusumma");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getLasku_id())));
        col2.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getVaraus().getVaraus_id())));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getEtunimi()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getSukunimi()));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getLahiosoite()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getPostinro()));
        col7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getPostitoimipaikka()));
        col8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getPuhelinnro()));
        col9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getAsiakas().getEmail()));
        col10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaraus().getPalvelut().toString()));
        col11.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getSumma())));
        col12.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getAlv())));
        col13.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getAlv()) + Double.parseDouble(cellData.getValue().getSumma())));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13);

        generateActions();
        applyStyle();
    }

    public Lasku getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            LaskuHallintaNakyma parent = (LaskuHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä laskuja, tai hakukriteereillä ei löytynyt yhtäkään laskua"));
    }
}

package src.frontend.ObjectUI.AsiakasHallinta;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Asiakas;

public class AsiakasTable extends TableView<Asiakas> {

    private Asiakas selectedItem;

    public AsiakasTable() {
        TableColumn<Asiakas, Number> col1 = new TableColumn<>("Asiakas_Id");
        TableColumn<Asiakas, String> col2 = new TableColumn<>("Etunimi");
        TableColumn<Asiakas, String> col3 = new TableColumn<>("Sukunimi");
        TableColumn<Asiakas, String> col4 = new TableColumn<>("Osoite");
        TableColumn<Asiakas, Number> col5 = new TableColumn<>("Postinumero");
        TableColumn<Asiakas, String> col6 = new TableColumn<>("postitoimipaikka");
        TableColumn<Asiakas, String> col7 = new TableColumn<>("S.posti");
        TableColumn<Asiakas, String> col8 = new TableColumn<>("Puhelinnro");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAsiakas_id()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtunimi()));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSukunimi()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLahiosoite()));
        col5.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getPostinro())));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostitoimipaikka()));
        col7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        col8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPuhelinnro()));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);

        generateActions();
        applyStyle();
    }

    public Asiakas getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            AsiakasHallintaNakyma parent = (AsiakasHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä asiakkaita, tai hakukriteereillä ei löytynyt yhtäkään asiakasta"));
    }
}

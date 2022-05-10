package src.frontend.ObjectUI.VarausHallinta;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Varaus;

import java.util.List;

public class VarausTable extends TableView<Varaus> {

    private Varaus selectedItem;

    public VarausTable() {
        TableColumn<Varaus, Number> col0 = new TableColumn<>("Varaus-Id");
        TableColumn<Varaus, Number> col1 = new TableColumn<>("Mökki-Id");
        TableColumn<Varaus, String> col2 = new TableColumn<>("Mökin nimi");
        TableColumn<Varaus, Number> col3 = new TableColumn<>("Asiakas-Id");
        TableColumn<Varaus, String> col4 = new TableColumn<>("Etunimi");
        TableColumn<Varaus, String> col5 = new TableColumn<>("Sukunimi");
        TableColumn<Varaus, String> col6 = new TableColumn<>("Puhelinnro");
        TableColumn<Varaus, String> col7 = new TableColumn<>("Sähköposti");
        TableColumn<Varaus, String> col8 = new TableColumn<>("Varattu");
        TableColumn<Varaus, String> col9 = new TableColumn<>("Vahvistettu");
        TableColumn<Varaus, String> col10 = new TableColumn<>("Alkaa");
        TableColumn<Varaus, String> col11 = new TableColumn<>("Päättyy");

        TableColumn<Varaus, List> col12 = new TableColumn<>("Palvelut");

        col0.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVaraus_id()));
        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMokki().getMokki_id()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMokki().getMokkinimi()));
        col3.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAsiakas().getAsiakas_id()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEtunimi()));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getSukunimi()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getPuhelinnro()));
        col7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEmail()));
        col8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVarattu_pvm()));
        col9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVahvistus_pvm()));
        col10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVarattu_alkupvm()));
        col11.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVarattu_loppupvm()));

        col12.setCellValueFactory(cellData -> new SimpleListProperty(FXCollections.observableArrayList(cellData.getValue().getPalvelut())));

        this.getColumns().addAll(col0, col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12);

        generateActions();
        applyStyle();
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            VarausHallintaNakyma parent = (VarausHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    public Varaus getSelectedItem() {
        return selectedItem;
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä varauksia, tai hakukriteereillä ei löytynyt yhtäkään varausta"));
    }
}

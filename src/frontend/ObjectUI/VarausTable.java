package src.frontend.ObjectUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Varaus;

public class VarausTable extends TableView<Varaus> {

    private Varaus selectedItem;

    public VarausTable () {
        TableColumn<Varaus, Number> col1 = new TableColumn<>("mökin id");
        TableColumn<Varaus, String> col2 = new TableColumn<>("mökin nimi");
        TableColumn<Varaus, String> col3 = new TableColumn<>("etunimi");
        TableColumn<Varaus, String> col4 = new TableColumn<>("sukunimi");
        TableColumn<Varaus, String> col5 = new TableColumn<>("puhelinnro");
        TableColumn<Varaus, String> col6 = new TableColumn<>("sähköposti");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMokki().getMokki_id()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMokki().getMokkinimi()));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEtunimi()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getSukunimi()));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getPuhelinnro()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsiakas().getEmail()));

        this.getColumns().add(col1);
        this.getColumns().add(col2);
        this.getColumns().add(col3);
        this.getColumns().add(col4);
        this.getColumns().add(col5);
        this.getColumns().add(col6);

        this.generateActions();
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            YleisNakyma parent = (YleisNakyma) this.getParent();
            parent.construct();
        });
    }

    public Varaus getSelectedItem() {
        return this.selectedItem;
    }
}

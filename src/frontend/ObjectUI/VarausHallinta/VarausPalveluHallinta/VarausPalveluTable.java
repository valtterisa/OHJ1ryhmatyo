package src.frontend.ObjectUI.VarausHallinta.VarausPalveluHallinta;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.VarauksenPalvelu;


public class VarausPalveluTable extends TableView<VarauksenPalvelu> {

    private VarauksenPalvelu selectedItem;

    public VarausPalveluTable() {

        TableColumn<VarauksenPalvelu, Number> col1 = new TableColumn<>("Varaus_Id");
        TableColumn<VarauksenPalvelu, Number> col2 = new TableColumn<>("Palvelu_Id");
        TableColumn<VarauksenPalvelu, Number> col3 = new TableColumn<>("Lukumäärä");
        TableColumn<VarauksenPalvelu, Number> col4 = new TableColumn<>("Alue_Id");
        TableColumn<VarauksenPalvelu, String> col5 = new TableColumn<>("Alue");
        TableColumn<VarauksenPalvelu, String> col6 = new TableColumn<>("Nimi");
        TableColumn<VarauksenPalvelu, Number> col7 = new TableColumn<>("Tyyppi");

        TableColumn<VarauksenPalvelu, String> col8 = new TableColumn<>("Kuvaus");
        TableColumn<VarauksenPalvelu, Number> col9 = new TableColumn<>("Hinta");
        TableColumn<VarauksenPalvelu, Number> col10 = new TableColumn<>("Alv");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getVaraus_id())));
        col2.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getPalvelu_id())));
        col3.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getLkm())));
        col4.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getPalvelu().getAlue_id())));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPalvelu().getAlue()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPalvelu().getNimi()));
        col7.setCellValueFactory(cellData -> new SimpleIntegerProperty(Integer.parseInt(cellData.getValue().getPalvelu().getTyyppi())));

        col8.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPalvelu().getKuvaus()));
        col9.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getPalvelu().getHinta())));
        col10.setCellValueFactory(cellData -> new SimpleDoubleProperty(Double.parseDouble(cellData.getValue().getPalvelu().getAlv())));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10);

        generateActions();
        applyStyle();
    }

    public VarauksenPalvelu getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            VarausEditPalveluNakyma parent = (VarausEditPalveluNakyma) this.getParent();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä palveluja, tai hakukriteereillä ei löytynyt yhtäkään palvelua"));
    }
}

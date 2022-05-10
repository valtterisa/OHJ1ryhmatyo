package src.frontend.ObjectUI.MokkiHallinta;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.YleisNakyma;

public class MokkiTable extends TableView<Mokki> {

    private Mokki selectedItem;

    public MokkiTable() {

        TableColumn<Mokki, Number> col1 = new TableColumn<>("Mökki_Id");
        TableColumn<Mokki, String> col2 = new TableColumn<>("Nimi");
        TableColumn<Mokki, Number> col3 = new TableColumn<>("Alue_Id");
        TableColumn<Mokki, String> col4 = new TableColumn<>("Alue");
        TableColumn<Mokki, String> col5 = new TableColumn<>("Osoite");
        TableColumn<Mokki, String> col6 = new TableColumn<>("Postinumero");
        TableColumn<Mokki, String> col7 = new TableColumn<>("Postitoimipaikka");
        TableColumn<Mokki, Number> col8 = new TableColumn<>("Hinta");
        TableColumn<Mokki, String> col9 = new TableColumn<>("Kuvaus");
        TableColumn<Mokki, Number> col10 = new TableColumn<>("Henkilömäärä");
        TableColumn<Mokki, String> col11 = new TableColumn<>("Varustelu");

        col1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getMokki_id()));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMokkinimi()));
        col3.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAlue_id()));
        col4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlue()));
        col5.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKatuosoite()));
        col6.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostinro()));
        col7.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostitoimipaikka()));
        col8.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHinta()));
        col9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKuvaus()));
        col10.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHenkilomaara()));
        col11.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVarustelu()));

        this.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11);

        generateActions();
        applyStyle();
    }

    public Mokki getSelectedItem() {
        return selectedItem;
    }

    private void generateActions() {
        this.setOnMouseClicked(e -> {
            this.selectedItem = this.getSelectionModel().getSelectedItem();
            MokkiHallintaNakyma parent = (MokkiHallintaNakyma) this.getParent();
            parent.construct();
        });
    }

    private void applyStyle() {
        this.setPlaceholder(new Label("Taulukkoon ei ole haettu vielä mökkejä, tai hakukriteereillä ei löytynyt yhtäkään mökkiä"));
    }
}

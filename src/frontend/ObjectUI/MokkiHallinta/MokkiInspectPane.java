package src.frontend.ObjectUI.MokkiHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Mokki;

public class MokkiInspectPane extends VBox{

    private Mokki selectedMokki;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public MokkiInspectPane(Mokki selectedItem) {
        this.selectedMokki = selectedItem;
        this.editButton = new Button("Muokkaa valittua mökkiä");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu mökki");

        generateActions();
        construct();
        applyStyle();
    }
    public MokkiInspectPane(Mokki selectedItem, String plain) {
        this.selectedMokki = selectedItem;
        this.editButton = new Button("Muokkaa valittua mökkiä");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu mökki");

        this.editButton.setVisible(false);
        this.closeButton.setVisible(false);
        this.deleteButton.setVisible(false);

        //generateActions();
        construct();
        applyStyle();
    }


    private void construct() {
        if (this.selectedMokki != null) {

            VBox mokkiInfo = new VBox();
            Mokki mokki = selectedMokki;

            Text mokkiTitle = new Text("Mökin tiedot: ");
            Text mokkiId = new Text("Mökki-id: " + mokki.getMokki_id());
            Text mokkiName = new Text("Nimi: " + mokki.getMokkinimi());
            Text mokkiKuvaus = new Text("Kuvaus: " + mokki.getKuvaus() + "\n(alue: " + mokki.getAlue() + ", hlömäärä: " + mokki.getHenkilomaara() + ")");
            Text mokkiVarustelu = new Text("Varustelu: " + mokki.getVarustelu());
            Text mokkiAddress = new Text("Katuosoite: " + mokki.getKatuosoite());
            Text mokkiPostal = new Text("Postinumero ja toimipaikka: " + mokki.getPostinro() + ", " + mokki.getPostitoimipaikka());


            mokkiTitle.setFont(new Font(20));
            mokkiTitle.setUnderline(true);


            mokkiInfo.getChildren().add(mokkiTitle);
            mokkiInfo.getChildren().add(mokkiId);
            mokkiInfo.getChildren().add(mokkiName);
            mokkiInfo.getChildren().add(mokkiKuvaus);
            mokkiInfo.getChildren().add(mokkiVarustelu);
            mokkiInfo.getChildren().add(mokkiAddress);
            mokkiInfo.getChildren().add(mokkiPostal);

            this.getChildren().addAll(mokkiInfo, editButton, deleteButton, closeButton);
        }
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new MokkiEditNakyma(this.selectedMokki, editWindow, (MokkiHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan mökkiä: id = " + this.selectedMokki.getMokki_id());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new MokkiDeleteNakyma(this.selectedMokki, deleteWindow, (MokkiHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan mökki: id = " + this.selectedMokki.getMokki_id());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedMokki = null;
            this.getChildren().removeAll(this.getChildren());
            MokkiHallintaNakyma parent = (MokkiHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void applyStyle() {
        if (this.selectedMokki == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}

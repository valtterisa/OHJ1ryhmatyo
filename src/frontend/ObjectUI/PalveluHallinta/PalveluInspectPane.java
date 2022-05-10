package src.frontend.ObjectUI.PalveluHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.Palvelu;
import src.frontend.ObjectUI.MokkiHallinta.MokkiDeleteNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiEditNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiHallintaNakyma;

public class PalveluInspectPane extends VBox {

    private Palvelu selectedPalvelu;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public PalveluInspectPane(Palvelu selectedItem) {
        this.selectedPalvelu = selectedItem;
        this.editButton = new Button("Muokkaa valittua palvelua");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu palvelu");

        generateActions();
        construct();
        applyStyle();
    }

    private void construct() {
        if (this.selectedPalvelu != null) {

            VBox mokkiInfo = new VBox();
            Palvelu palvelu = selectedPalvelu;

            Text palveluTitle = new Text("Palvelun tiedot: ");
            Text palveluId = new Text("Palvelu-id: " + palvelu.getPalvelu_id());
            Text palveluName = new Text("Nimi: " + palvelu.getNimi());
            Text palveluKuvaus = new Text("Kuvaus: " + palvelu.getKuvaus() + "\n(alue: " + palvelu.getAlue() + ", tyyppi: " + palvelu.getTyyppi() + ")");
            Text palveluHinta = new Text("Hinta: " + palvelu.getHinta());
            Text palveluAlv = new Text("Alv: " + palvelu.getAlv());


            palveluTitle.setFont(new Font(20));
            palveluTitle.setUnderline(true);


            mokkiInfo.getChildren().add(palveluTitle);
            mokkiInfo.getChildren().add(palveluId);
            mokkiInfo.getChildren().add(palveluName);
            mokkiInfo.getChildren().add(palveluKuvaus);
            mokkiInfo.getChildren().add(palveluHinta);
            mokkiInfo.getChildren().add(palveluAlv);

            this.getChildren().addAll(mokkiInfo, editButton, deleteButton, closeButton);
        }
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new PalveluEditNakyma(this.selectedPalvelu, editWindow, (PalveluHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan palvelua: id = " + this.selectedPalvelu.getPalvelu_id());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new PalveluDeleteNakyma(this.selectedPalvelu, deleteWindow, (PalveluHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan palvelu: id = " + this.selectedPalvelu.getPalvelu_id());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedPalvelu = null;
            this.getChildren().removeAll(this.getChildren());
            PalveluHallintaNakyma parent = (PalveluHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void applyStyle() {
        if (this.selectedPalvelu == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}

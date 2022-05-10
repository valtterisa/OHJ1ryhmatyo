package src.frontend.ObjectUI.AlueHallinta;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.backend.datatypes.Alue;
import src.backend.datatypes.Mokki;
import src.frontend.ObjectUI.MokkiHallinta.MokkiDeleteNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiEditNakyma;
import src.frontend.ObjectUI.MokkiHallinta.MokkiHallintaNakyma;

public class AlueInspectPane extends VBox {

    private Alue selectedAlue;
    private Button editButton;
    private Button closeButton;
    private Button deleteButton;

    public AlueInspectPane(Alue selectedItem) {
        this.selectedAlue = selectedItem;
        this.editButton = new Button("Muokkaa valittua aluetta");
        this.closeButton = new Button("X");
        this.deleteButton = new Button("Poista valittu alue");

        generateActions();
        construct();
        applyStyle();
    }
    private void construct() {
        if (this.selectedAlue != null) {

            VBox alueInfo = new VBox();
            Alue alue = selectedAlue;

            Text alueTitle = new Text("Alueen tiedot: ");
            Text alueId = new Text("Alue-id: " + alue.getId());
            Text alueName = new Text("Alueen nimi: " + alue.getNimi());


            alueTitle.setFont(new Font(20));
            alueTitle.setUnderline(true);


            alueInfo.getChildren().add(alueTitle);
            alueInfo.getChildren().add(alueId);
            alueInfo.getChildren().add(alueName);

            this.getChildren().addAll(alueInfo, editButton, deleteButton, closeButton);
        }
    }

    private void generateActions() {
        this.editButton.setOnAction(e -> {
            Stage editWindow = new Stage();
            Scene editScene = new Scene(new AlueEditNakyma(this.selectedAlue, editWindow, (AlueHallintaNakyma) this.getParent()));
            editWindow.setScene(editScene);
            editWindow.setTitle("Muokataan aluetta: id = " + this.selectedAlue.getId());

            editWindow.show();
        });
        this.deleteButton.setOnAction(e -> {
            Stage deleteWindow = new Stage();
            Scene deleteScene = new Scene(new AlueDeleteNakyma(this.selectedAlue, deleteWindow, (AlueHallintaNakyma) this.getParent()), 350, 150);
            deleteWindow.setScene(deleteScene);
            deleteWindow.setTitle("Poistetaan alue: id = " + this.selectedAlue.getId());
            deleteWindow.setResizable(false);
            deleteWindow.show();
        });
        this.closeButton.setOnAction(e -> {
            this.selectedAlue = null;
            this.getChildren().removeAll(this.getChildren());
            AlueHallintaNakyma parent = (AlueHallintaNakyma) this.getParent();
            parent.getTable().getSelectionModel().clearSelection();
        });
    }

    private void applyStyle() {
        if (this.selectedAlue == null) {
            this.setPadding(new Insets(0));
        }
        else {
            this.setPadding(new Insets(10));
        }
    }
}


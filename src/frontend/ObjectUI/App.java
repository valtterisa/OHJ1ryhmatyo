package src.frontend.ObjectUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new YleisNakyma(stage));
        stage.setScene(scene);
        stage.setTitle("Village Newbies - Yleisnäkymä");

        stage.show();
    }
}

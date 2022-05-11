package src.frontend.ObjectUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import src.backend.api.BackendAPI;
import src.backend.datatypes.Mokki;
import src.backend.datatypes.Varaus;
import src.frontend.ObjectUI.LaskuHallinta.LaskuGenerator.LaskuGenerator;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        /*Scene scene = new Scene(new YleisNakyma(stage));
        stage.setScene(scene);
        stage.setTitle("Village Newbies - Yleisnäkymä");*/
/*
        WebView webView = new WebView();
        webView.getEngine().loadContent(LaskuGenerator.generateLasku(BackendAPI.getLasku(new HashMap<>()).get(0)));*/

        stage.show();
    }
}

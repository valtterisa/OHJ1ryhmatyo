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
        //launch(args);
        /*HashMap<String, String> params = new HashMap<>();
        params.put("mokki_id", "10");
        Varaus varaus = BackendAPI.getVarausTiedoilla(new HashMap<>(), params, new HashMap<>()).get(0);
        System.out.println(varaus);*/

        /*ArrayList<String> mokkiId = new ArrayList<String>();
        ArrayList<Mokki> mokit = BackendAPI.getMokki(new HashMap<>());
        int index = 0;
        for (Mokki i : mokit) {
            System.out.println(i);
            mokkiId.add(i.getMokki_id());
            index++;
        }*/


    }

    @Override
    public void start(Stage stage) throws Exception {
        /*Scene scene = new Scene(new YleisNakyma(stage));
        stage.setScene(scene);
        stage.setTitle("Village Newbies - Yleisnäkymä");*/

        WebView webView = new WebView();
        webView.getEngine().loadContent(LaskuGenerator.generateLasku(BackendAPI.getLasku(new HashMap<>()).get(0)));

        stage.show();
    }
}

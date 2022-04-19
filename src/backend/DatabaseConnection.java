package src.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class DatabaseConnection {

    // vakioita tietokantayhteyttä varten
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/vn";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String MAX_POOL = "250";

    private Connection connection;
    private Properties properties;

    // palauttaa käyttäjätiedot properties-oliossa
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
            System.out.println("Properties set");
        }
        return properties;
    }

    // palauttaa tietokantayhteyden connection -objektissa
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                System.out.println("Connection established");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // katkaisee tietokantayhteyden
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList doSQL(String method, String table, HashMap<String, String> parameters) {

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        String sql = SQLGenerator.generateSQL(method, table, parameters);        // Tekee parametroitavan SQL-lauseen annetuista tiedoista
        System.out.println("Making query: " + sql);

        try {
            PreparedStatement statement = this.connect().prepareStatement(sql); // Asettaa generoidun SQL-lauseen statement-oliolle

            int i = 0;
            for (String str : parameters.keySet()) {                            // Parametroi SQL-lauseen ?-muuttujat
                statement.setString(i+1, parameters.get(str));
                i++;
            }


            if (method.equalsIgnoreCase("get")) {            // METHOD = GET
                try {
                    ResultSet res = statement.executeQuery();           // res-muuttujassa queryn palauttama data

                    int row = 0;
                    while(res.next()) {                                 // käy läpi res-muuttujan ja mäppää taulukon kaksiuloitteeseen ArrayListiin
                        list.add(new ArrayList());
                        for (int col = 0; col < (res.getMetaData().getColumnCount()); col++) {
                            list.get(row).add(res.getString(res.getMetaData().getColumnLabel(col + 1)));
                        }
                        row++;
                    }
                } catch (SQLException e) {                              // Virheenkäsittelyä
                    e.printStackTrace();
                }
            }

            if (method.equalsIgnoreCase("post")) {           // METHOD = POST
                int res = statement.executeUpdate();                    // res-muuttujassa updaten palauttama data
                System.out.println("Insert successful, affected rows: " + res);
            }
                } catch (SQLException e) {                              // Virheenkäsittelyä
            e.printStackTrace();
        } finally {                                                     // Lopuksi katkaisee tietokantayhteyden
            this.disconnect();
        }

        return list;                                                    // Palauttaa listan
    }


    /*Tietokantayhteyden testailua*/
    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();               // Tietokantayhteys

        HashMap<String, String> hakuehdot = new HashMap();              // Haku rajatuilla ehdoilla
        hakuehdot.put("etunimi", "Kullervo");
        hakuehdot.put("postinro", "18890");

        HashMap<String, String> uusiAsiakas = new HashMap();            // Tiedot uudelle lisättävälle asiakkaalle
        //asiakasParam.put("asiakas_id", "10");                         // Tietokannassa auto-increment, joten ei tarvitse
        uusiAsiakas.put("postinro", "18890");
        uusiAsiakas.put("etunimi", "Kullervo");
        uusiAsiakas.put("sukunimi", "Kulkija");
        uusiAsiakas.put("lahiosoite", "Kulkijankatu 6");
        uusiAsiakas.put("email", "kullervo.kulkija@outlook.com");
        uusiAsiakas.put("puhelinnro", "7777777777");

        System.out.println("GET (kaikki): " + db.doSQL("get", "mokki", new HashMap<>()));
        System.out.println("GET (rajattu): " + db.doSQL("get", "asiakas", hakuehdot));
        System.out.println("POST (uusi asiakas): " + db.doSQL("post", "asiakas", uusiAsiakas));

    }
}


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

    /**
     * Muodostaa yhteyden tietokantaan ja tekee sinne dynaamisesti generoidun SQL-kyselyn
     * annetuista parametreista. Lopuksi sulkee tietokantayhteyden, sekä palauttaa tietokannasta
     * saadun datan, tai tietoa tuloksesta kaksiuloitteidessa ArrayList:ssä.
     * <p>Metodit REST-rajapinnan mukaan: GET (select),
     * POST (insert), <s>PUT (update)</s>, DELETE (delete)</p>
     *
     * @param method  String-muuttuja; joko: <b>get, post tai delete</b>
     * @param table String-muuttuja; Tietokannan taulun tai näkymän nimi.
     * @param parameters HashMap<String, String>; Parametrit avain-arvo -pareina.
     * @return Data kaksiuloitteisessa ArrayList:ssä
     */
    public ArrayList doSQL(String method, String table, HashMap<String, String> parameters) {

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        String sql = SQLGenerator.generateSQL(method, table, parameters);        // Tekee parametroitavan SQL-lauseen annetuista tiedoista
        System.out.println("Making query: " + sql);

        try {
            PreparedStatement statement = this.connect().prepareStatement(sql,  // Asettaa generoidun SQL-lauseen statement-oliolle
                                                Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            for (String str : parameters.keySet()) {                             // Parametroi SQL-lauseen ?-muuttujat
                statement.setString(i+1, parameters.get(str));
                i++;
            }


            if (method.equalsIgnoreCase("get")) {                     // METHOD = GET
                ResultSet res = statement.executeQuery();                        // res-muuttujassa queryn palauttama data

                int row = 0;
                while(res.next()) {                                              // käy läpi res-muuttujan ja mäppää taulukon kaksiuloitteeseen ArrayListiin
                    list.add(new ArrayList());
                    for (int col = 0; col < (res.getMetaData().getColumnCount()); col++) {
                        list.get(row).add(res.getString(res.getMetaData().getColumnLabel(col + 1)));
                    }
                    row++;
                }
            }

            if (method.equalsIgnoreCase("post")) {                   // METHOD = POST
                int res = statement.executeUpdate();                            // res-muuttujassa päivitetty rivi

                ResultSet insertIdRes = statement.getGeneratedKeys();

                String insertIdColumn = null;
                int insertId = -1;
                while (insertIdRes.next()) {
                    insertId = insertIdRes.getInt(1);
                }

                System.out.println("Insert successful, affected rows: " + res + ", inserted id: " + insertId);
                list.clear();
                ArrayList<String> success = new ArrayList<>();
                success.add("Insert successful");                               // palautetaan listassa viesti, lisättyjen rivien määrä ja lisätyn rivin id
                success.add("" + res);
                success.add("" + insertId);
                list.add(success);
            }

            if (method.equalsIgnoreCase("delete")) {                 // METHOD = DELETE
                int res = statement.executeUpdate();                            // res-muuttujassa poistettujen rivien määrä
                System.out.println("Delete successful, affected rows: " + res);
                list.clear();
                ArrayList<String> success = new ArrayList<>();
                success.add("Delete successful");                               // palautetaan listassa viesti ja poistetut rivit
                success.add("" + res);
                list.add(success);
            }

        } catch (SQLException e) {                                              // Virheenkäsittelyä
            e.printStackTrace();
        } finally {                                                             // Lopuksi katkaisee tietokantayhteyden
            this.disconnect();
        }

        return list;                                                            // Palauttaa listan
    }

    /**
     * Muodostaa yhteyden tietokantaan ja tekee sinne dynaamisesti generoidun SQL-kyselyn
     * annetuista parametreista. Lopuksi sulkee tietokantayhteyden, sekä palauttaa tietokannasta
     * saadun datan, tai tietoa tuloksesta kaksiuloitteidessa ArrayList:ssä.
     * <p>Metodit REST-rajapinnan mukaan: GET (select),
     * POST (insert), PUT (update), DELETE (delete)</p>
     *
     * @param method  String-muuttuja; joko: <b>get, post, put tai delete</b>
     * @param table String-muuttuja; Tietokannan taulun tai näkymän nimi.
     * @param parameters HashMap<String, String>; Parametrit avain-arvo -pareina.
     * @param id String[]; Manipuloitavan sarakkeen id:n avain ja arvo taulukossa.
     * @return Data kaksiuloitteisessa ArrayList:ssä
     */
    public ArrayList doSQL(String method, String table, HashMap<String, String> parameters, String[] id) {

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        if (!method.equalsIgnoreCase("put")) {                       // Jos metodi ei ole put, niin ignoraa id-parametrin
            list = doSQL(method, table, parameters);
            return list;
        }
                                                                                //METHOD = PUT
        String sql = SQLGenerator.generateSQL(method, table, parameters, id);   // Tekee parametroitavan SQL-lauseen annetuista tiedoista
        System.out.println("Making query: " + sql);

        try {
            PreparedStatement statement = this.connect().prepareStatement(sql, // Asettaa generoidun SQL-lauseen statement-oliolle
                                                Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            for (String str : parameters.keySet()) {                            // Parametroi SQL-lauseen ?-muuttujat
                statement.setString(i+1, parameters.get(str));
                i++;
            }
            statement.setString(i+1, id[1]);

            int res = statement.executeUpdate();                                // res-muuttujassa updaten palauttama data

            System.out.println("Update successful, affected rows: " + res);
            HashMap<String, String> putReturn = new HashMap<>();
            putReturn.put(id[0], id[1]);

            list = this.doSQL("get", table, putReturn);                  // palautetaan listassa muokattu rivi tietokannasta

        } catch (SQLException e) {                                              // Virheenkäsittelyä
            e.printStackTrace();
        } finally {                                                             // Lopuksi katkaisee tietokantayhteyden
            this.disconnect();
        }

        return list;                                                            // Palauttaa listan
    }


    /*Tietokantayhteyden testailua*/
    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();                       // Tietokantayhteys

        HashMap<String, String> hakuehdot = new HashMap();                      // Haku rajatuilla ehdoilla
        hakuehdot.put("etunimi", "Kullervo");
        hakuehdot.put("postinro", "18890");

        HashMap<String, String> uusiAsiakas = new HashMap();                    // Tiedot uudelle lisättävälle asiakkaalle
        //asiakasParam.put("asiakas_id", "10");                                 // Tietokannassa auto-increment, joten ei tarvitse
        uusiAsiakas.put("postinro", "18890");
        uusiAsiakas.put("etunimi", "Kullervo");
        uusiAsiakas.put("sukunimi", "Kulkija");
        uusiAsiakas.put("lahiosoite", "Kulkijankatu 6");
        uusiAsiakas.put("email", "kullervo.kulkija@outlook.com");
        uusiAsiakas.put("puhelinnro", "7777777777");

        HashMap<String, String> muokattavaAsiakas = new HashMap();              // Tiedot muokatavalle asiakkaalle
        //muokattavaAsiakas.put("asiakas_id", "2");                             // muokattavan asiakkaan ID tulee omassa kentässään. EI TÄHÄN
        muokattavaAsiakas.put("postinro", "70150");
        muokattavaAsiakas.put("etunimi", "Pertti");
        muokattavaAsiakas.put("sukunimi", "Perämetsä");
        muokattavaAsiakas.put("lahiosoite", "Perämetsäpolku 3");
        muokattavaAsiakas.put("email", "Pertti.Perämetsä@gmail.com");
        muokattavaAsiakas.put("puhelinnro", "1231231230");

        String[] muokattavaID = new String[] {"asiakas_id", "2"};               // Muokattavan asiakkaan ID

        HashMap<String, String> poistettavaID = new HashMap<>();                // Poistettavan / Poistettavien asiakkaiden tiedot
        poistettavaID.put("asiakas_id", "37");                                  // esim. id

        System.out.println("GET (kaikki): " + db.doSQL("get", "mokki", new HashMap<>(), muokattavaID));
        System.out.println("GET (rajattu): " + db.doSQL("get", "asiakas", hakuehdot));
        System.out.println("POST (uusi asiakas): " + db.doSQL("post", "asiakas", uusiAsiakas));
        System.out.println("PUT (update): " + db.doSQL("put", "asiakas", muokattavaAsiakas, muokattavaID));
        System.out.println("DELETE (delete): " + db.doSQL("delete", "asiakas", poistettavaID));
    }
}


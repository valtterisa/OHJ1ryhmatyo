package src.backend;

import java.util.HashMap;

public class SQLGenerator {

    private static String generateGet(String table, HashMap<String, String> parameters) {
        StringBuilder sql;
        sql = new StringBuilder("SELECT * ");
        sql.append("FROM ").append(table).append(" WHERE (1=1)");           // Koska WHERE (1=1), niin helppo lisätä AND
        for (String param : parameters.keySet()) {                          // jos hakuehtoja, niin parametroitavat ? -muuttujat
            sql.append(" AND (").append(param).append(" = ?)");
        }
        return sql.toString();                                              // Palauttaa SQL-lauseen String-muuttujassa
    }

    private static String generatePost(String table, HashMap<String, String> parameters) {
        StringBuilder sql;
        sql = new StringBuilder("INSERT INTO " + table + " (");
        for (String str : parameters.keySet()) {                            // sarakkeiden nimet
            sql.append(str).append(",");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                          // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(") VALUES (");
        for (int i = 0; i < parameters.size(); i++) {                       // parametroitavat ? -muuttujat
            sql.append("?,");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                          // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(")");
        return sql.toString();                                              // Palauttaa SQL-lauseen String-muuttujassa
    }

    private static String generatePut(String table, HashMap<String, String> parameters, String[] id) {
        StringBuilder sql;
        sql = new StringBuilder("UPDATE " + table + " SET ");
        for (String str : parameters.keySet()) {                            // sarakkeiden nimet
            sql.append(str).append(" = ?,");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                          // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(" WHERE (" + id[0] + " = ?)");
        return sql.toString();                                              // Palauttaa SQL-lauseen String-muuttujassa
    }

    private static String generatePut(String table, HashMap<String, String> parameters, HashMap<String, String> where) {
        StringBuilder sql;
        sql = new StringBuilder("UPDATE " + table + " SET ");
        for (String str : parameters.keySet()) {                            // sarakkeiden nimet
            sql.append(str).append(" = ?,");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                          // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(" WHERE (1=1)");
        for (String str : where.keySet()) {
            sql.append(" AND ");
            sql.append(str).append(" = ?");
        }
        return sql.toString();                                              // Palauttaa SQL-lauseen String-muuttujassa
    }

    private static String generateDelete(String table, HashMap<String, String> parameters) {
        StringBuilder sql;
        sql = new StringBuilder("DELETE FROM " + table);
        sql.append(" WHERE (1=1)");                                         // Koska WHERE (1=1), niin helppo lisätä AND
        for (String param : parameters.keySet()) {                          // jos hakuehtoja, niin parametroitavat ? -muuttujat
            sql.append(" AND (").append(param).append(" = ?)");
        }
        return sql.toString();                                              // Palauttaa SQL-lauseen String-muuttujassa
    }

    /**
     * Generoi SQL-lauseen anetuista parametreista.
     * Palauttaa generoidun SQL-lauseen String-oliossa.
     * <p>Metodit REST-rajapinnan mukaan: GET (select),
     * POST (insert), <s>PUT (update)</s>, DELETE (delete)</p>
     *
     * @param method  String-muuttuja; joko: <b> get, post tai delete </b>
     * @param table String-muuttuja; Tietokannan taulun tai näkymän nimi.
     * @param parameters HashMap<String, String>; Parametrit avain-arvo -pareina.
     * @return SQL-lause String-muuttujassa
     */
    public static String generateSQL(String method, String table, HashMap<String, String> parameters) {
        System.out.println("Generating SQL...");
        String sql = null;

        if (method.equalsIgnoreCase("get")) {                   // METHOD = GET
            sql = generateGet(table, parameters);
        }

        else if (method.equalsIgnoreCase("post")) {             // METHOD = POST
            sql = generatePost(table, parameters);
        }

        else if (method.equalsIgnoreCase("delete")) {             // METHOD = DELETE
            sql = generateDelete(table, parameters);
        }
        else {                                                              // Jos metodi on tuntematon, printtaa erroria ja palauttaa nullia
            System.out.println("Error generating SQL. Unknown method: \"" + method + "\"");
            return null;
        }

        System.out.println("Generated SQL: " + sql);                        // Jos kaikki ok, palauttaa generoidun SQL-lauseen
        return sql;
    }

    /**
     * Generoi SQL-lauseen anetuista parametreista.
     * Palauttaa generoidun SQL-lauseen String-oliossa.
     * <p>Metodit REST-rajapinnan mukaan: GET (select),
     * POST (insert), PUT (update), DELETE (delete)</p>
     *
     * @param method  String-muuttuja; joko: <b> get, post, put tai delete </b>
     * @param table String-muuttuja; Tietokannan taulun tai näkymän nimi.
     * @param parameters HashMap<String, String>; Parametrit avain-arvo -pareina.
     * @param id String[]; Manipuloitavan sarakkeen id:n avain ja arvo taulukossa.
     * @return SQL-lause String-muuttujassa
     */
    public static String generateSQL(String method, String table, HashMap<String, String> parameters, String[] id) {
        System.out.println("Generating SQL...");
        String sql = null;

        if (method.equalsIgnoreCase("put")) {                   // METHOD = PUT
            sql = generatePut(table, parameters, id);
        }
        else {                                                              // Jos metodi on muu kuin put, ei ota huomioon id-kenttää
            sql = generateSQL(method, table, parameters);
        }

        System.out.println("Generated SQL: " + sql);                        // Jos kaikki ok, palauttaa generoidun SQL-lauseen
        return sql;
    }

    // POIKKEUSTAPAUS VARAUKSEN PALVELUILLE
    public static String generateSQL(String method, String table, HashMap<String, String> parameters, HashMap<String, String> where) {
        System.out.println("Generating SQL...");
        String sql = null;

        if (method.equalsIgnoreCase("put")) {                   // METHOD = PUT
            sql = generatePut(table, parameters, where);
        }
        else {                                                              // Jos metodi on muu kuin put, ei ota huomioon id-kenttää
            sql = generateSQL(method, table, parameters);
        }

        System.out.println("Generated SQL: " + sql);                        // Jos kaikki ok, palauttaa generoidun SQL-lauseen
        return sql;
    }
}

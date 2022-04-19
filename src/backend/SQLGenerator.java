package src.backend;

import java.util.HashMap;

public class SQLGenerator {

    private static String generateGet(String table, HashMap<String, String> parameters) {
        StringBuilder sql;
        sql = new StringBuilder("SELECT * ");
        sql.append("FROM ").append(table).append(" WHERE (1=1)");        // Koska WHERE (1=1), niin helppo lisätä AND
        for (String param : parameters.keySet()) {                       // jos hakuehtoja, niin parametroitavat ? -muuttujat
            sql.append(" AND (").append(param).append(" = ?)");
        }
        return sql.toString();                                          // Palauttaa SQL-lauseen String-muuttujassa
    }

    private static String generatePost(String table, HashMap<String, String> parameters) {
        StringBuilder sql;
        sql = new StringBuilder("INSERT INTO " + table + " (");
        for (String str : parameters.keySet()) {                        // sarakkeiden nimet
            sql.append(str).append(",");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                      // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(") VALUES (");
        for (int i = 0; i < parameters.size(); i++) {                   // parametroitavat ? -muuttujat
            sql.append("?,");
        }
        if (sql.charAt(sql.length() - 1) == ',') {                      // Jos lopussa pilkku, poistaa sen
            sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        }
        sql.append(")");
        return sql.toString();                                          // Palauttaa SQL-lauseen String-muuttujassa
    }

    /**
     * Generoi SQL-lauseen anetuisat parametreista.
     * Palauttaa generoidun SQL-lauseen String-oliossa.
     *
     * @param method  String-muuttuja; joko: get, post
     * @param table String-muuttuja; Tietokannan taulun tai näkymän nimi
     * @param parameters HashMap<String, String>; Parametrit avain-arvo -pareina
     * @return      SQL-lause String-muuttujassa
     */
    public static String generateSQL(String method, String table, HashMap<String, String> parameters) {
        System.out.println("Generating SQL...");
        String sql = null;

        if (method.equalsIgnoreCase("get")) {                // METHOD = GET
            sql = generateGet(table, parameters);
        }

        else if (method.equalsIgnoreCase("post")) {          // METHOD = POST
            sql = generatePost(table, parameters);
        }

        else {                                                          // Jos metodi on tuntematon, printtaa erroria ja palauttaa nullia
            System.out.println("Error generating SQL. Unknown method: \"" + method + "\"");
            return null;
        }

        System.out.println("Generated SQL: " + sql);                    // Jos kaikki ok, palauttaa generoidun SQL-lauseen
        return sql;
    }
}

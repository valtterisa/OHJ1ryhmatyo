package src.backend;

import java.util.HashMap;
import java.util.Scanner;

public class BackendDemo {
    public static void main(String[] args) {

        //Demo backend-koodin tämänhetkisestä toiminnallisuudesta

        System.out.println("Mökkijärjestelmän backend-demo käyttäen DatabaseConnection-luokkaa...");

        DatabaseConnection db = new DatabaseConnection();
        Scanner input = new Scanner(System.in);

        System.out.print("Mitä taulua haluat muokata? ");
        String table = input.nextLine();
        System.out.println("Muokataan taulua " + table);

        System.out.println("Mitä haluat tehdä taululle (get, post, put tai delete)?");
        String method = input.nextLine();

        String[] id = new String[2];

        HashMap<String, String> params = new HashMap<>();

        if (method.equalsIgnoreCase("get")) {
            System.out.println("Haettavien " + table + "-rivien rajaavat tiedot (tyhjä palauttaa kaikki)");
            System.out.println("Tyhjä arvo lopettaa");
            while (true) {
                System.out.print("Anna uusi avain:     ");
                String key = input.nextLine();
                System.out.print("Anna avaimelle arvo: ");
                String value = input.nextLine();
                System.out.println();

                if (key.length() == 0 || value.length() == 0) {
                    System.out.println("----------------------------------------------");
                    System.out.print("\nHaluatko varmasti lopettaa tietojen antamisen (y/n)? ");
                    if (input.nextLine().equalsIgnoreCase("y")) {
                        break;
                    } else {
                        continue;
                    }
                }
                params.put(key, value);
            }
        }

        if (method.equalsIgnoreCase("post")) {
            System.out.println("Lisättävän " + table + "-rivin tiedot: ");
            System.out.println("Tyhjä arvo lopettaa");
            while (true) {
                System.out.print("Anna uusi avain:     ");
                String key = input.nextLine();
                System.out.print("Anna avaimelle arvo: ");
                String value = input.nextLine();
                System.out.println();

                if (key.length() == 0 || value.length() == 0) {
                    System.out.println("----------------------------------------------");
                    System.out.print("\nHaluatko varmasti lopettaa tietojen antamisen (y/n)? ");
                    if (input.nextLine().equalsIgnoreCase("y")) {
                        break;
                    } else {
                        continue;
                    }
                }
                params.put(key, value);
            }
        }

        if (method.equalsIgnoreCase("delete")) {
            System.out.println("Poistettavien " + table + "-rivien rajaavat tiedot: ");
            System.out.println("Tyhjä arvo lopettaa");
            while (true) {
                System.out.print("Anna uusi avain:     ");
                String key = input.nextLine();
                System.out.print("Anna avaimelle arvo: ");
                String value = input.nextLine();
                System.out.println();

                if (key.length() == 0 || value.length() == 0) {
                    System.out.println("----------------------------------------------");
                    System.out.print("\nHaluatko varmasti lopettaa tietojen antamisen (y/n)? ");
                    if (input.nextLine().equalsIgnoreCase("y")) {
                        break;
                    } else {
                        continue;
                    }
                }
                params.put(key, value);
            }
        }

        if (method.equalsIgnoreCase("put")) {
            System.out.println("Muokattavan " + table + "-rivin id:n sarakkeen nimi? ");
            id[0] = input.nextLine();
            System.out.println("Muokattavan " + table + "-rivin id:n arvo? ");
            id[1] = input.nextLine();
            System.out.println("Muokattavan " + table + "-rivin id:" + id[1] + " uudet tiedot");
            System.out.println("Tyhjä arvo lopettaa");
            while (true) {
                System.out.print("Anna uusi avain:     ");
                String key = input.nextLine();
                System.out.print("Anna avaimelle arvo: ");
                String value = input.nextLine();
                System.out.println();

                if (key.length() == 0 || value.length() == 0) {
                    System.out.println("----------------------------------------------");
                    System.out.print("\nHaluatko varmasti lopettaa tietojen antamisen (y/n)? ");
                    if (input.nextLine().equalsIgnoreCase("y")) {
                        break;
                    } else {
                        continue;
                    }
                }
                params.put(key, value);
            }
        }

        System.out.println("Tietokanta palauttaa: " + db.doSQL(method, table, params, id));
    }
}

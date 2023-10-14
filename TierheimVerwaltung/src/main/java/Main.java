import Tiere.Affe;
import Tiere.Hund;
import Tiere.Katze;
import Tiere.Tier;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Arda Aksu
 */
@SuppressWarnings("SpellCheckingInspection")
public class Main {
    static String nextAction;
    static NewScanner newScanner = new NewScanner();
    static Datenbankverwaltung datenhaltung = new Datenbankverwaltung();

    public static void main(String[] args) throws SQLException {
        datenhaltung.getAll();
        System.out.println("Flotte Pfote\n----");
        while (!Objects.equals(nextAction, "a")) {
            options();
            aktionsoptionen();
        }
        System.out.println("Anwendung wurde beendet!");
        datenhaltung.con.close();
    }

    /**
     * <p>Gibt dem User seine Optionen
     * </p>
     */
    private static void aktionsoptionen() {
        switch (nextAction) {
            case "+" -> anlegen();
            case "-" -> tierSubtraktion();
            case "?" -> listenAusgabe();
            case "??" -> anzahlTiereProArt();
            case "#" -> tierBearbeitung();
            default -> {
                if (!Objects.equals(nextAction, "a")) {
                    System.out.println("Geben sie eine legale Eingabe ein!");
                }
            }
        }
    }

    /**
     * <p>Bearbeitet die Attribute der Tiere je nach User input
     * </p>
     */
    private static void tierBearbeitung() {
        System.out.println("\nKennnummer des Tieres, dass sie bearbeiten möchten:");
        int bearbeitTier = newScanner.nextInt2();
        Tier tier = datenhaltung.readTier(bearbeitTier);
        if(tier==null){
            System.out.println("\nDieses Tier existiert nicht");
            return;
        }
            tier.ausgabe(tier);
            System.out.println("\nWelches Attribut möchten sie bearbeiten (1)Name, (2)Alter:");
            int attribut = newScanner.nextInt2();
            if (attribut == 1) {
                System.out.println("\nNeuer Name");
                String neuerName = newScanner.nextLine2();
                tier.setName(neuerName);
            } else if (attribut == 2) {
                System.out.println("\nNeues Alter");
                int neuesAlter = newScanner.nextInt2();
                tier.setAlter(neuesAlter);
            }
            else{
                System.out.println("\nBitte geben sie eins oder zwei ein!");
            }
            datenhaltung.updateTier(tier);
        }

    /**
     * <p>Gibt anzahl der Tiere pro Art wieder
     * </p>
     */
    private static void anzahlTiereProArt() {
        Hund.anzahl = 0;
        Affe.anzahl = 0;
        Katze.anzahl = 0;
        Tier.anzahlderTiere();
        List<Tier> tierList;
        tierList = datenhaltung.getAll();
        for (Tier t : tierList) {
            if (t instanceof Hund) {
                Hund.anzahl++;
            } else if (t instanceof Katze) {
                Katze.anzahl++;
            } else if (t instanceof Affe) {
                Affe.anzahl++;
            }
        }
        System.out.println("\nHunde:" + Hund.anzahl + "\nKatzen:" + Katze.anzahl + "\nAffen:" + Affe.anzahl);
    }

    /**
     * <p>Gibt alle Tiere in der Liste aus
     * </p>
     */
    private static void listenAusgabe() {
        List<Tier> tierList;
        tierList = datenhaltung.getAll();
        System.out.println("Wollen sie:\n-nach einem Namen suchen 's'\n-nach einem attribut sortieren 'a'\n-normal ausgeben lassen 'n'");
        String p = newScanner.nextLine2();
        switch (p){
            case "n"->{
                for (Tier tier : tierList) {
                    tier.ausgabe(tier);
                }
            }
            case "a"-> {
                System.out.println("Nach welchen Attribut:\nAlter 'a'\nname 'n'");
                String d = newScanner.nextLine2();
                switch (d) {
                    case "a"->{
                    Comparator<Tier> b = Comparator.comparingInt(Tier::getAlter);
                    tierList.sort(b);
                    for (Tier tier : tierList) {
                        tier.ausgabe(tier);
                    }
                    }
                    case "n"->{
                        Comparator<Tier> namensComparator = Comparator.comparing(Tier::getName);
                        tierList.sort(namensComparator);
                        for (Tier tier : tierList) {
                            tier.ausgabe(tier);
                        }
                    }
                        default -> System.out.println("Geben sie eine legale Eingabe ein!");
                }
            }
            case "s"-> {
                System.out.println("Nach was suchen sie:");
                String namensSuche = newScanner.nextLine2();
                List<Tier> l = tierList.stream().filter(tier -> tier.getName().contains(namensSuche)).toList();
                for (Tier tier : l) {
                    tier.ausgabe(tier);
                }
            }
            default -> System.out.println("Geben sie eine legale Eingabe ein!");
        }
    }

    /**
     * <p>Entfernt ein Objekt aus der Liste
     * </p>
     */
    private static void tierSubtraktion() {
        System.out.println("Welches Tier möchten sie entfernen (als Kennummer):");
        int tierKennnummer = newScanner.nextInt2();
        Tier zuSubtrahierendesTier = datenhaltung.readTier(tierKennnummer);
        zuSubtrahierendesTier.ausgabe(zuSubtrahierendesTier);
        System.out.println("\nSind sie sich sicher, dass sie dieses Tier löschen möchten ('y', 'n'):");
        String entscheidung = newScanner.nextLine2();
        switch(entscheidung){
            case "y" -> {
                datenhaltung.deleteTier(tierKennnummer);
                System.out.println("\nTier wurde erfolgreich gelöscht!\n");
            }
            case "n" -> System.out.println("\nSie haben den prozess abgebrochen!\n");
            default -> System.out.println("\nBitte geben sie eine gültige eingabe ein!\n");
        }
    }

    /**
     * <p>Legt in einer Liste 'tierList', je nach user Input, eine Katze, einen Hund oder einen Affen mit spezifischer Kennnummer, Alter und Namen an
     * </p>
     */
    private static void anlegen() {
        System.out.println("Hund(1) oder Katze(2) oder Affe(3)?:\n");
        String dogvsCat = newScanner.nextLine2();
        int kennummer = Tier.hoechsteKennnummer;
        switch (Objects.requireNonNull(dogvsCat)) {
            case "1" -> {
                Hund hund1 = new Hund(++kennummer, getAlter(), getName());
                datenhaltung.createTier(hund1);
                hund1.ausgabe(hund1);
            }
            case "2" -> {
                Katze katze1 = new Katze(++kennummer, getAlter(), getName());
                datenhaltung.createTier(katze1);
                katze1.ausgabe(katze1);
            }
            case "3" -> {
                Affe affe1 = new Affe(++kennummer, getAlter(), getName());
                datenhaltung.createTier(affe1);
                affe1.ausgabe(affe1);
            }
            default -> {
                System.out.println("Sie müssen entweder 1, 2 oder 3 angeben");
                anlegen();
            }
        }
    }

    /**
     * <p>Nimmt das ALter des Tieres vom User
     * </p>
     *
     * @return Integer mit dem Alter des Tieres
     */
    private static int getAlter() {
        int alter;
        do {
            System.out.println("Was ist das alter des Tieres?:");
            alter = newScanner.nextInt2();
        } while (alter == -1);
        return alter;
    }

    /**
     * <p>Nimmt den Namen des Tieres vom User
     * </p>
     *
     * @return String mit dem Namen des Tieres
     */
    private static String getName() {
        String name;
        do {
            System.out.println("Was ist der Name dieses Tieres?:");
            name = newScanner.nextLine2();
        } while (Objects.equals(name, null));
        return name;
    }

    /**
     * <p>Gibt dem Nutzer seine Optionen
     * </p>
     */
    static void options() {
        System.out.println("""
                Welche Aktion möchten sie durchführen:
                                
                + = Tier hinzufügen
                - = Tier entfernen ⚠
                # = Tier bearbeiten
                ? = Liste der Tiere abrufen
                ?? = Menge der Tiere abrufen
                a = Beenden der Anwendung
                                
                Gewünschte Aktion:""");
        nextAction = newScanner.nextLine2();
    }
}
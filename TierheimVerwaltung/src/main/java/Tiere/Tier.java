package Tiere;

import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Arda Aksu
 */
@SuppressWarnings("SpellCheckingInspection")
public class Tier {
    int alter;
    public static int anzahl, hoechsteKennnummer;
    int kennnummer;
    String name;

    public Tier(int kennnummer, int alter, String name) {
        this.kennnummer = kennnummer;
        this.alter = alter;
        this.name = name;
        anzahl++;
        if (kennnummer > hoechsteKennnummer) {
            hoechsteKennnummer = this.kennnummer;
        }
    }

    /**
     * <p>Gibt die vordefinierte Menge an Tieren wieder
     * </p>
     */
    public static void anzahlderTiere() {
        System.out.println("\nIn unserem Tierheim befinden sich " + anzahl + " Tiere\n");

    }

    /**
     * <p>Gibt die das Alter des Tieres wieder
     * </p>
     *
     * @return alter des Tieres
     */
    public int getAlter() {
        return this.alter;
    }

    /**
     * <p>Setzt das Alter des Tieres</p>
     *
     * @param alter ist das Alter das zu setzen ist
     */
    public void setAlter(int alter) {
        this.alter = alter;
    }


    /**
     * @return name des Tieres
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Setzt den Namen des Tieres</p>
     *
     * @param name ist der Name der zu setzen ist
     */
    public void setName(String name) {
        this.name = name;
    }


    public static void clearAnzahl() {
        anzahl = 0;
        hoechsteKennnummer = 0;
    }

    /**
     * @return kennummer des Tieres
     */
    public int getKennnummer() {
        return this.kennnummer;
    }

    /**
     * <p>Gibt alle Informationen der Tiere wieder</p>
     */
    public void ausgabe(Tier tier) {
        if (Objects.equals(tier, this)) {
            System.out.println("\nTier\n" + "Name: " + this.getName() + "\nAlter: " + this.getAlter() + "\nKennummer: " + tier.getKennnummer() + "\n---\n");
        }
    }

    public String getArt(Tier tier) {
        if (tier instanceof Katze) {
            return "Katze";
        }
        if (tier instanceof Hund) {
            return "Hund";
        }
        if (tier instanceof Affe) {
            return "Affe";
        }
        return null;
    }
}

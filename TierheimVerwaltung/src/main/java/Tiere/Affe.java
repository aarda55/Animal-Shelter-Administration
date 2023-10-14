package Tiere;

import java.util.Objects;

/**
 *
 * @author Arda Aksu
 */
@SuppressWarnings("SpellCheckingInspection")
public class Affe extends Tier{
    public static int anzahl;
    public Affe(int kennnummer, int alter, String name) {
        super(kennnummer, alter, name);
    }

    /**
     * @param tier ist das Tier, dessen Informationenausgegeben werden sollen
     */
    @Override
    public void ausgabe(Tier tier){
        if(Objects.equals(tier, this)){
            System.out.println("\nAffe\n" + "Name: " + this.getName() + "\nAlter: " + this.getAlter() + "\nKennummer: " + tier.getKennnummer() + "\n---\n");
        }
    }
}

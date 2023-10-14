package Tiere;

import java.util.Objects;

/**
 *
 * @author Arda Aksu
 */
@SuppressWarnings("SpellCheckingInspection")
public class Katze extends Tier{

    public static int anzahl;
    public Katze(int kennnummer, int alter, String name) {
        super(kennnummer, alter, name);
    }


    /**
     * @param tier ist das Tier, dessen Informationenausgegeben werden sollen
     */
    @Override
    public void ausgabe(Tier tier){
        if(Objects.equals(tier, this)){
            System.out.println("\nKatze\n" + "Name: " + this.getName() + "\nAlter: " + this.getAlter()+ "\nKennummer: " + tier.getKennnummer() +  "\n---\n");
        }
    }

}

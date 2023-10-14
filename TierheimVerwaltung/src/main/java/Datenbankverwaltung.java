import Tiere.Affe;
import Tiere.Hund;
import Tiere.Katze;
import Tiere.Tier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arda Aksu
 */
@SuppressWarnings("ALL")
public class Datenbankverwaltung implements TierDatenverwaltungsInterface {

    public Connection con;

    public Datenbankverwaltung() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/TIERE", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Erzeugt Tier in der Datenbank
     * </p>
     */
    @Override
    public void createTier(Tier tier) {
        try {
            {
                PreparedStatement stmt = con.prepareStatement("INSERT INTO `tierliste` VALUES (?,?,?,?)");
                stmt.setInt(1,tier.getKennnummer());
                stmt.setString(2,tier.getArt(tier));
                stmt.setString(3,tier.getName());
                stmt.setInt(4,tier.getAlter());
                int rs = stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param kennummer Die Kennnummer des zu lesenden Tieres
     * @return Das erstellte Tier
     */
    @Override
    public Tier readTier(int kennummer) {
        try {
            {
                PreparedStatement stmt = con.prepareStatement("select * from `tierliste` WHERE `kennnummer` = ?");
                stmt.setInt(1,kennummer);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int kennnummer = rs.getInt(1);
                String art = rs.getString(2);
                String name = rs.getString(3);
                int alter = rs.getInt(4);
                if (art.equals("Katze")) {
                    Katze katze1 = new Katze(kennnummer, alter, name);
                    return katze1;
                }
                if (art.equals("Hund")) {
                    Hund hund1 = new Hund(kennnummer, alter, name);
                    return hund1;
                }
                if (art.equals("Affe")) {
                    Affe affe1 = new Affe(kennnummer, alter, name);
                    return affe1;
                }

            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * <p>Aktualisiert daten eines Tieres
     * </p>
     *
     * @param tier Das Tier das aktualisiert werden soll
     */
    @Override
    public void updateTier(Tier tier) {
        try {
            {
                int kennummer = tier.getKennnummer();
                PreparedStatement stmt = con.prepareStatement("UPDATE `tierliste` SET `art` = ?, `name` = ?, `alter` = ? WHERE `kennnummer` = ?");
                stmt.setString(1,tier.getArt(tier));
                stmt.setString(2,tier.getName());
                stmt.setInt(3,tier.getAlter());
                stmt.setInt(4,tier.getKennnummer());
                int rs = stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param kennummer Die Kennnummer des zu löschenden Tieres
     */
    @Override
    public void deleteTier(int kennummer) {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM `tierliste` WHERE `kennnummer` = ?");
            stmt.setInt(1,kennummer);
            int rs = stmt.executeUpdate();
            getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>Liest alle Tiere in der Datenbank aus</p>
     *
     * @return Die Liste mit allen Tieren
     */
    @Override
    public List<Tier> getAll() {
        List<Tier> tierListe = new ArrayList<>();
        try {
            {
                Statement stmt = con.createStatement();
                String query = "SELECT * from `tierliste`";

                ResultSet rs = stmt.executeQuery(query);
                tierListe.clear();
                Tier.clearAnzahl();
                while (rs.next()) {
                    int kennnummer = rs.getInt(1);
                    int alter = rs.getInt(4);
                    String name = rs.getString(3);
                    String art = rs.getString(2);
                    if (art.equals("Katze")) {
                        Katze katze1 = new Katze(kennnummer, alter, name);
                        tierListe.add(katze1);
                    }
                    if (art.equals("Hund")) {
                        Hund hund1 = new Hund(kennnummer, alter, name);
                        tierListe.add(hund1);
                    }
                    if (art.equals("Affe")) {
                        Affe affe1 = new Affe(kennnummer, alter, name);
                        tierListe.add(affe1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tierListe;
    }
}
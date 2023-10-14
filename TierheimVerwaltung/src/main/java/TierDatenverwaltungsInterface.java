import Tiere.Tier;

import java.util.List;

public interface TierDatenverwaltungsInterface {
    void createTier(Tier tier);
    Tier readTier(int kennummer);
    void updateTier(Tier tier);
    void deleteTier(int kennummer);
    List<Tier> getAll();
}

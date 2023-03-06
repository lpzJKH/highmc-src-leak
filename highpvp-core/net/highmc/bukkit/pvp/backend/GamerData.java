package net.highmc.bukkit.pvp.backend;

import java.util.UUID;
import net.highmc.bukkit.pvp.gamer.Gamer;

public interface GamerData {
  <T extends Gamer> T loadGamer(UUID paramUUID, Class<T> paramClass);
  
  boolean deleteGamer(UUID paramUUID);
  
  boolean createGamer(Gamer paramGamer);
}


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/backend/GamerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
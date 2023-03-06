/*    */ package net.highmc.bukkit.pvp.manager;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.highmc.CommonPlugin;
/*    */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GamerManager
/*    */ {
/* 16 */   private Map<UUID, Gamer> gamers = new HashMap<>();
/*    */ 
/*    */   
/*    */   public void loadGamer(Gamer gamer) {
/* 20 */     this.gamers.put(gamer.getUniqueId(), gamer);
/* 21 */     CommonPlugin.getInstance().debug("The gamer " + gamer.getUniqueId() + " has been loaded.");
/*    */   }
/*    */   
/*    */   public <T extends Gamer> T getGamer(UUID uuid, Class<T> clazz) {
/* 25 */     return clazz.cast(this.gamers.get(uuid));
/*    */   }
/*    */   
/*    */   public Gamer getGamer(UUID uuid) {
/* 29 */     return this.gamers.get(uuid);
/*    */   }
/*    */   
/*    */   public Collection<Gamer> getGamers() {
/* 33 */     return this.gamers.values();
/*    */   }
/*    */   
/*    */   public void unloadGamer(UUID uuid) {
/* 37 */     this.gamers.remove(uuid);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/manager/GamerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
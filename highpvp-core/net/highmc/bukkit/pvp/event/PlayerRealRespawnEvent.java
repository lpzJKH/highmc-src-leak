/*    */ package net.highmc.bukkit.pvp.event;
/*    */ 
/*    */ import lombok.NonNull;
/*    */ import net.highmc.bukkit.event.PlayerEvent;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlayerRealRespawnEvent
/*    */   extends PlayerEvent {
/*    */   private Location respawnLocation;
/*    */   
/*    */   public void setRespawnLocation(Location respawnLocation) {
/* 13 */     this.respawnLocation = respawnLocation; } public Location getRespawnLocation() {
/* 14 */     return this.respawnLocation;
/*    */   }
/*    */   
/*    */   public PlayerRealRespawnEvent(@NonNull Player player, Location respawnLocation) {
/* 18 */     super(player); if (player == null)
/* 19 */       throw new NullPointerException("player is marked non-null but is null");  this.respawnLocation = respawnLocation;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/event/PlayerRealRespawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
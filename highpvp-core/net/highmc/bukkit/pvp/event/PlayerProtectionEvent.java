/*    */ package net.highmc.bukkit.pvp.event;
/*    */ 
/*    */ import lombok.NonNull;
/*    */ import net.highmc.bukkit.event.PlayerEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlayerProtectionEvent
/*    */   extends PlayerEvent
/*    */ {
/*    */   private boolean newState;
/*    */   
/*    */   public PlayerProtectionEvent(@NonNull Player player, boolean newState) {
/* 13 */     super(player); if (player == null)
/* 14 */       throw new NullPointerException("player is marked non-null but is null");  this.newState = newState;
/*    */   }
/*    */   
/*    */   public boolean getNewState() {
/* 18 */     return this.newState;
/*    */   }
/*    */   
/*    */   public boolean getOldState() {
/* 22 */     return !this.newState;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/event/PlayerProtectionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
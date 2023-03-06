/*    */ package net.highmc.bukkit.pvp.arena.event.gladiator;
/*    */ 
/*    */ import net.highmc.bukkit.event.PlayerCancellableEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class GladiatorScapeEvent
/*    */   extends PlayerCancellableEvent {
/*    */   private Player target;
/*    */   
/*    */   public Player getTarget() {
/* 11 */     return this.target;
/*    */   }
/*    */   public GladiatorScapeEvent(Player player, Player target) {
/* 14 */     super(player);
/* 15 */     this.target = target;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/event/gladiator/GladiatorScapeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
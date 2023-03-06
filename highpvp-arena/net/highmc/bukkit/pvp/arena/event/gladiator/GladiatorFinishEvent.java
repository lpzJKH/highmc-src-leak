/*    */ package net.highmc.bukkit.pvp.arena.event.gladiator;
/*    */ 
/*    */ import net.highmc.bukkit.event.PlayerCancellableEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GladiatorFinishEvent
/*    */   extends PlayerCancellableEvent
/*    */ {
/*    */   public GladiatorFinishEvent(Player challenger, Player challenged) {
/* 12 */     super(challenger);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/event/gladiator/GladiatorFinishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
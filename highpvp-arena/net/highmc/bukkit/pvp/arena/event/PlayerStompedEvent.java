/*    */ package net.highmc.bukkit.pvp.arena.event;
/*    */ 
/*    */ import net.highmc.bukkit.event.PlayerCancellableEvent;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlayerStompedEvent
/*    */   extends PlayerCancellableEvent {
/*    */   private Player stomper;
/*    */   
/*    */   public Player getStomper() {
/* 11 */     return this.stomper;
/*    */   }
/*    */   public PlayerStompedEvent(Player stomper, Player stomped) {
/* 14 */     super(stomped);
/* 15 */     this.stomper = stomper;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/event/PlayerStompedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
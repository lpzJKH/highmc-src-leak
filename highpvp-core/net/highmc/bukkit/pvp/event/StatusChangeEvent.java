/*    */ package net.highmc.bukkit.pvp.event;
/*    */ 
/*    */ import lombok.NonNull;
/*    */ import net.highmc.bukkit.event.PlayerEvent;
/*    */ import net.highmc.member.status.Status;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class StatusChangeEvent
/*    */   extends PlayerEvent {
/*    */   private Status status;
/*    */   
/*    */   public Status getStatus() {
/* 13 */     return this.status;
/*    */   }
/*    */   public StatusChangeEvent(@NonNull Player player, Status status) {
/* 16 */     super(player); if (player == null)
/* 17 */       throw new NullPointerException("player is marked non-null but is null");  this.status = status;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/event/StatusChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
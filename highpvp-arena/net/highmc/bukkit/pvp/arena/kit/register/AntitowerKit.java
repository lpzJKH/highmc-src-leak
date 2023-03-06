/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.pvp.arena.event.PlayerStompedEvent;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AntitowerKit
/*    */   extends Kit
/*    */ {
/*    */   public AntitowerKit() {
/* 17 */     super("Antitower", "Não seja stompado", Material.GOLD_HELMET, 22000, new ArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerStomped(PlayerStompedEvent event) {
/* 23 */     if (hasAbility(event.getPlayer())) {
/* 24 */       event.setCancelled(true);
/* 25 */       event.getPlayer().damage(4.0D);
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityDamage(EntityDamageEvent event) {
/* 31 */     if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL && 
/* 32 */       hasAbility((Player)event.getEntity()))
/* 33 */       event.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/AntitowerKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
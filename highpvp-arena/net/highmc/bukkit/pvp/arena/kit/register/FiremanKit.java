/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiremanKit
/*    */   extends Kit
/*    */ {
/*    */   public FiremanKit() {
/* 16 */     super("Fireman", "Não tome dano para fogo nem lava", Material.LAVA_BUCKET, 12000, new ArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityDamage(EntityDamageEvent event) {
/* 22 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 25 */     Player player = (Player)event.getEntity();
/*    */     
/* 27 */     if (!hasAbility(player)) {
/*    */       return;
/*    */     }
/* 30 */     if (event.getCause() == EntityDamageEvent.DamageCause.LAVA || event.getCause() == EntityDamageEvent.DamageCause.FIRE || event
/* 31 */       .getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)
/* 32 */       event.setCancelled(true); 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/FiremanKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
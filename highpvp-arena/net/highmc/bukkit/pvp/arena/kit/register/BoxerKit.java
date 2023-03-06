/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ 
/*    */ 
/*    */ public class BoxerKit
/*    */   extends Kit
/*    */ {
/*    */   public BoxerKit() {
/* 15 */     super("Boxer", "Vire um boxeador e esteja acustumado a receber pancadas e a revida-las", Material.STONE_SWORD, 12000, new ArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onBoxer(EntityDamageByEntityEvent event) {
/* 21 */     if (!(event.getDamager() instanceof Player)) {
/*    */       return;
/*    */     }
/* 24 */     Player damager = (Player)event.getDamager();
/*    */     
/* 26 */     if (!hasAbility(damager)) {
/*    */       return;
/*    */     }
/* 29 */     if (damager.getItemInHand().getType() == Material.AIR) {
/* 30 */       event.setDamage(event.getDamage() + 2.0D);
/*    */     }
/*    */   }
/*    */   
/*    */   public void onSnail(EntityDamageByEntityEvent event) {
/* 35 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 38 */     Player damaged = (Player)event.getEntity();
/*    */     
/* 40 */     if (!hasAbility(damaged)) {
/*    */       return;
/*    */     }
/* 43 */     if (event.getDamage() - 1.0D >= 1.0D) {
/* 44 */       event.setDamage(event.getDamage() - 1.0D);
/*    */     } else {
/* 46 */       event.setDamage(1.0D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/BoxerKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
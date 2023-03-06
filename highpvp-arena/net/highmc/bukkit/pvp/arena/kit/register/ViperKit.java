/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ public class ViperKit
/*    */   extends Kit
/*    */ {
/*    */   public ViperKit() {
/* 18 */     super("Viper", "Envenene seus inimigos ao encosta-los", Material.SPIDER_EYE, 12250, new ArrayList());
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onSnail(EntityDamageByEntityEvent event) {
/* 23 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 26 */     if (!(event.getDamager() instanceof Player)) {
/*    */       return;
/*    */     }
/* 29 */     Player damager = (Player)event.getDamager();
/*    */     
/* 31 */     if (hasAbility(damager)) {
/* 32 */       Random r = new Random();
/* 33 */       Player damaged = (Player)event.getEntity();
/*    */       
/* 35 */       if (damaged instanceof Player && 
/* 36 */         r.nextInt(4) == 0)
/* 37 */         damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/ViperKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
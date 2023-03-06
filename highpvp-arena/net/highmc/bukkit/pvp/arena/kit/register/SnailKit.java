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
/*    */ public class SnailKit
/*    */   extends Kit
/*    */ {
/*    */   public SnailKit() {
/* 18 */     super("Snail", "Deixe seus inimigos mais lentos ao encosta-los", Material.WEB, 12500, new ArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onSnail(EntityDamageByEntityEvent event) {
/* 24 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 27 */     if (!(event.getDamager() instanceof Player)) {
/*    */       return;
/*    */     }
/* 30 */     Player damager = (Player)event.getDamager();
/*    */     
/* 32 */     if (!hasAbility(damager)) {
/*    */       return;
/*    */     }
/* 35 */     Random r = new Random();
/* 36 */     Player damaged = (Player)event.getEntity();
/*    */     
/* 38 */     if (damaged instanceof Player && 
/* 39 */       r.nextInt(4) == 0)
/* 40 */       damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1)); 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/SnailKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
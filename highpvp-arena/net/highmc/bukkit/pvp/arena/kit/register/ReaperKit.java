/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Random;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ 
/*    */ public class ReaperKit
/*    */   extends Kit
/*    */ {
/*    */   public ReaperKit() {
/* 20 */     super("Reaper", "Ceife a alma de seus inimigos por alguns segundos com a sua enxada", Material.WOOD_HOE, 11250, 
/* 21 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aReaper").type(Material.WOOD_HOE).build() }));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onSnail(EntityDamageByEntityEvent event) {
/* 26 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 29 */     if (!(event.getDamager() instanceof Player)) {
/*    */       return;
/*    */     }
/* 32 */     Player damager = (Player)event.getDamager();
/*    */     
/* 34 */     if (hasAbility(damager)) {
/* 35 */       ItemStack item = damager.getItemInHand();
/*    */       
/* 37 */       if (isAbilityItem(item)) {
/* 38 */         event.setCancelled(true);
/* 39 */         damager.updateInventory();
/* 40 */         Random r = new Random();
/* 41 */         Player damaged = (Player)event.getEntity();
/*    */         
/* 43 */         if (damaged instanceof Player && 
/* 44 */           r.nextInt(4) == 0)
/* 45 */           damaged.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 4)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/ReaperKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
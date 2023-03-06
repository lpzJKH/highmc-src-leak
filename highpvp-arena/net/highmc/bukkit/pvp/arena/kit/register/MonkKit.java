/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Random;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class MonkKit
/*    */   extends Kit
/*    */ {
/*    */   public MonkKit() {
/* 19 */     super("Monk", "Bagunce o inventário de seus inimigos", Material.BLAZE_ROD, 11500, 
/* 20 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aMonk").type(Material.BLAZE_ROD).build() }));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onInteractEntity(PlayerInteractEntityEvent e) {
/* 25 */     if (!(e.getRightClicked() instanceof Player)) {
/*    */       return;
/*    */     }
/* 28 */     Player player = e.getPlayer();
/*    */     
/* 30 */     if (!hasAbility(player)) {
/*    */       return;
/*    */     }
/* 33 */     ItemStack item = player.getItemInHand();
/*    */     
/* 35 */     if (!isAbilityItem(item)) {
/*    */       return;
/*    */     }
/* 38 */     Player clicked = (Player)e.getRightClicked();
/*    */     
/* 40 */     if (GameMain.getInstance().getGamerManager().getGamer(clicked.getUniqueId()).isSpawnProtection()) {
/*    */       return;
/*    */     }
/* 43 */     if (!isCooldown(player)) {
/* 44 */       addCooldown(player, 15L);
/*    */       
/* 46 */       int randomN = (new Random()).nextInt(36);
/*    */       
/* 48 */       ItemStack atual = (clicked.getItemInHand() != null) ? clicked.getItemInHand().clone() : null;
/*    */       
/* 50 */       ItemStack random = (clicked.getInventory().getItem(randomN) != null) ? clicked.getInventory().getItem(randomN).clone() : null;
/*    */ 
/*    */       
/* 53 */       if (random == null) {
/* 54 */         clicked.getInventory().setItem(randomN, atual);
/* 55 */         clicked.setItemInHand(null);
/*    */       } else {
/* 57 */         clicked.getInventory().setItem(randomN, atual);
/* 58 */         clicked.getInventory().setItemInHand(random);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/MonkKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
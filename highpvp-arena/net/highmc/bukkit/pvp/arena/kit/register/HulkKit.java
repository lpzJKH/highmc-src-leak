/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.event.player.PlayerDamagePlayerEvent;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class HulkKit
/*    */   extends Kit
/*    */ {
/*    */   public HulkKit() {
/* 20 */     super("Hulk", "Pegue seus inimigos em suas costas e lançe-os para longe", Material.SADDLE, 14000, new ArrayList());
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
/* 26 */     Player player = event.getPlayer();
/*    */     
/* 28 */     if (hasAbility(player) && 
/* 29 */       event.getRightClicked() instanceof Player) {
/* 30 */       Player clicked = (Player)event.getRightClicked();
/*    */       
/* 32 */       if (!GameMain.getInstance().getGamerManager().getGamer(clicked.getUniqueId()).isSpawnProtection() && 
/* 33 */         !player.isInsideVehicle() && !clicked.isInsideVehicle() && player
/* 34 */         .getItemInHand().getType() == Material.AIR) {
/*    */         
/* 36 */         if (isCooldown(player)) {
/*    */           return;
/*    */         }
/* 39 */         addCooldown(player, 12L);
/* 40 */         player.setPassenger((Entity)clicked);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerDamagePlayer(PlayerDamagePlayerEvent event) {
/* 47 */     final Player player = event.getPlayer();
/* 48 */     Player hulk = event.getDamager();
/*    */     
/* 50 */     if (hulk.getPassenger() != null && hulk.getPassenger() == player && hasAbility(hulk) && hulk
/* 51 */       .getPassenger() == player) {
/* 52 */       event.setCancelled(true);
/* 53 */       player.setSneaking(true);
/*    */       
/* 55 */       Vector v = hulk.getEyeLocation().getDirection().multiply(1.6F);
/* 56 */       v.setY(0.6D);
/* 57 */       player.setVelocity(v);
/*    */       
/* 59 */       (new BukkitRunnable()
/*    */         {
/*    */           public void run()
/*    */           {
/* 63 */             player.setSneaking(false);
/*    */           }
/* 65 */         }).runTaskLater((Plugin)GameMain.getInstance(), 10L);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/HulkKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
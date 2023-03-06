/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.event.player.PlayerDamagePlayerEvent;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class AnchorKit
/*    */   extends Kit
/*    */ {
/*    */   public AnchorKit() {
/* 20 */     super("Anchor", "Se prenda ao chão e não saia dele", Material.ANVIL, 9000, new ArrayList());
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */   public void onPlayerDamagePlayer(PlayerDamagePlayerEvent event) {
/* 25 */     Player player = event.getPlayer();
/* 26 */     Player damager = event.getDamager();
/*    */     
/* 28 */     if (hasAbility(player) || hasAbility(damager)) {
/* 29 */       player.getWorld().playSound(player.getLocation(), Sound.ANVIL_LAND, 0.15F, 1.0F);
/*    */       
/* 31 */       velocityPlayer(player);
/* 32 */       velocityPlayer(damager);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void velocityPlayer(final Player player) {
/* 37 */     player.setVelocity(new Vector(0, 0, 0));
/*    */     
/* 39 */     (new BukkitRunnable() {
/*    */         public void run() {
/* 41 */           player.setVelocity(new Vector(0, 0, 0));
/*    */         }
/* 43 */       }).runTaskLater((Plugin)GameMain.getInstance(), 1L);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/AnchorKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
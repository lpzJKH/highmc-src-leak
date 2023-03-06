/*    */ package net.highmc.bukkit.pvp.arena.listener;
/*    */ 
/*    */ import net.highmc.bukkit.event.player.PlayerMoveUpdateEvent;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class LauncherListener implements Listener {
/*    */   @EventHandler
/*    */   public void onPlayerMoveUpdate(PlayerMoveUpdateEvent event) {
/* 18 */     Player player = event.getPlayer();
/* 19 */     Material type = event.getTo().getBlock().getRelative(BlockFace.DOWN).getType();
/*    */     
/* 21 */     boolean noFall = false;
/*    */     
/* 23 */     if (type == Material.DIAMOND_BLOCK) {
/* 24 */       player.setVelocity(player.getLocation().getDirection().multiply(0).setY(2.5D));
/* 25 */       noFall = true;
/* 26 */     } else if (type == Material.SPONGE) {
/* 27 */       player.setVelocity(player.getLocation().getDirection().multiply(0).setY(4));
/* 28 */       noFall = true;
/*    */     } 
/*    */     
/* 31 */     if (noFall) {
/* 32 */       player.playSound(player.getLocation(), Sound.LEVEL_UP, 6.0F, 1.0F);
/* 33 */       player.setMetadata("nofall", (MetadataValue)new FixedMetadataValue(
/* 34 */             (Plugin)GameMain.getInstance(), Long.valueOf(System.currentTimeMillis() + 5000L)));
/* 35 */       player.setMetadata("anticheat-bypass", (MetadataValue)new FixedMetadataValue(
/* 36 */             (Plugin)GameMain.getInstance(), Long.valueOf(System.currentTimeMillis() + 5000L)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/listener/LauncherListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.highmc.bukkit.pvp.listener;
/*    */ 
/*    */ import net.highmc.bukkit.event.player.PlayerCommandEvent;
/*    */ import net.highmc.bukkit.event.player.PlayerDamagePlayerEvent;
/*    */ import net.highmc.bukkit.pvp.GameAPI;
/*    */ import net.highmc.bukkit.pvp.GameConst;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class DamageListener
/*    */   implements Listener {
/*    */   @EventHandler
/*    */   public void onPlayerDamagePlayer(PlayerDamagePlayerEvent event) {
/* 19 */     Player player = event.getPlayer();
/* 20 */     Player damager = event.getDamager();
/*    */     
/* 22 */     if (GameAPI.getInstance().getGamerManager().getGamer(damager.getUniqueId()).isSpawnProtection() || 
/* 23 */       GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId()).isSpawnProtection()) {
/*    */       return;
/*    */     }
/* 26 */     player.setMetadata("combatlog", 
/* 27 */         GameAPI.getInstance().createMeta(Long.valueOf(System.currentTimeMillis() + 12000L)));
/* 28 */     damager.setMetadata("combatlog", 
/* 29 */         GameAPI.getInstance().createMeta(Long.valueOf(System.currentTimeMillis() + 12000L)));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerQuitEvent event) {
/* 34 */     Player player = event.getPlayer();
/*    */     
/* 36 */     if (isInCombatlog(player))
/* 37 */       player.damage(2.147483647E9D); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerDeathEvent event) {
/* 42 */     Player player = event.getEntity();
/*    */     
/* 44 */     if (player.getKiller() instanceof Player) {
/* 45 */       player.getKiller().removeMetadata("combatlog", (Plugin)GameAPI.getInstance());
/*    */     }
/* 47 */     player.removeMetadata("combatlog", (Plugin)GameAPI.getInstance());
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerCommand(PlayerCommandEvent event) {
/* 52 */     Player player = event.getPlayer();
/*    */     
/* 54 */     if (isInCombatlog(player) && GameConst.BLOCKED_COMMANDS.contains(event.getCommandLabel().toLowerCase())) {
/* 55 */       event.setCancelled(true);
/* 56 */       player.sendMessage("§cVocê está em combate, aguarde para executar esse comando.");
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean isInCombatlog(Player player) {
/* 61 */     if (player.hasMetadata("combatlog")) {
/*    */       
/* 63 */       MetadataValue metadataValue = player.getMetadata("combatlog").stream().findFirst().orElse(null);
/* 64 */       long expire = metadataValue.asLong();
/*    */       
/* 66 */       return (expire > System.currentTimeMillis());
/*    */     } 
/*    */     
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/listener/DamageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
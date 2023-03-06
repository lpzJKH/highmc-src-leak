/*    */ package net.highmc.bukkit.pvp.arena.listener;
/*    */ 
/*    */ import net.highmc.CommonPlugin;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.event.PlayerSelectedKitEvent;
/*    */ import net.highmc.bukkit.pvp.arena.gamer.Gamer;
/*    */ import net.highmc.bukkit.pvp.event.PlayerProtectionEvent;
/*    */ import net.highmc.bukkit.pvp.event.StatusChangeEvent;
/*    */ import net.highmc.bukkit.utils.scoreboard.ScoreHelper;
/*    */ import net.highmc.bukkit.utils.scoreboard.Scoreboard;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.event.player.PlayerJoinEvent;
/*    */ 
/*    */ public class ScoreboardListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onStatusChange(StatusChangeEvent event) {
/* 23 */     updateScoreboard(event.getPlayer());
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPlayerJoin(PlayerJoinEvent event) {
/* 28 */     handleScoreboard(event.getPlayer());
/* 29 */     updateScoreboard(event.getPlayer());
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onGamerChange(PlayerSelectedKitEvent event) {
/* 34 */     updateScoreboard(event.getPlayer());
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPlayerDeath(PlayerDeathEvent event) {
/* 39 */     updateScoreboard(event.getEntity());
/*    */     
/* 41 */     if (event.getEntity().getKiller() != null)
/* 42 */       updateScoreboard(event.getEntity().getKiller()); 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPlayerProtection(PlayerProtectionEvent event) {
/* 47 */     updateScoreboard(event.getPlayer());
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPlayerSelectedKit(PlayerSelectedKitEvent event) {
/* 52 */     updateScoreboard(event.getPlayer());
/*    */   }
/*    */   
/*    */   private void handleScoreboard(Player player) {
/* 56 */     Scoreboard scoreboard = new Scoreboard(player, "§6§lARENA");
/*    */     
/* 58 */     scoreboard.add(9, "§e");
/* 59 */     scoreboard.add(8, "§fKills: §70");
/* 60 */     scoreboard.add(7, "§fDeaths: §70");
/* 61 */     scoreboard.add(6, "§fKillstreak: §70");
/* 62 */     scoreboard.add(5, "§e");
/* 63 */     scoreboard.add(4, "§fKit 1: §aNenhum");
/* 64 */     scoreboard.add(3, "§fKit 2: §aNenhum");
/* 65 */     scoreboard.add(2, "§e");
/* 66 */     scoreboard.add(1, "§e" + CommonPlugin.getInstance().getPluginInfo().getWebsite());
/*    */     
/* 68 */     ScoreHelper.getInstance().setScoreboard(player, scoreboard);
/*    */   }
/*    */   
/*    */   private void updateScoreboard(Player player) {
/* 72 */     ScoreHelper.getInstance().updateScoreboard(player, 8, "§fKills: §70");
/* 73 */     ScoreHelper.getInstance().updateScoreboard(player, 7, "§fDeaths: §70");
/* 74 */     ScoreHelper.getInstance().updateScoreboard(player, 6, "§fKillstreak: §70");
/*    */     
/* 76 */     Gamer gamer = (Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class);
/*    */     
/* 78 */     ScoreHelper.getInstance().updateScoreboard(player, 4, "§fKit 1: §a" + gamer.getPrimary());
/* 79 */     ScoreHelper.getInstance().updateScoreboard(player, 3, "§fKit 2: §a" + gamer.getSecondary());
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/listener/ScoreboardListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
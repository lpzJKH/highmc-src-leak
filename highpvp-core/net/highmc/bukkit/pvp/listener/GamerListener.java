/*    */ package net.highmc.bukkit.pvp.listener;
/*    */ 
/*    */ import net.highmc.CommonPlugin;
/*    */ import net.highmc.bukkit.pvp.GameAPI;
/*    */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*    */ import net.highmc.member.status.StatusType;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
/*    */ import org.bukkit.event.player.PlayerLoginEvent;
/*    */ 
/*    */ public class GamerListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
/* 18 */     if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
/*    */       return;
/*    */     }
/* 21 */     Gamer gamer = GameAPI.getInstance().getGamerData().loadGamer(event.getUniqueId(), 
/* 22 */         GameAPI.getInstance().getGamerClass());
/*    */     
/* 24 */     if (gamer == null) {
/* 25 */       event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, 
/* 26 */           CommonPlugin.getInstance().getPluginInfo().translate("gamer-not-loaded") + " [0]");
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     GameAPI.getInstance().getGamerManager().loadGamer(gamer);
/* 31 */     CommonPlugin.getInstance().getStatusManager().loadStatus(gamer.getUniqueId(), StatusType.PVP);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGHEST)
/*    */   public void onPlayerLogin(PlayerLoginEvent event) {
/* 36 */     if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
/*    */       return;
/*    */     }
/* 39 */     if (GameAPI.getInstance().getGamerManager().getGamer(event.getPlayer().getUniqueId()) == null) {
/* 40 */       event.disallow(PlayerLoginEvent.Result.KICK_OTHER, 
/* 41 */           CommonPlugin.getInstance().getPluginInfo().translate("gamer-not-loaded") + " [1]");
/*    */       return;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/listener/GamerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
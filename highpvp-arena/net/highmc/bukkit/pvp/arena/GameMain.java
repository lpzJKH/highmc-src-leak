/*    */ package net.highmc.bukkit.pvp.arena;
/*    */ import net.highmc.bukkit.pvp.GameAPI;
/*    */ import net.highmc.bukkit.pvp.arena.gamer.Gamer;
/*    */ import net.highmc.bukkit.pvp.arena.listener.LauncherListener;
/*    */ import net.highmc.bukkit.pvp.arena.listener.PlayerListener;
/*    */ import net.highmc.bukkit.pvp.arena.listener.ScoreboardListener;
/*    */ import net.highmc.bukkit.pvp.arena.manager.KitManager;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class GameMain extends GameAPI {
/*    */   private static GameMain instance;
/*    */   
/*    */   public static GameMain getInstance() {
/* 16 */     return instance;
/*    */   } private KitManager kitManager;
/*    */   public KitManager getKitManager() {
/* 19 */     return this.kitManager;
/*    */   }
/*    */   
/*    */   public void onLoad() {
/* 23 */     super.onLoad();
/* 24 */     instance = this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 29 */     super.onEnable();
/* 30 */     setGamerClass(Gamer.class);
/* 31 */     setDropItems(true);
/*    */     
/* 33 */     this.kitManager = new KitManager();
/*    */     
/* 35 */     Bukkit.getPluginManager().registerEvents((Listener)new LauncherListener(), (Plugin)this);
/* 36 */     Bukkit.getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
/* 37 */     Bukkit.getPluginManager().registerEvents((Listener)new ScoreboardListener(), (Plugin)this);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/GameMain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
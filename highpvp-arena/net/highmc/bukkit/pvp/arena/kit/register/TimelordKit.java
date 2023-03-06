/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.entity.PlayerDeathEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class TimelordKit
/*    */   extends Kit
/*    */ {
/*    */   private static final int RADIUS = 5;
/* 24 */   private Map<Player, Long> timelordList = new HashMap<>();
/*    */   
/*    */   public TimelordKit() {
/* 27 */     super("Timelord", "Pare o tempo com seu relógio", Material.WATCH, 11700, 
/* 28 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aTimelord").type(Material.WATCH).build() }));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent event) {
/* 33 */     if (hasAbility(event.getPlayer()) && isAbilityItem(event.getPlayer().getItemInHand())) {
/* 34 */       if (isCooldown(event.getPlayer())) {
/*    */         return;
/*    */       }
/* 37 */       for (Player game : Bukkit.getOnlinePlayers()) {
/* 38 */         if (game == event.getPlayer()) {
/*    */           continue;
/*    */         }
/* 41 */         if (GameMain.getInstance().getVanishManager().isPlayerVanished(game.getUniqueId())) {
/*    */           continue;
/*    */         }
/* 44 */         double distance = event.getPlayer().getLocation().distance(game.getPlayer().getLocation());
/*    */         
/* 46 */         if (distance <= 5.0D) {
/* 47 */           this.timelordList.put(game, Long.valueOf(System.currentTimeMillis() + 4000L));
/*    */         }
/*    */       } 
/*    */       
/* 51 */       event.setCancelled(true);
/* 52 */       addCooldown(event.getPlayer(), 45L);
/* 53 */       event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.WITHER_SHOOT, 1.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayeDeath(PlayerDeathEvent event) {
/* 59 */     if (this.timelordList.containsKey(event.getEntity()))
/* 60 */       this.timelordList.remove(event.getEntity()); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerQuit(PlayerQuitEvent event) {
/* 65 */     if (this.timelordList.containsKey(event.getPlayer()))
/* 66 */       this.timelordList.remove(event.getPlayer()); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 71 */     if (this.timelordList.containsKey(event.getPlayer()))
/* 72 */       if (((Long)this.timelordList.get(event.getPlayer())).longValue() > System.currentTimeMillis()) {
/* 73 */         if (event.getTo().getX() != event.getFrom().getX() || event.getTo().getZ() != event.getFrom().getZ())
/* 74 */           event.setCancelled(true); 
/*    */       } else {
/* 76 */         this.timelordList.remove(event.getPlayer());
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/TimelordKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
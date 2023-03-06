/*     */ package net.highmc.bukkit.pvp.listener;
/*     */ 
/*     */ import net.highmc.CommonPlugin;
/*     */ import net.highmc.bukkit.BukkitCommon;
/*     */ import net.highmc.bukkit.member.BukkitMember;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockBurnEvent;
/*     */ import org.bukkit.event.block.BlockExplodeEvent;
/*     */ import org.bukkit.event.block.BlockIgniteEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.block.BlockSpreadEvent;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ import org.bukkit.event.entity.ItemSpawnEvent;
/*     */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class WorldListener
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void onBlockBreak(BlockBreakEvent event) {
/*  29 */     BukkitMember member = (BukkitMember)CommonPlugin.getInstance().getMemberManager().getMember(event.getPlayer().getUniqueId(), BukkitMember.class);
/*     */ 
/*     */     
/*  32 */     event.setCancelled((member == null) ? true : (!member.isBuildEnabled()));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockPlace(BlockPlaceEvent event) {
/*  37 */     BukkitMember member = (BukkitMember)CommonPlugin.getInstance().getMemberManager().getMember(event.getPlayer().getUniqueId(), BukkitMember.class);
/*     */ 
/*     */     
/*  40 */     event.setCancelled((member == null) ? true : (!member.isBuildEnabled()));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
/*  45 */     BukkitMember member = (BukkitMember)CommonPlugin.getInstance().getMemberManager().getMember(event.getPlayer().getUniqueId(), BukkitMember.class);
/*     */ 
/*     */     
/*  48 */     event.setCancelled((member == null) ? true : (!member.isBuildEnabled()));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onCreatureSpawn(CreatureSpawnEvent event) {
/*  53 */     event.setCancelled((event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM));
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityExplode(EntityExplodeEvent event) {
/*  58 */     event.blockList().clear();
/*  59 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockExplode(BlockExplodeEvent event) {
/*  64 */     event.blockList().clear();
/*  65 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockBurn(BlockBurnEvent event) {
/*  70 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockIgnite(BlockIgniteEvent event) {
/*  75 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onBlockSpread(BlockSpreadEvent event) {
/*  80 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onFoodLevelChange(FoodLevelChangeEvent event) {
/*  85 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDropItem(PlayerDropItemEvent event) {
/*  90 */     if (event.getItemDrop().getItemStack().getType().name().contains("SWORD"))
/*  91 */       event.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onItemSpawn(final ItemSpawnEvent event) {
/*  96 */     (new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 100 */           event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.NOTE, 1);
/* 101 */           event.getEntity().remove();
/*     */         }
/* 103 */       }).runTaskLater((Plugin)BukkitCommon.getInstance(), 60L);
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/listener/WorldListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
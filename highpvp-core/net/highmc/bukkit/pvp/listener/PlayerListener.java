/*     */ package net.highmc.bukkit.pvp.listener;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.highmc.CommonPlugin;
/*     */ import net.highmc.bukkit.BukkitCommon;
/*     */ import net.highmc.bukkit.event.player.PlayerDamagePlayerEvent;
/*     */ import net.highmc.bukkit.event.player.PlayerMoveUpdateEvent;
/*     */ import net.highmc.bukkit.pvp.GameAPI;
/*     */ import net.highmc.bukkit.pvp.event.PlayerProtectionEvent;
/*     */ import net.highmc.bukkit.pvp.event.PlayerRealRespawnEvent;
/*     */ import net.highmc.bukkit.pvp.event.PlayerSpawnEvent;
/*     */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*     */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*     */ import net.highmc.bukkit.utils.player.PlayerHelper;
/*     */ import net.highmc.member.status.Status;
/*     */ import net.highmc.member.status.StatusType;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ 
/*     */ public class PlayerListener implements Listener {
/*     */   @EventHandler(priority = EventPriority.LOW)
/*     */   public void onPlayerJoin(PlayerJoinEvent event) {
/*  41 */     handlePlayer(event.getPlayer());
/*  42 */     event.getPlayer().teleport(BukkitCommon.getInstance().getLocationManager().getLocation("spawn"));
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void onPlayerSpawn(PlayerSpawnEvent event) {
/*  47 */     handlePlayer(event.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onEntityRegain(EntityRegainHealthEvent event) {
/*  52 */     event.setCancelled(true);
/*     */   }
/*     */   
/*     */   private void handlePlayer(Player player) {
/*  56 */     player.getInventory().clear();
/*  57 */     player.getInventory().setArmorContents(new ItemStack[4]);
/*  58 */     player.setGameMode(GameMode.SURVIVAL);
/*     */     
/*  60 */     player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
/*     */     
/*  62 */     player.setHealth(20.0D);
/*  63 */     player.setFoodLevel(20);
/*     */     
/*  65 */     GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId()).setSpawnProtection(true);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerDeath(PlayerDeathEvent event) {
/*  70 */     event.setDeathMessage(null);
/*  71 */     final Player player = event.getEntity();
/*     */     
/*  73 */     List<ItemStack> items = new ArrayList<>(event.getDrops());
/*     */     
/*  75 */     if (GameAPI.getInstance().isDropItems()) {
/*  76 */       items.forEach(item -> {
/*     */           
/*     */           });
/*     */     }
/*     */     
/*  81 */     if (player.getKiller() instanceof Player) {
/*  82 */       Player killer = player.getKiller();
/*     */       
/*  84 */       for (ItemStack itemStack : killer.getInventory().getArmorContents()) {
/*  85 */         if (itemStack != null) {
/*  86 */           itemStack.setDurability((short)0);
/*     */         }
/*     */       } 
/*     */       
/*  90 */       Status status1 = CommonPlugin.getInstance().getStatusManager().loadStatus(killer.getUniqueId(), StatusType.PVP);
/*     */ 
/*     */       
/*  93 */       status1.addInteger("kills", 1);
/*  94 */       status1.addInteger("killstreak", 1);
/*  95 */       status1.setInteger("killstreak-max", 
/*  96 */           Math.max(status1.getInteger("killstreak-max"), status1.getInteger("killstreak")));
/*  97 */       status1.save();
/*  98 */       killer.sendMessage("§aVocê matou o jogador " + player.getName() + ".");
/*  99 */       player.sendMessage("§cVocê foi morto pelo jogador " + killer.getName() + ".");
/*     */     } else {
/* 101 */       player.sendMessage("§cVocê morreu.");
/*     */     } 
/* 103 */     Status status = CommonPlugin.getInstance().getStatusManager().loadStatus(player.getUniqueId(), StatusType.PVP);
/*     */     
/* 105 */     status.addInteger("deaths", 1);
/* 106 */     status.setInteger("killstreak", 0);
/* 107 */     status.save();
/*     */     
/* 109 */     event.getDrops().clear();
/*     */     
/* 111 */     player.getInventory().clear();
/* 112 */     player.getInventory().setArmorContents(new ItemStack[4]);
/* 113 */     player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
/*     */     
/* 115 */     (new BukkitRunnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 119 */           player.spigot().respawn();
/* 120 */           player.setFallDistance(-1.0F);
/*     */ 
/*     */           
/* 123 */           PlayerRealRespawnEvent playerRespawnEvent = new PlayerRealRespawnEvent(player, BukkitCommon.getInstance().getLocationManager().getLocation("spawn"));
/* 124 */           Bukkit.getPluginManager().callEvent((Event)playerRespawnEvent);
/* 125 */           player.teleport(playerRespawnEvent.getRespawnLocation());
/*     */           
/* 127 */           GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId()).setSpawnProtection(true);
/* 128 */           Bukkit.getPluginManager().callEvent((Event)new PlayerProtectionEvent(player, true));
/*     */         }
/* 130 */       }).runTaskLater((Plugin)GameAPI.getInstance(), 7L);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void onPlayerInteract(PlayerInteractEvent event) {
/* 135 */     Player player = event.getPlayer();
/* 136 */     ItemStack itemStack = player.getItemInHand();
/*     */     
/* 138 */     if (itemStack.getType() == Material.MUSHROOM_SOUP && event.getAction().name().contains("RIGHT") && player
/* 139 */       .getHealth() < player.getMaxHealth()) {
/* 140 */       event.setCancelled(true);
/* 141 */       int restores = 7;
/*     */       
/* 143 */       player.setHealth(Math.min(player.getHealth() + restores, player.getMaxHealth()));
/* 144 */       player.setItemInHand((new ItemBuilder()).type(Material.BOWL).build());
/*     */       
/*     */       return;
/*     */     } 
/* 148 */     if (event.hasBlock() && event.getClickedBlock().getType() == Material.TRAP_DOOR)
/* 149 */       event.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerRespawn(PlayerRespawnEvent event) {
/* 154 */     Player player = event.getPlayer();
/*     */     
/* 156 */     player.getInventory().clear();
/* 157 */     player.getInventory().setArmorContents(new ItemStack[4]);
/* 158 */     player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
/*     */ 
/*     */     
/* 161 */     PlayerRealRespawnEvent playerRespawnEvent = new PlayerRealRespawnEvent(player, BukkitCommon.getInstance().getLocationManager().getLocation("spawn"));
/* 162 */     Bukkit.getPluginManager().callEvent((Event)playerRespawnEvent);
/* 163 */     player.teleport(playerRespawnEvent.getRespawnLocation());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
/*     */   public void onDamageDamage(EntityDamageEvent event) {
/* 168 */     if (!(event.getEntity() instanceof Player)) {
/*     */       return;
/*     */     }
/* 171 */     Player player = (Player)event.getEntity();
/* 172 */     Gamer gamer = GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId());
/*     */     
/* 174 */     if (gamer.hasSpawnProtection())
/* 175 */       event.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
/*     */   public void onPlayerDamagePlayer(PlayerDamagePlayerEvent event) {
/* 180 */     Gamer gamer = GameAPI.getInstance().getGamerManager().getGamer(event.getPlayer().getUniqueId());
/* 181 */     Gamer damager = GameAPI.getInstance().getGamerManager().getGamer(event.getDamager().getUniqueId());
/*     */     
/* 183 */     if (gamer.hasSpawnProtection() || damager.hasSpawnProtection()) {
/* 184 */       event.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 188 */     Player player = event.getPlayer();
/* 189 */     ItemStack itemStack = player.getItemInHand();
/*     */     
/* 191 */     if (itemStack != null && itemStack.getType().name().contains("SWORD"))
/* 192 */       itemStack.setDurability((short)0); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerMoveUpdate(PlayerMoveUpdateEvent event) {
/* 197 */     Player player = event.getPlayer();
/* 198 */     Gamer gamer = GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId());
/*     */     
/* 200 */     if (gamer.hasSpawnProtection() && distanceSquared(event.getTo())) {
/* 201 */       gamer.setSpawnProtection(false);
/* 202 */       Bukkit.getPluginManager().callEvent((Event)new PlayerProtectionEvent(player, false));
/* 203 */       player.sendMessage("§cVocê perdeu sua proteção de spawn.");
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerProtection(PlayerProtectionEvent event) {
/* 209 */     Player player = event.getPlayer();
/*     */     
/* 211 */     if (!event.getNewState() && player.getGameMode() == GameMode.CREATIVE)
/* 212 */       PlayerHelper.title(player, "§c§lATENÇÃO", "§fvocê está no criativo.", 10, 60, 10); 
/*     */   }
/*     */   
/*     */   public boolean distanceSquared(Location locationTo) {
/* 216 */     return distanceSquared(locationTo, GameAPI.getInstance().getProtectionRadius());
/*     */   }
/*     */   
/*     */   public boolean distanceSquared(Location locatioTo, double radius) {
/* 220 */     Location spawnLocation = BukkitCommon.getInstance().getLocationManager().getLocation("spawn");
/* 221 */     double distX = locatioTo.getX() - spawnLocation.getX();
/* 222 */     double distZ = locatioTo.getZ() - spawnLocation.getZ();
/*     */     
/* 224 */     double distance = distX * distX + distZ * distZ;
/* 225 */     return (distance > radius * radius);
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/listener/PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
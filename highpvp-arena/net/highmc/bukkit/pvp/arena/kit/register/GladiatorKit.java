/*     */ package net.highmc.bukkit.pvp.arena.kit.register;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.highmc.bukkit.event.UpdateEvent;
/*     */ import net.highmc.bukkit.event.player.PlayerDamagePlayerEvent;
/*     */ import net.highmc.bukkit.event.player.PlayerMoveUpdateEvent;
/*     */ import net.highmc.bukkit.pvp.GameAPI;
/*     */ import net.highmc.bukkit.pvp.arena.GameMain;
/*     */ import net.highmc.bukkit.pvp.arena.event.gladiator.ChallengeGladiatorEvent;
/*     */ import net.highmc.bukkit.pvp.arena.event.gladiator.GladiatorFinishEvent;
/*     */ import net.highmc.bukkit.pvp.arena.event.gladiator.GladiatorScapeEvent;
/*     */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*     */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockDamageEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class GladiatorKit
/*     */   extends Kit {
/*  46 */   private final GladiatorController gladiatorController = new GladiatorController();
/*     */   
/*     */   public GladiatorKit() {
/*  49 */     super("Gladiator", "Puxe os jogadores em uma jaula, onde ficará somente você e ele para tirarem x1", Material.IRON_FENCE, 22000, 
/*     */ 
/*     */         
/*  52 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aGladiator").type(Material.IRON_FENCE).build() }));
/*     */   }
/*     */   private static final double HEIGHT = 190.0D;
/*     */   @EventHandler
/*     */   public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
/*  57 */     if (!(event.getRightClicked() instanceof Player)) {
/*     */       return;
/*     */     }
/*  60 */     if (event.getPlayer().getItemInHand() == null) {
/*     */       return;
/*     */     }
/*  63 */     Player player = event.getPlayer();
/*     */     
/*  65 */     if (hasAbility(player) && isAbilityItem(player.getItemInHand())) {
/*  66 */       event.setCancelled(true);
/*     */       
/*  68 */       if (isCooldown(player)) {
/*     */         return;
/*     */       }
/*  71 */       Player target = (Player)event.getRightClicked();
/*     */       
/*  73 */       if (GameMain.getInstance().getGamerManager().getGamer(target.getUniqueId()).isSpawnProtection()) {
/*     */         return;
/*     */       }
/*  76 */       ChallengeGladiatorEvent challengeGladiatorEvent = new ChallengeGladiatorEvent(player, target);
/*     */       
/*  78 */       challengeGladiatorEvent
/*  79 */         .setCancelled((this.gladiatorController.isInFight(player) || this.gladiatorController.isInFight(target)));
/*  80 */       Bukkit.getPluginManager().callEvent((Event)challengeGladiatorEvent);
/*     */       
/*  82 */       if (!challengeGladiatorEvent.isCancelled()) {
/*  83 */         this.gladiatorController.sendGladiator(player, target);
/*  84 */         player.setMetadata("combatlog", 
/*  85 */             GameAPI.getInstance().createMeta(Long.valueOf(System.currentTimeMillis() + 12000L)));
/*  86 */         target.setMetadata("combatlog", 
/*  87 */             GameAPI.getInstance().createMeta(Long.valueOf(System.currentTimeMillis() + 12000L)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent e) {
/*  94 */     Player player = e.getPlayer();
/*     */     
/*  96 */     if (e.getAction() != Action.PHYSICAL && hasAbility(player) && isAbilityItem(e.getItem())) {
/*  97 */       player.updateInventory();
/*  98 */       e.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class GladiatorController
/*     */   {
/* 106 */     private int radius = 8;
/* 107 */     private int height = 12;
/*     */     
/*     */     private Map<Player, Gladiator> playerList;
/*     */     
/*     */     private List<Gladiator> gladiatorList;
/*     */     private List<Block> blockList;
/* 113 */     private GladiatorListener listener = new GladiatorListener();
/*     */     
/*     */     public GladiatorController() {
/* 116 */       this.playerList = new HashMap<>();
/* 117 */       this.gladiatorList = new ArrayList<>();
/* 118 */       this.blockList = new ArrayList<>();
/*     */     }
/*     */     
/*     */     public Location[] createGladiator(List<Block> blockList, Location gladiatorLocation) {
/* 122 */       Location loc = gladiatorLocation;
/* 123 */       boolean hasGladi = true;
/*     */       
/* 125 */       while (hasGladi) {
/* 126 */         hasGladi = false;
/* 127 */         boolean stop = false; double d;
/* 128 */         for (d = -8.0D; d <= 8.0D; d++) {
/* 129 */           double z; for (z = -8.0D; z <= 8.0D; z++) {
/* 130 */             double y; for (y = 0.0D; y <= 10.0D; y++) {
/* 131 */               Location l = new Location(loc.getWorld(), loc.getX() + d, 190.0D + y, loc.getZ() + z);
/* 132 */               if (l.getBlock().getType() != Material.AIR) {
/* 133 */                 hasGladi = true;
/* 134 */                 loc = new Location(loc.getWorld(), loc.getX() + 20.0D, loc.getY(), loc.getZ());
/* 135 */                 stop = true;
/*     */               } 
/* 137 */               if (stop) {
/*     */                 break;
/*     */               }
/*     */             } 
/* 141 */             if (stop) {
/*     */               break;
/*     */             }
/*     */           } 
/* 145 */           if (stop) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 151 */       Block mainBlock = loc.getBlock();
/*     */       double x;
/* 153 */       for (x = -this.radius; x <= this.radius; x++) {
/* 154 */         double z; for (z = -this.radius; z <= this.radius; z++) {
/* 155 */           double y; for (y = 0.0D; y <= this.height; y++) {
/*     */             
/* 157 */             Location l = new Location(mainBlock.getWorld(), mainBlock.getX() + x, 190.0D + y, mainBlock.getZ() + z);
/* 158 */             l.getBlock().setType(Material.GLASS);
/* 159 */             blockList.add(l.getBlock());
/* 160 */             this.blockList.add(l.getBlock());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 165 */       for (x = (-this.radius + 1); x <= (this.radius - 1); x++) {
/* 166 */         double z; for (z = (-this.radius + 1); z <= (this.radius - 1); z++) {
/* 167 */           double y; for (y = 1.0D; y <= this.height; y++) {
/*     */             
/* 169 */             Location l = new Location(mainBlock.getWorld(), mainBlock.getX() + x, 190.0D + y, mainBlock.getZ() + z);
/* 170 */             l.getBlock().setType(Material.AIR);
/* 171 */             this.blockList.remove(l.getBlock());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 176 */       return new Location[] { new Location(mainBlock
/* 177 */             .getWorld(), mainBlock.getX() + 6.5D, 191.0D, mainBlock.getZ() + 6.5D), new Location(mainBlock
/* 178 */             .getWorld(), mainBlock.getX() - 5.5D, 191.0D, mainBlock
/* 179 */             .getZ() - 5.5D) };
/*     */     }
/*     */     
/*     */     public boolean isInFight(Player player) {
/* 183 */       return this.playerList.containsKey(player);
/*     */     }
/*     */     
/*     */     public Gladiator getGladiator(Player player) {
/* 187 */       return this.playerList.get(player);
/*     */     }
/*     */     
/*     */     public boolean isGladiatorBlock(Block block) {
/* 191 */       return this.blockList.contains(block);
/*     */     }
/*     */     
/*     */     public void sendGladiator(Player player, Player target) {
/* 195 */       Gladiator gladiator = new Gladiator(player, target);
/*     */       
/* 197 */       this.playerList.put(player, gladiator);
/* 198 */       this.playerList.put(target, gladiator);
/* 199 */       this.gladiatorList.add(gladiator);
/* 200 */       this.listener.register();
/*     */     }
/*     */     
/*     */     public void removeGladiator(Gladiator gladiator) {
/* 204 */       this.playerList.remove(gladiator.gladiator);
/* 205 */       this.playerList.remove(gladiator.player);
/* 206 */       this.gladiatorList.remove(gladiator);
/*     */       
/* 208 */       if (this.playerList.isEmpty()) {
/* 209 */         this.listener.unregister();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public class Gladiator
/*     */     {
/*     */       private Player gladiator;
/*     */       
/*     */       private Player player;
/*     */       private Location gladiatorLocation;
/*     */       private Location backLocation;
/*     */       private List<Block> gladiatorBlocks;
/*     */       private List<Block> playersBlocks;
/*     */       private int time;
/*     */       
/*     */       public Gladiator(Player gladiator, Player player) {
/* 226 */         this.gladiator = gladiator;
/* 227 */         this.player = player;
/*     */         
/* 229 */         this.gladiatorBlocks = new ArrayList<>();
/* 230 */         this.playersBlocks = new ArrayList<>();
/*     */         
/* 232 */         this.gladiatorLocation = gladiator.getLocation();
/* 233 */         this.backLocation = gladiator.getLocation();
/*     */         
/* 235 */         Location[] location = GladiatorKit.GladiatorController.this.createGladiator(this.gladiatorBlocks, this.gladiatorLocation);
/*     */         
/* 237 */         Location l1 = location[0];
/* 238 */         l1.setYaw(135.0F);
/*     */         
/* 240 */         gladiator.teleport(l1);
/* 241 */         gladiator.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
/*     */         
/* 243 */         Location l2 = location[1];
/* 244 */         l2.setYaw(315.0F);
/*     */         
/* 246 */         player.teleport(l2);
/* 247 */         player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
/*     */         
/* 249 */         player.damage(1.0D, (Entity)gladiator);
/* 250 */         gladiator.damage(1.0D, (Entity)player);
/*     */       }
/*     */       
/*     */       public void handleEscape(boolean teleportBack) {
/* 254 */         clearGladiator();
/*     */         
/* 256 */         if (teleportBack) {
/* 257 */           teleportBack();
/*     */         }
/* 259 */         this.gladiator.removePotionEffect(PotionEffectType.WITHER);
/* 260 */         this.player.removePotionEffect(PotionEffectType.WITHER);
/* 261 */         GladiatorKit.GladiatorController.this.removeGladiator(this);
/*     */         
/* 263 */         Bukkit.getPluginManager().callEvent((Event)new GladiatorScapeEvent(this.gladiator, this.player));
/*     */       }
/*     */       
/*     */       public void handleWin(Player death) {
/* 267 */         Player winner = (death == this.gladiator) ? this.player : this.gladiator;
/*     */         
/* 269 */         clearGladiator();
/*     */         
/* 271 */         winner.teleport(this.backLocation);
/* 272 */         winner.removePotionEffect(PotionEffectType.WITHER);
/* 273 */         winner.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
/* 274 */         GladiatorKit.GladiatorController.this.removeGladiator(this);
/* 275 */         Bukkit.getPluginManager().callEvent((Event)new GladiatorFinishEvent(this.gladiator, this.player));
/*     */       }
/*     */       
/*     */       public void handleFinish() {
/* 279 */         clearGladiator();
/* 280 */         teleportBack();
/*     */         
/* 282 */         if (this.gladiator.isOnline()) {
/* 283 */           this.gladiator.removePotionEffect(PotionEffectType.WITHER);
/*     */         }
/* 285 */         if (this.player.isOnline()) {
/* 286 */           this.player.removePotionEffect(PotionEffectType.WITHER);
/*     */         }
/* 288 */         GladiatorKit.GladiatorController.this.removeGladiator(this);
/* 289 */         Bukkit.getPluginManager().callEvent((Event)new GladiatorFinishEvent(this.gladiator, this.player));
/*     */       }
/*     */       
/*     */       public void pulse() {
/* 293 */         this.time++;
/*     */         
/* 295 */         if (this.time == 10) {
/* 296 */           for (Block block : this.gladiatorBlocks) {
/* 297 */             if (block.hasMetadata("gladiatorBreakable")) {
/* 298 */               block.setType(Material.AIR);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 303 */         if (this.time == 120) {
/* 304 */           this.gladiator.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 3));
/* 305 */           this.player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1200, 3));
/*     */         } 
/*     */         
/* 308 */         if (this.time == 180) {
/* 309 */           handleFinish();
/*     */         }
/*     */       }
/*     */       
/*     */       public void addBlock(Block block) {
/* 314 */         if (!this.playersBlocks.contains(block))
/* 315 */           this.playersBlocks.add(block); 
/*     */       }
/*     */       
/*     */       public boolean removeBlock(Block block) {
/* 319 */         if (this.playersBlocks.contains(block)) {
/* 320 */           this.playersBlocks.remove(block);
/* 321 */           return true;
/*     */         } 
/*     */         
/* 324 */         return false;
/*     */       }
/*     */       
/*     */       private void clearGladiator() {
/* 328 */         for (Block block : this.gladiatorBlocks) {
/* 329 */           block.setType(Material.AIR);
/*     */           
/* 331 */           if (GladiatorKit.GladiatorController.this.blockList.contains(block)) {
/* 332 */             GladiatorKit.GladiatorController.this.blockList.remove(block);
/*     */           }
/*     */         } 
/* 335 */         for (Block block : this.playersBlocks) {
/* 336 */           block.setType(Material.AIR);
/*     */           
/* 338 */           if (GladiatorKit.GladiatorController.this.blockList.contains(block))
/* 339 */             GladiatorKit.GladiatorController.this.blockList.remove(block); 
/*     */         } 
/*     */       }
/*     */       
/*     */       private void teleportBack() {
/* 344 */         this.gladiator.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
/* 345 */         this.gladiator.teleport(this.backLocation);
/*     */         
/* 347 */         this.player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 5));
/* 348 */         this.player.teleport(this.backLocation);
/*     */       }
/*     */       
/*     */       public boolean isInGladiator(Player player) {
/* 352 */         return (player == this.player || player == this.gladiator);
/*     */       }
/*     */     }
/*     */     
/*     */     public class GladiatorListener
/*     */       implements Listener {
/*     */       private boolean registered;
/*     */       
/*     */       @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
/*     */       public void onPlayerDeath(PlayerDeathEvent event) {
/* 362 */         Player player = event.getEntity();
/*     */         
/* 364 */         if (GladiatorKit.GladiatorController.this.isInFight(player)) {
/* 365 */           event.getDrops().clear();
/* 366 */           GladiatorKit.GladiatorController.this.getGladiator(player).handleWin(player);
/*     */         } 
/*     */       }
/*     */       
/*     */       @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
/*     */       public void onPlayerQuit(PlayerQuitEvent event) {
/* 372 */         Player player = event.getPlayer();
/*     */         
/* 374 */         if (GladiatorKit.GladiatorController.this.isInFight(player))
/* 375 */           GladiatorKit.GladiatorController.this.getGladiator(player).handleWin(player); 
/*     */       }
/*     */       
/*     */       @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
/*     */       public void onPlayerDamage(PlayerDamagePlayerEvent event) {
/* 380 */         Player damager = event.getDamager();
/* 381 */         Player player = event.getPlayer();
/*     */         
/* 383 */         if (GladiatorKit.GladiatorController.this.isInFight(player)) {
/* 384 */           GladiatorKit.GladiatorController.Gladiator gladiator = GladiatorKit.GladiatorController.this.getGladiator(player);
/*     */           
/* 386 */           if (gladiator.isInGladiator(damager))
/* 387 */           { event.setCancelled(false); }
/*     */           else
/* 389 */           { event.setCancelled(true); } 
/* 390 */         } else if (GladiatorKit.GladiatorController.this.isInFight(damager)) {
/* 391 */           GladiatorKit.GladiatorController.Gladiator gladiator = GladiatorKit.GladiatorController.this.getGladiator(damager);
/*     */           
/* 393 */           if (gladiator.isInGladiator(player)) {
/* 394 */             event.setCancelled(false);
/*     */           } else {
/* 396 */             event.setCancelled(true);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
/*     */       public void onBlockPlace(BlockPlaceEvent event) {
/* 402 */         Player player = event.getPlayer();
/*     */         
/* 404 */         if (GladiatorKit.GladiatorController.this.isInFight(player))
/* 405 */           GladiatorKit.GladiatorController.this.getGladiator(player).addBlock(event.getBlock()); 
/*     */       }
/*     */       
/*     */       @EventHandler(priority = EventPriority.MONITOR)
/*     */       public void onBlockBreak(BlockBreakEvent event) {
/* 410 */         Player player = event.getPlayer();
/*     */         
/* 412 */         if (GladiatorKit.GladiatorController.this.blockList.contains(event.getBlock())) {
/* 413 */           event.setCancelled(true);
/*     */           
/*     */           return;
/*     */         } 
/* 417 */         if (GladiatorKit.GladiatorController.this.isInFight(player)) {
/* 418 */           GladiatorKit.GladiatorController.this.getGladiator(player).removeBlock(event.getBlock());
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*     */       @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
/*     */       public void onBlockBreak(BlockDamageEvent event) {
/* 425 */         if (GladiatorKit.GladiatorController.this.blockList.contains(event.getBlock())) {
/* 426 */           Player player = event.getPlayer();
/* 427 */           Block block = event.getBlock();
/*     */           
/* 429 */           player.sendBlockChange(block.getLocation(), Material.BEDROCK, (byte)0);
/*     */           return;
/*     */         } 
/*     */       }
/*     */       
/*     */       @EventHandler
/*     */       public void onUpdate(UpdateEvent event) {
/* 436 */         if (event.getType() == UpdateEvent.UpdateType.SECOND) {
/* 437 */           GladiatorKit.GladiatorController.this.gladiatorList.iterator().forEachRemaining(GladiatorKit.GladiatorController.Gladiator::pulse);
/*     */         }
/*     */       }
/*     */       
/*     */       @EventHandler
/*     */       public void onPlayerMoveUpdate(PlayerMoveUpdateEvent event) {
/* 443 */         Player player = event.getPlayer();
/*     */         
/* 445 */         if (GladiatorKit.GladiatorController.this.isInFight(player)) {
/* 446 */           GladiatorKit.GladiatorController.Gladiator gladiator = GladiatorKit.GladiatorController.this.getGladiator(player);
/*     */           
/* 448 */           if (event.getFrom().getY() - 190.0D > GladiatorKit.GladiatorController.this.height) {
/* 449 */             gladiator.handleEscape(true);
/* 450 */           } else if (event.getFrom().getY() <= 188.0D && gladiator.time > 2) {
/* 451 */             gladiator.handleEscape(true);
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       @EventHandler
/*     */       public void onExplode(EntityExplodeEvent event) {
/* 463 */         Iterator<Block> blockIt = event.blockList().iterator();
/*     */         
/* 465 */         while (blockIt.hasNext()) {
/* 466 */           Block b = blockIt.next();
/* 467 */           if (GladiatorKit.GladiatorController.this.blockList.contains(b)) {
/* 468 */             blockIt.remove();
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/*     */       public void register() {
/* 474 */         if (!this.registered) {
/* 475 */           Bukkit.getPluginManager().registerEvents(this, (Plugin)GameMain.getInstance());
/* 476 */           this.registered = true;
/*     */         } 
/*     */       }
/*     */       
/*     */       public void unregister() {
/* 481 */         if (this.registered) {
/* 482 */           HandlerList.unregisterAll(this);
/* 483 */           this.registered = false;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/GladiatorKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
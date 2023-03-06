/*     */ package net.highmc.bukkit.pvp.arena.listener;
/*     */ 
/*     */ import net.highmc.bukkit.BukkitCommon;
/*     */ import net.highmc.bukkit.pvp.arena.GameMain;
/*     */ import net.highmc.bukkit.pvp.arena.gamer.Gamer;
/*     */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*     */ import net.highmc.bukkit.pvp.arena.menu.AbilityInventory;
/*     */ import net.highmc.bukkit.pvp.event.PlayerProtectionEvent;
/*     */ import net.highmc.bukkit.pvp.event.PlayerSpawnEvent;
/*     */ import net.highmc.bukkit.utils.item.ActionItemStack;
/*     */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerListener
/*     */   implements Listener
/*     */ {
/*  29 */   private static final ActionItemStack SELECT_PRIMARY = new ActionItemStack((new ItemBuilder())
/*  30 */       .name("§aSelecionar kit 1").type(Material.CHEST).build(), new ActionItemStack.Interact()
/*     */       {
/*     */         
/*     */         public boolean onInteract(Player player, Entity entity, Block block, ItemStack item, ActionItemStack.ActionType action)
/*     */         {
/*  35 */           new AbilityInventory(player, AbilityInventory.InventoryType.PRIMARY);
/*  36 */           return false;
/*     */         }
/*     */       });
/*     */   
/*  40 */   private static final ActionItemStack SELECT_SECONDARY = new ActionItemStack((new ItemBuilder())
/*  41 */       .name("§aSelecionar kit 2").type(Material.CHEST).build(), new ActionItemStack.Interact()
/*     */       {
/*     */         
/*     */         public boolean onInteract(Player player, Entity entity, Block block, ItemStack item, ActionItemStack.ActionType action)
/*     */         {
/*  46 */           new AbilityInventory(player, AbilityInventory.InventoryType.SECONDARY);
/*  47 */           return false;
/*     */         }
/*     */       });
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onPlayerJoin(PlayerJoinEvent event) {
/*  53 */     handlePlayer(event.getPlayer());
/*  54 */     event.getPlayer().teleport(BukkitCommon.getInstance().getLocationManager().getLocation("spawn"));
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void onPlayerSpawn(PlayerSpawnEvent event) {
/*  59 */     handlePlayer(event.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void onPlayerProtection(PlayerProtectionEvent event) {
/*  64 */     if (event.getNewState()) {
/*  65 */       Gamer gamer = (Gamer)GameMain.getInstance().getGamerManager().getGamer(event.getPlayer().getUniqueId(), Gamer.class);
/*     */       
/*  67 */       gamer.setPrimaryKit(null);
/*  68 */       gamer.setSecondaryKit(null);
/*     */     } else {
/*  70 */       Player player = event.getPlayer();
/*  71 */       handleCombatPlayer(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void onPlayerDropItem(PlayerDropItemEvent event) {
/*  77 */     Player player = event.getPlayer();
/*  78 */     ItemStack itemStack = event.getItemDrop().getItemStack();
/*     */     
/*  80 */     if (itemStack.isSimilar(SELECT_PRIMARY.getItemStack()) || itemStack
/*  81 */       .isSimilar(SELECT_SECONDARY.getItemStack())) {
/*  82 */       event.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/*  86 */     Kit kit = ((Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class)).getPrimaryKit();
/*     */     
/*  88 */     if (kit != null && kit.isAbilityItem(itemStack)) {
/*  89 */       event.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/*  93 */     kit = ((Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class)).getSecondaryKit();
/*     */     
/*  95 */     if (kit != null && kit.isAbilityItem(itemStack))
/*  96 */       event.setCancelled(true); 
/*     */   }
/*     */   
/*     */   private void resetPlayer(Player player) {
/* 100 */     player.getInventory().clear();
/* 101 */     player.getInventory().setArmorContents(new ItemStack[4]);
/* 102 */     player.setHealth(20.0D);
/* 103 */     player.setFoodLevel(20);
/* 104 */     player.setFireTicks(0);
/* 105 */     player.setExp(0.0F);
/* 106 */     player.setLevel(0);
/* 107 */     player.setFallDistance(-1.0F);
/*     */   }
/*     */   
/*     */   public void handleCombatPlayer(Player player) {
/* 111 */     resetPlayer(player);
/*     */     
/* 113 */     player.getInventory().setItem(0, (new ItemBuilder()).type(Material.STONE_SWORD).build());
/*     */     
/* 115 */     Kit kit = ((Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class)).getPrimaryKit();
/*     */     
/* 117 */     if (kit != null) {
/* 118 */       kit.applyKit(player);
/*     */     }
/*     */     
/* 121 */     kit = ((Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class)).getSecondaryKit();
/*     */     
/* 123 */     if (kit != null) {
/* 124 */       kit.applyKit(player);
/*     */     }
/* 126 */     for (int x = 0; x < 36; x++) {
/* 127 */       player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
/*     */     } 
/* 129 */     player.getInventory().setItem(13, (new ItemBuilder()).amount(64).type(Material.BOWL).build());
/* 130 */     player.getInventory().setItem(14, (new ItemBuilder()).amount(64).type(Material.RED_MUSHROOM).build());
/* 131 */     player.getInventory().setItem(15, (new ItemBuilder()).amount(64).type(Material.BROWN_MUSHROOM).build());
/*     */   }
/*     */   
/*     */   private void handlePlayer(Player player) {
/* 135 */     resetPlayer(player);
/* 136 */     player.getInventory().setItem(0, SELECT_PRIMARY.getItemStack());
/* 137 */     player.getInventory().setItem(1, SELECT_SECONDARY.getItemStack());
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/listener/PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
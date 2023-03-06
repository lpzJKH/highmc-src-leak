/*     */ package net.highmc.bukkit.pvp.arena.kit;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.highmc.bukkit.pvp.arena.GameMain;
/*     */ import net.highmc.bukkit.utils.cooldown.Cooldown;
/*     */ import net.highmc.language.Language;
/*     */ import net.highmc.utils.DateUtils;
/*     */ import net.highmc.utils.string.StringFormat;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public abstract class Kit implements Listener {
/*     */   private String kitName;
/*     */   private String kitDescription;
/*     */   private Material kitType;
/*     */   
/*  25 */   public String getKitName() { return this.kitName; } private int price; private List<ItemStack> itemList; private boolean registred; private Set<UUID> playerSet; public String getKitDescription() {
/*  26 */     return this.kitDescription; } public Material getKitType() {
/*  27 */     return this.kitType;
/*     */   } public int getPrice() {
/*  29 */     return this.price;
/*     */   } public List<ItemStack> getItemList() {
/*  31 */     return this.itemList;
/*     */   }
/*  33 */   public boolean isRegistred() { return this.registred; } public Set<UUID> getPlayerSet() {
/*  34 */     return this.playerSet;
/*     */   }
/*     */   public Kit(String kitName, String kitDescription, Material kitType, int price, List<ItemStack> itemList) {
/*  37 */     this.kitName = kitName;
/*  38 */     this.kitDescription = kitDescription;
/*  39 */     this.kitType = kitType;
/*  40 */     this.price = price;
/*  41 */     this.itemList = itemList;
/*  42 */     this.playerSet = new HashSet<>();
/*     */   }
/*     */   
/*     */   public void register() {
/*  46 */     if (this.registred) {
/*     */       return;
/*     */     }
/*     */     
/*  50 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)GameMain.getInstance());
/*  51 */     this.registred = true;
/*     */   }
/*     */   
/*     */   public void unregister() {
/*  55 */     if (!this.registred) {
/*     */       return;
/*     */     }
/*  58 */     HandlerList.unregisterAll(this);
/*  59 */     this.registred = false;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  63 */     return this.kitName;
/*     */   }
/*     */   
/*     */   public void addPlayer(UUID playerId) {
/*  67 */     this.playerSet.add(playerId);
/*  68 */     register();
/*     */   }
/*     */   
/*     */   public void removePlayer(UUID playerId) {
/*  72 */     this.playerSet.remove(playerId);
/*     */     
/*  74 */     if (this.playerSet.isEmpty())
/*  75 */       unregister(); 
/*     */   }
/*     */   
/*     */   public boolean hasAbility(Player player) {
/*  79 */     return this.playerSet.contains(player.getUniqueId());
/*     */   }
/*     */   
/*     */   public boolean isAbilityItem(ItemStack item) {
/*  83 */     if (item == null) {
/*  84 */       return false;
/*     */     }
/*  86 */     return this.itemList.contains(item);
/*     */   }
/*     */   
/*     */   public boolean isCooldown(Player player) {
/*  90 */     if (GameMain.getInstance().getCooldownManager().hasCooldown(player.getUniqueId(), "Kit " + 
/*  91 */         StringFormat.formatString(getName()))) {
/*     */       
/*  93 */       Cooldown cooldown = GameMain.getInstance().getCooldownManager().getCooldown(player.getUniqueId(), "Kit " + 
/*  94 */           StringFormat.formatString(getName()));
/*     */       
/*  96 */       if (cooldown == null) {
/*  97 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 102 */       String message = "§cVocê precisa esperar " + DateUtils.formatDifference(Language.getLanguage(player.getUniqueId()), (long)cooldown.getRemaining()) + " para usar o Kit " + StringFormat.formatString(getName()) + " novamente!";
/*     */       
/* 104 */       player.sendMessage(message);
/* 105 */       return true;
/*     */     } 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   public void addCooldown(Player player, long time) {
/* 112 */     GameMain.getInstance().getCooldownManager().addCooldown(player.getUniqueId(), "Kit " + 
/* 113 */         StringFormat.formatString(getName()), time);
/*     */   }
/*     */   
/*     */   public void addCooldown(UUID uniqueId, long time) {
/* 117 */     GameMain.getInstance().getCooldownManager().addCooldown(uniqueId, "Kit " + StringFormat.formatString(getName()), time);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyKit(Player player) {
/* 126 */     for (ItemStack item : this.itemList) {
/* 127 */       player.getInventory().addItem(new ItemStack[] { item });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/Kit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
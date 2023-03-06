/*     */ package net.highmc.bukkit.pvp;
/*     */ import net.highmc.bukkit.BukkitCommon;
/*     */ import net.highmc.bukkit.pvp.backend.GamerData;
/*     */ import net.highmc.bukkit.pvp.backend.impl.VoidGamerData;
/*     */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*     */ import net.highmc.bukkit.pvp.listener.DamageListener;
/*     */ import net.highmc.bukkit.pvp.listener.SignListener;
/*     */ import net.highmc.bukkit.pvp.listener.WorldListener;
/*     */ import net.highmc.bukkit.pvp.manager.GamerManager;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.ShapelessRecipe;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class GameAPI extends BukkitCommon {
/*     */   private static GameAPI instance;
/*     */   private GamerData gamerData;
/*     */   private Class<? extends Gamer> gamerClass;
/*     */   
/*     */   public static GameAPI getInstance() {
/*  24 */     return instance;
/*     */   } private GamerManager gamerManager; private boolean dropItems; private double protectionRadius; private boolean fullIron;
/*     */   public GamerData getGamerData() {
/*  27 */     return this.gamerData; }
/*  28 */   public void setGamerClass(Class<? extends Gamer> gamerClass) { this.gamerClass = gamerClass; } public Class<? extends Gamer> getGamerClass() {
/*  29 */     return this.gamerClass;
/*     */   } public GamerManager getGamerManager() {
/*  31 */     return this.gamerManager;
/*     */   }
/*  33 */   public void setDropItems(boolean dropItems) { this.dropItems = dropItems; }
/*  34 */   public boolean isDropItems() { return this.dropItems; }
/*  35 */   public double getProtectionRadius() { return this.protectionRadius; } public boolean isFullIron() {
/*  36 */     return this.fullIron;
/*     */   } private boolean fallDamageProtection = false;
/*  38 */   public void setFallDamageProtection(boolean fallDamageProtection) { this.fallDamageProtection = fallDamageProtection; } public boolean isFallDamageProtection() {
/*  39 */     return this.fallDamageProtection;
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  43 */     instance = this;
/*  44 */     super.onEnable();
/*     */     
/*  46 */     this.protectionRadius = getConfig().getDouble("protectionRadius", 30.0D);
/*  47 */     this.fullIron = getConfig().getBoolean("fullIron", true);
/*     */     
/*  49 */     this.gamerData = (GamerData)new VoidGamerData();
/*     */     
/*  51 */     this.gamerManager = new GamerManager();
/*     */     
/*  53 */     Bukkit.getPluginManager().registerEvents((Listener)new DamageListener(), (Plugin)this);
/*  54 */     Bukkit.getPluginManager().registerEvents((Listener)new GamerListener(), (Plugin)this);
/*  55 */     Bukkit.getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
/*  56 */     Bukkit.getPluginManager().registerEvents((Listener)new SignListener(), (Plugin)this);
/*  57 */     Bukkit.getPluginManager().registerEvents((Listener)new WorldListener(), (Plugin)this);
/*     */     
/*  59 */     loadSoups();
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadSoups() {
/*  64 */     ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
/*  65 */     ShapelessRecipe cocoa = new ShapelessRecipe(soup);
/*  66 */     ShapelessRecipe cactus = new ShapelessRecipe(soup);
/*  67 */     ShapelessRecipe pumpkin = new ShapelessRecipe(soup);
/*  68 */     ShapelessRecipe melon = new ShapelessRecipe(soup);
/*  69 */     ShapelessRecipe flower = new ShapelessRecipe(soup);
/*  70 */     ShapelessRecipe nether = new ShapelessRecipe(soup);
/*     */     
/*  72 */     cocoa.addIngredient(Material.BOWL);
/*  73 */     cocoa.addIngredient(Material.INK_SACK, 3);
/*     */     
/*  75 */     cactus.addIngredient(Material.BOWL);
/*  76 */     cactus.addIngredient(Material.CACTUS);
/*     */     
/*  78 */     pumpkin.addIngredient(Material.BOWL);
/*  79 */     pumpkin.addIngredient(1, Material.PUMPKIN_SEEDS);
/*     */     
/*  81 */     melon.addIngredient(Material.BOWL);
/*  82 */     melon.addIngredient(1, Material.MELON_SEEDS);
/*     */     
/*  84 */     nether.addIngredient(Material.BOWL);
/*  85 */     nether.addIngredient(Material.getMaterial(372));
/*     */     
/*  87 */     flower.addIngredient(Material.BOWL);
/*  88 */     flower.addIngredient(Material.RED_ROSE);
/*  89 */     flower.addIngredient(Material.YELLOW_FLOWER);
/*     */     
/*  91 */     Bukkit.addRecipe((Recipe)cocoa);
/*  92 */     Bukkit.addRecipe((Recipe)cactus);
/*  93 */     Bukkit.addRecipe((Recipe)pumpkin);
/*  94 */     Bukkit.addRecipe((Recipe)melon);
/*  95 */     Bukkit.addRecipe((Recipe)nether);
/*  96 */     Bukkit.addRecipe((Recipe)flower);
/*     */   }
/*     */   
/*     */   public void setProtectionRadius(double protectionRadius) {
/* 100 */     this.protectionRadius = protectionRadius;
/* 101 */     getConfig().set("protectionRadius", Double.valueOf(protectionRadius));
/* 102 */     saveDefaultConfig();
/*     */   }
/*     */   
/*     */   public void setFullIron(boolean fullIron) {
/* 106 */     this.fullIron = fullIron;
/* 107 */     getConfig().set("fullIron", Boolean.valueOf(fullIron));
/* 108 */     saveDefaultConfig();
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/GameAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
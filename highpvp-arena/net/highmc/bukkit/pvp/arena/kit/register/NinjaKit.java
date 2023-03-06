/*     */ package net.highmc.bukkit.pvp.arena.kit.register;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.player.PlayerQuitEvent;
/*     */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*     */ 
/*     */ 
/*     */ public class NinjaKit
/*     */   extends Kit
/*     */ {
/*     */   private HashMap<String, NinjaHit> ninjaHits;
/*     */   
/*     */   public NinjaKit() {
/*  23 */     super("Ninja", "Como um ninja teletransporte-se para as costas de seus inimigos", Material.EMERALD, 17000, new ArrayList());
/*     */     
/*  25 */     this.ninjaHits = new HashMap<>();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onNinjaHit(EntityDamageByEntityEvent event) {
/*  30 */     if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
/*  31 */       Player damager = (Player)event.getDamager();
/*  32 */       Player damaged = (Player)event.getEntity();
/*  33 */       if (hasAbility(damager)) {
/*  34 */         NinjaHit ninjaHit = this.ninjaHits.get(damager.getName());
/*  35 */         if (ninjaHit == null) {
/*  36 */           ninjaHit = new NinjaHit(damaged);
/*     */         } else {
/*  38 */           ninjaHit.setTarget(damaged);
/*  39 */         }  this.ninjaHits.put(damager.getName(), ninjaHit);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onShift(PlayerToggleSneakEvent event) {
/*  46 */     Player p = event.getPlayer();
/*     */     
/*  48 */     if (!event.isSneaking()) {
/*     */       return;
/*     */     }
/*  51 */     if (!hasAbility(p)) {
/*     */       return;
/*     */     }
/*  54 */     if (!this.ninjaHits.containsKey(p.getName())) {
/*     */       return;
/*     */     }
/*  57 */     NinjaHit ninjaHit = this.ninjaHits.get(p.getName());
/*  58 */     Player target = ninjaHit.getTarget();
/*     */     
/*  60 */     if (target.isDead()) {
/*     */       return;
/*     */     }
/*  63 */     if (ninjaHit.getTargetExpires() < System.currentTimeMillis()) {
/*     */       return;
/*     */     }
/*  66 */     if (p.getLocation().distance(target.getLocation()) > 50.0D) {
/*  67 */       p.sendMessage("§a§l> §fO jogador está muito longe§f!");
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     if (isCooldown(p)) {
/*     */       return;
/*     */     }
/*     */     
/*  75 */     p.teleport(target.getLocation());
/*  76 */     p.sendMessage("§a§l> §fTeletransportado até o §a" + target.getName() + "§f!");
/*  77 */     addCooldown(p, 6L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onDeath(PlayerDeathEvent event) {
/*  82 */     Player p = event.getEntity();
/*     */     
/*  84 */     if (p.getKiller() != null) {
/*  85 */       Iterator<Map.Entry<String, NinjaHit>> iterator = this.ninjaHits.entrySet().iterator();
/*     */       
/*  87 */       while (iterator.hasNext()) {
/*  88 */         Map.Entry<String, NinjaHit> entry = iterator.next();
/*     */         
/*  90 */         if ((entry.getValue()).target == p.getKiller()) {
/*  91 */           iterator.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*  95 */     if (!this.ninjaHits.containsKey(p.getName())) {
/*     */       return;
/*     */     }
/*  98 */     this.ninjaHits.remove(p.getName());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onQuit(PlayerQuitEvent event) {
/* 103 */     Player p = event.getPlayer();
/*     */     
/* 105 */     if (!this.ninjaHits.containsKey(p.getName())) {
/*     */       return;
/*     */     }
/* 108 */     this.ninjaHits.remove(p.getName());
/*     */   }
/*     */   
/*     */   private static class NinjaHit {
/*     */     private Player target;
/*     */     private long targetExpires;
/*     */     
/*     */     public NinjaHit(Player target) {
/* 116 */       this.target = target;
/* 117 */       this.targetExpires = System.currentTimeMillis() + 15000L;
/*     */     }
/*     */     
/*     */     public Player getTarget() {
/* 121 */       return this.target;
/*     */     }
/*     */     
/*     */     public long getTargetExpires() {
/* 125 */       return this.targetExpires;
/*     */     }
/*     */     
/*     */     public void setTarget(Player player) {
/* 129 */       this.target = player;
/* 130 */       this.targetExpires = System.currentTimeMillis() + 20000L;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/NinjaKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
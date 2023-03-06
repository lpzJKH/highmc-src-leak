/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class KangarooKit
/*    */   extends Kit
/*    */ {
/* 24 */   private final List<Player> kangarooMap = new ArrayList<>();
/*    */   
/*    */   public KangarooKit() {
/* 27 */     super("Kangaroo", "Use o seu foguete para movimentar-se mais rapidamente pelo mapa", Material.FIREWORK, 18000, 
/* 28 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aKangaroo").type(Material.FIREWORK).build() }));
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*    */   public void onEntityDamage(EntityDamageByEntityEvent event) {
/* 33 */     if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
/*    */       return;
/*    */     }
/* 36 */     Player player = (Player)event.getEntity();
/*    */     
/* 38 */     if (hasAbility(player))
/* 39 */       addCooldown(player, 8L); 
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent event) {
/* 44 */     Player player = event.getPlayer();
/*    */     
/* 46 */     if (hasAbility(player) && event.getAction() != Action.PHYSICAL && isAbilityItem(player.getItemInHand())) {
/* 47 */       event.setCancelled(true);
/*    */       
/* 49 */       if (isCooldown(player) || this.kangarooMap.contains(player)) {
/*    */         return;
/*    */       }
/*    */       
/* 53 */       Vector vector = player.getEyeLocation().getDirection().multiply(player.isSneaking() ? 1.8F : 0.6F).setY(player.isSneaking() ? 0.6D : 0.8999999761581421D);
/* 54 */       player.setFallDistance(-1.0F);
/* 55 */       player.setVelocity(vector);
/* 56 */       this.kangarooMap.add(player);
/*    */     } 
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*    */   public void onEntityDamage(EntityDamageEvent event) {
/* 62 */     if (event.getEntity() instanceof Player) {
/* 63 */       Player player = (Player)event.getEntity();
/*    */       
/* 65 */       if (hasAbility(player) && 
/* 66 */         event.getCause().name().contains("FALL")) {
/* 67 */         if (event.getDamage() > 7.0D) {
/* 68 */           event.setDamage((player.getHealth() - 5.0D > 0.5D) ? 5.0D : 0.0D);
/* 69 */         } else if (event.getDamage() < 2.0D) {
/* 70 */           event.setCancelled(true);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void onPlayerMove(PlayerMoveEvent event) {
/* 79 */     Player player = event.getPlayer();
/*    */     
/* 81 */     if (hasAbility(player) && 
/* 82 */       this.kangarooMap.contains(player)) {
/* 83 */       Block block = player.getLocation().clone().add(0.0D, -1.0D, 0.0D).getBlock();
/* 84 */       if (block.getType() != Material.AIR)
/* 85 */         this.kangarooMap.remove(player); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/KangarooKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
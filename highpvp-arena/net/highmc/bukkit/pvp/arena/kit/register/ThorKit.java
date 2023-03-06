/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.UUID;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ThorKit
/*    */   extends Kit
/*    */ {
/*    */   private Map<UUID, Long> damageRaio;
/*    */   
/*    */   public ThorKit() {
/* 26 */     super("Thor", "Jogue raios em seus inimigos com seu machado", Material.WOOD_AXE, 10500, 
/* 27 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aThor").type(Material.WOOD_AXE).build() }));
/* 28 */     this.damageRaio = new HashMap<>();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
/* 33 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 36 */     Player player = (Player)event.getEntity();
/*    */     
/* 38 */     if (event.getEntity() instanceof org.bukkit.entity.LightningStrike) {
/* 39 */       if (this.damageRaio.containsKey(player.getUniqueId()) && ((Long)this.damageRaio
/* 40 */         .get(player.getUniqueId())).longValue() < System.currentTimeMillis()) {
/* 41 */         event.setDamage(0.0D);
/*    */       } else {
/* 43 */         event.setDamage(6.0D);
/* 44 */         event.getEntity().setFireTicks(200);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onPlayerInteract(PlayerInteractEvent e) {
/* 51 */     Player player = e.getPlayer();
/*    */     
/* 53 */     if (player.getItemInHand() == null) {
/*    */       return;
/*    */     }
/* 56 */     if (!isAbilityItem(player.getItemInHand())) {
/*    */       return;
/*    */     }
/* 59 */     if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) {
/*    */       return;
/*    */     }
/* 62 */     if (hasAbility(player)) {
/* 63 */       if (isCooldown(player)) {
/*    */         return;
/*    */       }
/*    */       
/* 67 */       Location loc = player.getTargetBlock((Set)null, 20).getLocation();
/* 68 */       loc = loc.getWorld().getHighestBlockAt(loc).getLocation();
/*    */       
/* 70 */       this.damageRaio.put(player.getUniqueId(), Long.valueOf(System.currentTimeMillis() + 4000L));
/* 71 */       player.getWorld().strikeLightning(loc);
/*    */       
/* 73 */       addCooldown(player, 8L);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/ThorKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
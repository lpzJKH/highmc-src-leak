/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.entity.Snowball;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.metadata.FixedMetadataValue;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class SwitcherKit extends Kit {
/*    */   public SwitcherKit() {
/* 22 */     super("Switcher", "Troque de lugar com seus inimigos com sua bola de neve", Material.SNOW_BALL, 12500, 
/* 23 */         Arrays.asList(new ItemStack[] { (new ItemBuilder()).name("§aSwitcher").type(Material.SNOW_BALL).build() }));
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onProjectileLaunch(PlayerInteractEvent event) {
/* 28 */     Player player = event.getPlayer();
/*    */     
/* 30 */     if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && 
/* 31 */       hasAbility(player) && isAbilityItem(player.getItemInHand())) {
/* 32 */       event.setCancelled(true);
/* 33 */       player.updateInventory();
/*    */       
/* 35 */       if (isCooldown(player)) {
/*    */         return;
/*    */       }
/* 38 */       Snowball ball = (Snowball)player.launchProjectile(Snowball.class);
/* 39 */       ball.setMetadata("switch", (MetadataValue)new FixedMetadataValue((Plugin)GameMain.getInstance(), player));
/* 40 */       addCooldown(player, 7L);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
/* 47 */     if (event.getEntity() instanceof Player && event.getDamager().hasMetadata("switch")) {
/* 48 */       Player player = (Player)((MetadataValue)event.getDamager().getMetadata("switch").get(0)).value();
/*    */       
/* 50 */       if (player == null) {
/*    */         return;
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 57 */       Location loc = event.getEntity().getLocation().clone();
/* 58 */       event.getEntity().teleport(player.getLocation().clone());
/* 59 */       player.teleport(loc);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/SwitcherKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
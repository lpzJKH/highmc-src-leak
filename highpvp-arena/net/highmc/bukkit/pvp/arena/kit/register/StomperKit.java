/*    */ package net.highmc.bukkit.pvp.arena.kit.register;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.highmc.bukkit.pvp.arena.event.PlayerStompedEvent;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.minecraft.server.v1_8_R3.EnumParticle;
/*    */ import net.minecraft.server.v1_8_R3.Packet;
/*    */ import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ public class StomperKit
/*    */   extends Kit {
/*    */   public StomperKit() {
/* 24 */     super("Stomper", "Pise em cima de seus inimigos", Material.IRON_BOOTS, 21000, new ArrayList());
/*    */   }
/*    */   
/*    */   @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
/*    */   public void onDamage(EntityDamageEvent event) {
/* 29 */     if (!(event.getEntity() instanceof Player)) {
/*    */       return;
/*    */     }
/* 32 */     if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
/*    */       return;
/*    */     }
/* 35 */     Player stomper = (Player)event.getEntity();
/*    */     
/* 37 */     if (!hasAbility(stomper)) {
/*    */       return;
/*    */     }
/* 40 */     EntityDamageEvent.DamageCause cause = event.getCause();
/*    */     
/* 42 */     if (cause != EntityDamageEvent.DamageCause.FALL) {
/*    */       return;
/*    */     }
/* 45 */     double dmg = event.getDamage();
/*    */     
/* 47 */     for (Player stomped : Bukkit.getOnlinePlayers()) {
/* 48 */       if (stomped.getUniqueId() == stomper.getUniqueId() || stomped.isDead()) {
/*    */         continue;
/*    */       }
/* 51 */       if (stomped.getLocation().distance(stomper.getLocation()) > 5.0D) {
/*    */         continue;
/*    */       }
/* 54 */       if (stomped.isSneaking() && dmg > 8.0D) {
/* 55 */         dmg = 8.0D;
/*    */       }
/* 57 */       PlayerStompedEvent playerStomperEvent = new PlayerStompedEvent(stomped, stomper);
/* 58 */       Bukkit.getPluginManager().callEvent((Event)playerStomperEvent);
/*    */       
/* 60 */       if (!playerStomperEvent.isCancelled()) {
/* 61 */         stomped.damage(0.1D, (Entity)stomper);
/* 62 */         stomped.damage(dmg);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 67 */     for (int x = -3; x <= 3; x++) {
/* 68 */       for (int z = -3; z <= 3; z++) {
/* 69 */         Location effect = stomper.getLocation().clone().add(x, 0.0D, z);
/*    */         
/* 71 */         if (effect.distance(stomper.getLocation()) <= 3.0D) {
/*    */ 
/*    */ 
/*    */           
/* 75 */           PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SPELL_WITCH, true, (float)effect.getX(), (float)effect.getY(), (float)effect.getZ(), 0.1F, 0.1F, 0.1F, 1.0F, 30, new int[0]);
/*    */           
/* 77 */           Bukkit.getOnlinePlayers().stream().filter(viewer -> viewer.canSee(stomper))
/* 78 */             .forEach(viewer -> (((CraftPlayer)viewer).getHandle()).playerConnection.sendPacket((Packet)packet));
/*    */         } 
/*    */       } 
/*    */     } 
/* 82 */     stomper.getWorld().playSound(stomper.getLocation(), Sound.ANVIL_LAND, 1.0F, 1.0F);
/*    */     
/* 84 */     if (event.getDamage() > 4.0D)
/* 85 */       event.setDamage(4.0D); 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/kit/register/StomperKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
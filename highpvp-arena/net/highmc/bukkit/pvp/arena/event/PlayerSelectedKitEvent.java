/*    */ package net.highmc.bukkit.pvp.arena.event;
/*    */ 
/*    */ import net.highmc.bukkit.event.PlayerCancellableEvent;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.pvp.arena.menu.AbilityInventory;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class PlayerSelectedKitEvent extends PlayerCancellableEvent {
/*    */   private Kit kit;
/*    */   private AbilityInventory.InventoryType inventoryType;
/*    */   
/*    */   public Kit getKit() {
/* 13 */     return this.kit; } public AbilityInventory.InventoryType getInventoryType() {
/* 14 */     return this.inventoryType;
/*    */   }
/*    */   public PlayerSelectedKitEvent(Player player, Kit kit, AbilityInventory.InventoryType inventoryType) {
/* 17 */     super(player);
/* 18 */     this.kit = kit;
/* 19 */     this.inventoryType = inventoryType;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/event/PlayerSelectedKitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
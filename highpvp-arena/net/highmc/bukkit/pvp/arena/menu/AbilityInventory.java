/*    */ package net.highmc.bukkit.pvp.arena.menu;
/*    */ 
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.utils.item.ItemBuilder;
/*    */ import net.highmc.bukkit.utils.menu.MenuInventory;
/*    */ import net.highmc.bukkit.utils.menu.click.ClickType;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class AbilityInventory {
/*    */   public enum InventoryType {
/* 14 */     PRIMARY, SECONDARY;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityInventory(Player player, InventoryType selectClass) {
/* 19 */     MenuInventory menuInventory = new MenuInventory("§7§nSelecionar kit", 6);
/*    */     
/* 21 */     int slot = 10;
/*    */     
/* 23 */     for (Kit kit : GameMain.getInstance().getKitManager().getKitList()) {
/* 24 */       menuInventory.setItem(slot, (new ItemBuilder()).name("§a" + kit.getKitName())
/* 25 */           .lore("§7" + kit.getKitDescription()).type(kit.getKitType()).build(), (p, inv, type, stack, s) -> player.performCommand("kit " + kit.getName() + " " + ((selectClass == InventoryType.PRIMARY) ? "1" : "2")));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 30 */       if (slot % 9 == 7) {
/* 31 */         slot += 3;
/*    */         
/*    */         continue;
/*    */       } 
/* 35 */       slot++;
/*    */     } 
/*    */     
/* 38 */     menuInventory.open(player);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/menu/AbilityInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
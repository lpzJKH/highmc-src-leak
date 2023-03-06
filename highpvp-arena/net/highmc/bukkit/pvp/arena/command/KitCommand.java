/*    */ package net.highmc.bukkit.pvp.arena.command;
/*    */ 
/*    */ import net.highmc.bukkit.member.BukkitMember;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.event.PlayerSelectedKitEvent;
/*    */ import net.highmc.bukkit.pvp.arena.gamer.Gamer;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.pvp.arena.menu.AbilityInventory;
/*    */ import net.highmc.bukkit.utils.player.PlayerHelper;
/*    */ import net.highmc.command.CommandArgs;
/*    */ import net.highmc.command.CommandClass;
/*    */ import net.highmc.command.CommandFramework.Command;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ public class KitCommand
/*    */   implements CommandClass {
/*    */   @Command(name = "kit")
/*    */   public void kitCommand(CommandArgs cmdArgs) {
/* 21 */     if (!cmdArgs.isPlayer()) {
/*    */       return;
/*    */     }
/* 24 */     String[] args = cmdArgs.getArgs();
/* 25 */     Player player = ((BukkitMember)cmdArgs.getSender()).getPlayer();
/* 26 */     Gamer gamer = (Gamer)GameMain.getInstance().getGamerManager().getGamer(player.getUniqueId(), Gamer.class);
/*    */     
/* 28 */     if (!gamer.isSpawnProtection()) {
/* 29 */       player.sendMessage("§cVocê não pode usar kits fora da área de proteção!");
/*    */       
/*    */       return;
/*    */     } 
/* 33 */     if (args.length == 0) {
/* 34 */       player.sendMessage("§eUse /" + cmdArgs.getLabel() + " para selecionar um kit");
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     Kit kit = GameMain.getInstance().getKitManager().getKit(args[0]);
/*    */     
/* 40 */     if (kit == null) {
/* 41 */       player.sendMessage("§cO kit " + args[0] + " não existe!");
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 46 */     AbilityInventory.InventoryType inventoryType = (args.length >= 2) ? (args[1].equalsIgnoreCase("1") ? AbilityInventory.InventoryType.PRIMARY : AbilityInventory.InventoryType.SECONDARY) : AbilityInventory.InventoryType.PRIMARY;
/*    */ 
/*    */     
/* 49 */     if (((inventoryType == AbilityInventory.InventoryType.PRIMARY) ? gamer.getSecondary() : gamer
/* 50 */       .getPrimary()).equalsIgnoreCase(kit.getName())) {
/* 51 */       player.sendMessage("§cVocê já está usando esse kit!");
/*    */       
/*    */       return;
/*    */     } 
/* 55 */     if (inventoryType == AbilityInventory.InventoryType.PRIMARY) {
/* 56 */       gamer.setPrimaryKit(kit);
/*    */     } else {
/* 58 */       gamer.setSecondaryKit(kit);
/*    */     } 
/* 60 */     Bukkit.getPluginManager().callEvent((Event)new PlayerSelectedKitEvent(player, kit, inventoryType));
/*    */     
/* 62 */     player.sendMessage("§aVocê selecionou o kit " + kit.getName());
/* 63 */     PlayerHelper.title(player, "§a" + kit.getName(), "§fselecionado!");
/* 64 */     player.closeInventory();
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/command/KitCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.highmc.bukkit.pvp.command;
/*    */ 
/*    */ import net.highmc.CommonConst;
/*    */ import net.highmc.bukkit.BukkitCommon;
/*    */ import net.highmc.bukkit.member.BukkitMember;
/*    */ import net.highmc.bukkit.pvp.GameAPI;
/*    */ import net.highmc.bukkit.pvp.event.PlayerProtectionEvent;
/*    */ import net.highmc.bukkit.pvp.event.PlayerSpawnEvent;
/*    */ import net.highmc.command.CommandArgs;
/*    */ import net.highmc.command.CommandClass;
/*    */ import net.highmc.command.CommandFramework.Command;
/*    */ import net.highmc.command.CommandSender;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ 
/*    */ public class ServerCommand
/*    */   implements CommandClass {
/*    */   @Command(name = "spawn")
/*    */   public void spawnCommand(CommandArgs cmdArgs) {
/* 21 */     if (!cmdArgs.isPlayer()) {
/*    */       return;
/*    */     }
/* 24 */     Player player = ((BukkitMember)cmdArgs.getSenderAsMember(BukkitMember.class)).getPlayer();
/*    */     
/* 26 */     Bukkit.getPluginManager().callEvent((Event)new PlayerSpawnEvent(player));
/* 27 */     player.teleport(BukkitCommon.getInstance().getLocationManager().getLocation("spawn"));
/* 28 */     player.sendMessage("§aTeletransportado para o spawn.");
/* 29 */     GameAPI.getInstance().getGamerManager().getGamer(player.getUniqueId()).setSpawnProtection(true);
/* 30 */     Bukkit.getPluginManager().callEvent((Event)new PlayerProtectionEvent(player, true));
/*    */   }
/*    */   
/*    */   @Command(name = "setfulliron", permission = "command.setfulliron")
/*    */   public void setfullironCommand(CommandArgs cmdArgs) {
/* 35 */     CommandSender sender = cmdArgs.getSender();
/*    */     
/* 37 */     GameAPI.getInstance().setFullIron(!GameAPI.getInstance().isFullIron());
/* 38 */     sender.sendMessage("§aO modo do servidor foi alterado para " + (
/* 39 */         GameAPI.getInstance().isFullIron() ? "FullIron" : "Simulator") + ".");
/*    */   }
/*    */   
/*    */   @Command(name = "setprotection", permission = "command.setprotection")
/*    */   public void setprotectionCommand(CommandArgs cmdArgs) {
/* 44 */     CommandSender sender = cmdArgs.getSender();
/* 45 */     String[] args = cmdArgs.getArgs();
/*    */     
/* 47 */     if (args.length == 0) {
/* 48 */       sender.sendMessage(" §e» §fUse §a/" + cmdArgs
/* 49 */           .getLabel() + " <radius>§f para alterar o raio de proteção do spawn.");
/*    */       
/*    */       return;
/*    */     } 
/* 53 */     Double integer = null;
/*    */     
/*    */     try {
/* 56 */       integer = Double.valueOf(args[0]);
/* 57 */     } catch (NumberFormatException numberFormatException) {}
/*    */ 
/*    */ 
/*    */     
/* 61 */     GameAPI.getInstance().setProtectionRadius(integer.doubleValue());
/* 62 */     sender.sendMessage("§aO raio de proteção do spawn foi alterado para " + CommonConst.DECIMAL_FORMAT
/* 63 */         .format(integer) + ".");
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/command/ServerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
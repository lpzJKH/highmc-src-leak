/*     */ package net.highmc.bukkit.pvp.listener;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class SignListener
/*     */   implements Listener {
/*     */   @EventHandler
/*     */   public void onPlayerInteract(PlayerInteractEvent event) {
/*  20 */     if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
/*     */       return;
/*     */     }
/*  23 */     if (event.getClickedBlock().getType() != Material.WALL_SIGN && event
/*  24 */       .getClickedBlock().getType() != Material.SIGN_POST) {
/*     */       return;
/*     */     }
/*  27 */     Player player = event.getPlayer();
/*     */     
/*  29 */     Sign sign = (Sign)event.getClickedBlock().getState();
/*  30 */     String[] lines = sign.getLines();
/*     */     
/*  32 */     if (lines[1].toLowerCase().contains("sopas")) {
/*  33 */       Inventory soup = Bukkit.createInventory(null, 54, "§7Sopas");
/*     */       
/*  35 */       for (int i = 0; i < 54; i++) {
/*  36 */         soup.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
/*     */       }
/*     */       
/*  39 */       player.openInventory(soup);
/*  40 */     } else if (lines[1].toLowerCase().contains("recraft")) {
/*  41 */       Inventory recraft = Bukkit.createInventory(null, 9, "§7Sopas");
/*     */       
/*  43 */       recraft.setItem(3, new ItemStack(Material.BOWL, 64));
/*  44 */       recraft.setItem(4, new ItemStack(Material.RED_MUSHROOM, 64));
/*  45 */       recraft.setItem(5, new ItemStack(Material.BROWN_MUSHROOM, 64));
/*     */       
/*  47 */       player.openInventory(recraft);
/*  48 */     } else if (lines[1].toLowerCase().contains("cactus")) {
/*  49 */       Inventory cactu = Bukkit.createInventory(null, 9, "§7Sopas");
/*     */       
/*  51 */       cactu.setItem(3, new ItemStack(Material.BOWL, 64));
/*  52 */       cactu.setItem(4, new ItemStack(Material.CACTUS, 64));
/*  53 */       cactu.setItem(5, new ItemStack(Material.CACTUS, 64));
/*     */       
/*  55 */       player.openInventory(cactu);
/*  56 */     } else if (lines[1].toLowerCase().contains("cocoa")) {
/*  57 */       Inventory cocoa = Bukkit.createInventory(null, 9, "§7Cocoa");
/*     */       
/*  59 */       cocoa.setItem(2, new ItemStack(Material.BOWL, 64));
/*  60 */       cocoa.setItem(3, new ItemStack(Material.INK_SACK, 64, (short)3));
/*     */       
/*  62 */       cocoa.setItem(5, new ItemStack(Material.BOWL, 64));
/*  63 */       cocoa.setItem(6, new ItemStack(Material.INK_SACK, 64, (short)3));
/*     */       
/*  65 */       player.openInventory(cocoa);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void onSignChange(SignChangeEvent event) {
/* 166 */     String line = event.getLine(0);
/*     */     
/* 168 */     if (line.equalsIgnoreCase("sopa") || line.equalsIgnoreCase("sopas")) {
/* 169 */       event.setLine(0, "§5High§fMC");
/* 170 */       event.setLine(1, "§bSopas");
/* 171 */       event.setLine(2, "§6§m>-----<");
/* 172 */       event.setLine(3, " ");
/* 173 */     } else if (line.equalsIgnoreCase("recraft") || line.equalsIgnoreCase("recrafts")) {
/* 174 */       event.setLine(0, "§5High§fMC");
/* 175 */       event.setLine(1, "§eRecraft");
/* 176 */       event.setLine(2, "§6§m>-----<");
/* 177 */       event.setLine(3, " ");
/* 178 */     } else if (line.equalsIgnoreCase("cocoa") || line.equalsIgnoreCase("cocoabean")) {
/* 179 */       event.setLine(0, "§5High§fMC");
/* 180 */       event.setLine(1, "§cCocoabean");
/* 181 */       event.setLine(2, "§6§m>-----<");
/* 182 */       event.setLine(3, " ");
/* 183 */     } else if (line.equalsIgnoreCase("cactu") || line.equalsIgnoreCase("cactus")) {
/* 184 */       event.setLine(0, "§5High§fMC");
/* 185 */       event.setLine(1, "§aCactus");
/* 186 */       event.setLine(2, "§6§m>-----<");
/* 187 */       event.setLine(3, " ");
/* 188 */     } else if (line.equalsIgnoreCase("dificil")) {
/* 189 */       event.setLine(0, "§6Lava");
/* 190 */       event.setLine(1, "§c§lHARD");
/* 191 */       event.setLine(2, "§6§m>-----<");
/* 192 */       event.setLine(3, " ");
/* 193 */     } else if (line.equalsIgnoreCase("facil")) {
/* 194 */       event.setLine(0, "§6Lava");
/* 195 */       event.setLine(1, "§a§lEASY");
/* 196 */       event.setLine(2, "§6§m>-----<");
/* 197 */       event.setLine(3, " ");
/* 198 */     } else if (line.equalsIgnoreCase("medio")) {
/* 199 */       event.setLine(0, "§6Lava");
/* 200 */       event.setLine(1, "§e§lMEDIUM");
/* 201 */       event.setLine(2, "§6§m>-----<");
/* 202 */       event.setLine(3, " ");
/* 203 */     } else if (line.equalsIgnoreCase("extreme")) {
/* 204 */       event.setLine(0, "§6Lava");
/* 205 */       event.setLine(1, "§4§lEXTREME");
/* 206 */       event.setLine(2, "§6§m>-----<");
/* 207 */       event.setLine(3, " ");
/* 208 */     } else if (line.contains(":")) {
/* 209 */       String[] code = line.split(":");
/* 210 */       if (code.length > 1)
/* 211 */         if (code[0].equalsIgnoreCase("money")) {
/*     */           try {
/* 213 */             int quantity = Integer.valueOf(code[1]).intValue();
/* 214 */             event.setLine(0, "§6§lMOEDAS");
/* 215 */             event.setLine(1, "§e§l" + quantity);
/* 216 */             event.setLine(2, " ");
/* 217 */             event.setLine(3, "§a§lClique!");
/* 218 */           } catch (NumberFormatException numberFormatException) {}
/*     */         }
/* 220 */         else if (code[0].equalsIgnoreCase("ticket")) {
/*     */           try {
/* 222 */             int quantity = Integer.valueOf(code[1]).intValue();
/* 223 */             event.setLine(0, "§b§lTICKET");
/* 224 */             event.setLine(1, "§3§l" + quantity);
/* 225 */             event.setLine(2, " ");
/* 226 */             event.setLine(3, "§a§lClique!");
/* 227 */           } catch (NumberFormatException numberFormatException) {}
/*     */         }
/* 229 */         else if (code[0].equalsIgnoreCase("doublexp")) {
/*     */           try {
/* 231 */             int quantity = Integer.valueOf(code[1]).intValue();
/* 232 */             event.setLine(0, "§3§lDOUBLEXP");
/* 233 */             event.setLine(1, "§b§l" + quantity);
/* 234 */             event.setLine(2, " ");
/* 235 */             event.setLine(3, "§a§lClique!");
/* 236 */           } catch (NumberFormatException numberFormatException) {}
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/listener/SignListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
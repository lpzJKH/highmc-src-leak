/*    */ package net.highmc.bukkit.pvp.arena.gamer;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*    */ 
/*    */ public class Gamer extends Gamer {
/*    */   private Kit primaryKit;
/*    */   private Kit secondaryKit;
/*    */   
/* 11 */   public Kit getPrimaryKit() { return this.primaryKit; } public Kit getSecondaryKit() {
/* 12 */     return this.secondaryKit;
/*    */   }
/*    */   public Gamer(UUID uniqueId) {
/* 15 */     super(uniqueId);
/*    */   }
/*    */   
/*    */   public String getPrimary() {
/* 19 */     return (this.primaryKit == null) ? "Nenhum" : this.primaryKit.getName();
/*    */   }
/*    */   
/*    */   public String getSecondary() {
/* 23 */     return (this.secondaryKit == null) ? "Nenhum" : this.secondaryKit.getName();
/*    */   }
/*    */   
/*    */   public void setPrimaryKit(Kit primaryKit) {
/* 27 */     if (this.primaryKit != null) {
/* 28 */       this.primaryKit.removePlayer(getUniqueId());
/*    */     }
/*    */     
/* 31 */     this.primaryKit = primaryKit;
/*    */     
/* 33 */     if (this.primaryKit != null)
/* 34 */       this.primaryKit.addPlayer(getUniqueId()); 
/*    */   }
/*    */   
/*    */   public void setSecondaryKit(Kit secondaryKit) {
/* 38 */     if (this.secondaryKit != null) {
/* 39 */       this.secondaryKit.removePlayer(getUniqueId());
/*    */     }
/*    */     
/* 42 */     this.secondaryKit = secondaryKit;
/*    */     
/* 44 */     if (this.secondaryKit != null)
/* 45 */       this.secondaryKit.addPlayer(getUniqueId()); 
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/gamer/Gamer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
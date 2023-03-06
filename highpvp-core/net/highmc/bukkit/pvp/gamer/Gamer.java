/*    */ package net.highmc.bukkit.pvp.gamer;
/*    */ 
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Gamer
/*    */ {
/*    */   private final UUID uniqueId;
/*    */   private boolean spawnProtection;
/*    */   
/*    */   public Gamer(UUID uniqueId) {
/* 14 */     this.spawnProtection = true; this.uniqueId = uniqueId; } public boolean isSpawnProtection() { return this.spawnProtection; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UUID getUniqueId() {
/* 24 */     return this.uniqueId;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSpawnProtection(boolean spawnProtection) {
/* 35 */     this.spawnProtection = spawnProtection;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasSpawnProtection() {
/* 46 */     return this.spawnProtection;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/gamer/Gamer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
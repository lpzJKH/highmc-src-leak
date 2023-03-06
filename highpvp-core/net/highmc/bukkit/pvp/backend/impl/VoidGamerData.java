/*    */ package net.highmc.bukkit.pvp.backend.impl;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.highmc.bukkit.pvp.backend.GamerData;
/*    */ import net.highmc.bukkit.pvp.gamer.Gamer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VoidGamerData
/*    */   implements GamerData
/*    */ {
/*    */   public <T extends Gamer> T loadGamer(UUID uniqueId, Class<T> clazz) {
/*    */     try {
/* 14 */       return (T)clazz.getConstructor(new Class[] { UUID.class }).newInstance(new Object[] { uniqueId });
/* 15 */     } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
/*    */       
/* 17 */       e.printStackTrace();
/*    */       
/* 19 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean deleteGamer(UUID uniqueId) {
/* 24 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean createGamer(Gamer gamer) {
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/backend/impl/VoidGamerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
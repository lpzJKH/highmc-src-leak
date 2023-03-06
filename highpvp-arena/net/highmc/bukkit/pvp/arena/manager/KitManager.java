/*    */ package net.highmc.bukkit.pvp.arena.manager;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.highmc.bukkit.pvp.arena.GameMain;
/*    */ import net.highmc.bukkit.pvp.arena.kit.Kit;
/*    */ import net.highmc.utils.ClassGetter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KitManager
/*    */ {
/* 16 */   private List<Kit> kitList = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Kit defaultKit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addKit(Kit kit) {
/* 41 */     this.kitList.add(kit);
/* 42 */     kit.register();
/*    */   }
/*    */   
/*    */   public Kit getKit(String kitName) {
/* 46 */     return this.kitList.stream().filter(kit -> kit.getKitName().equalsIgnoreCase(kitName)).findFirst().orElse(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Kit> getKitList() {
/* 87 */     return this.kitList;
/*    */   }
/*    */   
/*    */   public Kit getDefaultKit() {
/* 91 */     return this.defaultKit;
/*    */   }
/*    */   
/*    */   public KitManager() {
/*    */     for (Class<?> kitClass : (Iterable<Class<?>>)ClassGetter.getClassesForPackage(GameMain.getInstance().getClass(), "net.highmc.bukkit.pvp.arena.kit.register")) {
/*    */       if (Kit.class.isAssignableFrom(kitClass))
/*    */         try {
/*    */           Kit kit = (Kit)kitClass.newInstance();
/*    */           addKit(kit);
/*    */         } catch (Exception e) {
/*    */           e.printStackTrace();
/*    */           System.out.print("Erro ao carregar o kit " + kitClass.getSimpleName());
/*    */         }  
/*    */     } 
/*    */     this.kitList.sort((o1, o2) -> o1.getKitName().compareTo(o2.getKitName()));
/*    */     Kit item = this.kitList.stream().filter(kit -> kit.getKitName().equalsIgnoreCase("pvp")).findFirst().orElse(null);
/*    */     int itemPos = this.kitList.indexOf(item);
/*    */     this.defaultKit = item;
/*    */     this.kitList.remove(itemPos);
/*    */     this.kitList.add(0, item);
/*    */   }
/*    */ }


/* Location:              /home/uni/Área de trabalho/aaa/HighPvP.jar!/net/highmc/bukkit/pvp/arena/manager/KitManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
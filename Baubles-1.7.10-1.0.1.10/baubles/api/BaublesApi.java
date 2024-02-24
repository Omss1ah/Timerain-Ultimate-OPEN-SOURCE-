package baubles.api;

import cpw.mods.fml.common.FMLLog;
import java.lang.reflect.Method;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class BaublesApi {
   static Method getBaubles;

   public static IInventory getBaubles(EntityPlayer player) {
      IInventory ot = null;

      try {
         if (getBaubles == null) {
            Class<?> fake = Class.forName("baubles.common.lib.PlayerHandler");
            getBaubles = fake.getMethod("getPlayerBaubles", EntityPlayer.class);
         }

         ot = (IInventory)getBaubles.invoke((Object)null, player);
      } catch (Exception var3) {
         FMLLog.warning("[Baubles API] Could not invoke baubles.common.lib.PlayerHandler method getPlayerBaubles", new Object[0]);
      }

      return ot;
   }
}

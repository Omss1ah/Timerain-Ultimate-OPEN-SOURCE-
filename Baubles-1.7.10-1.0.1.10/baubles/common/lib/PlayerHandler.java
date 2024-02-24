package baubles.common.lib;

import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerHandler {
   private static HashMap<String, InventoryBaubles> playerBaubles = new HashMap();

   public static void clearPlayerBaubles(EntityPlayer player) {
      playerBaubles.remove(player.func_70005_c_());
   }

   public static InventoryBaubles getPlayerBaubles(EntityPlayer player) {
      if (!playerBaubles.containsKey(player.func_70005_c_())) {
         InventoryBaubles inventory = new InventoryBaubles(player);
         playerBaubles.put(player.func_70005_c_(), inventory);
      }

      return (InventoryBaubles)playerBaubles.get(player.func_70005_c_());
   }

   public static void setPlayerBaubles(EntityPlayer player, InventoryBaubles inventory) {
      playerBaubles.put(player.func_70005_c_(), inventory);
   }

   public static void loadPlayerBaubles(EntityPlayer player, File file1, File file2) {
      if (player != null && !player.field_70170_p.field_72995_K) {
         try {
            NBTTagCompound data = null;
            boolean save = false;
            FileInputStream fileinputstream;
            if (file1 != null && file1.exists()) {
               try {
                  fileinputstream = new FileInputStream(file1);
                  data = CompressedStreamTools.func_74796_a(fileinputstream);
                  fileinputstream.close();
               } catch (Exception var7) {
                  var7.printStackTrace();
               }
            }

            if (file1 == null || !file1.exists() || data == null || data.func_82582_d()) {
               Baubles.log.warn("Data not found for " + player.func_70005_c_() + ". Trying to load backup data.");
               if (file2 != null && file2.exists()) {
                  try {
                     fileinputstream = new FileInputStream(file2);
                     data = CompressedStreamTools.func_74796_a(fileinputstream);
                     fileinputstream.close();
                     save = true;
                  } catch (Exception var6) {
                     var6.printStackTrace();
                  }
               }
            }

            if (data != null) {
               InventoryBaubles inventory = new InventoryBaubles(player);
               inventory.readNBT(data);
               playerBaubles.put(player.func_70005_c_(), inventory);
               if (save) {
                  savePlayerBaubles(player, file1, file2);
               }
            }
         } catch (Exception var8) {
            Baubles.log.fatal("Error loading baubles inventory");
            var8.printStackTrace();
         }
      }

   }

   public static void savePlayerBaubles(EntityPlayer player, File file1, File file2) {
      if (player != null && !player.field_70170_p.field_72995_K) {
         try {
            if (file1 != null && file1.exists()) {
               try {
                  Files.copy(file1, file2);
               } catch (Exception var7) {
                  Baubles.log.error("Could not backup old baubles file for player " + player.func_70005_c_());
               }
            }

            try {
               if (file1 != null) {
                  InventoryBaubles inventory = getPlayerBaubles(player);
                  NBTTagCompound data = new NBTTagCompound();
                  inventory.saveNBT(data);
                  FileOutputStream fileoutputstream = new FileOutputStream(file1);
                  CompressedStreamTools.func_74799_a(data, fileoutputstream);
                  fileoutputstream.close();
               }
            } catch (Exception var8) {
               Baubles.log.error("Could not save baubles file for player " + player.func_70005_c_());
               var8.printStackTrace();
               if (file1.exists()) {
                  try {
                     file1.delete();
                  } catch (Exception var6) {
                  }
               }
            }
         } catch (Exception var9) {
            Baubles.log.fatal("Error saving baubles inventory");
            var9.printStackTrace();
         }
      }

   }
}

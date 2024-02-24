package baubles.common.event;

import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.google.common.io.Files;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;

public class EventHandlerEntity {
   @SubscribeEvent
   public void playerTick(LivingUpdateEvent event) {
      if (event.entity instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)event.entity;
         InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

         for(int a = 0; a < baubles.func_70302_i_(); ++a) {
            if (baubles.func_70301_a(a) != null && baubles.func_70301_a(a).func_77973_b() instanceof IBauble) {
               ((IBauble)baubles.func_70301_a(a).func_77973_b()).onWornTick(baubles.func_70301_a(a), player);
            }
         }
      }

   }

   @SubscribeEvent
   public void playerDeath(PlayerDropsEvent event) {
      if (event.entity instanceof EntityPlayer && !event.entity.field_70170_p.field_72995_K && !event.entity.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
         PlayerHandler.getPlayerBaubles(event.entityPlayer).dropItemsAt(event.drops, event.entityPlayer);
      }

   }

   @SubscribeEvent
   public void playerLoad(LoadFromFile event) {
      PlayerHandler.clearPlayerBaubles(event.entityPlayer);
      File file1 = this.getPlayerFile("baub", event.playerDirectory, event.entityPlayer.func_70005_c_());
      if (!file1.exists()) {
         File filep = event.getPlayerFile("baub");
         File fb;
         if (filep.exists()) {
            try {
               Files.copy(filep, file1);
               Baubles.log.info("Using and converting UUID Baubles savefile for " + event.entityPlayer.func_70005_c_());
               filep.delete();
               fb = event.getPlayerFile("baubback");
               if (fb.exists()) {
                  fb.delete();
               }
            } catch (IOException var7) {
            }
         } else {
            fb = getLegacyFileFromPlayer(event.entityPlayer);
            if (fb.exists()) {
               try {
                  Files.copy(fb, file1);
                  Baubles.log.info("Using pre MC 1.7.10 Baubles savefile for " + event.entityPlayer.func_70005_c_());
               } catch (IOException var6) {
               }
            }
         }
      }

      PlayerHandler.loadPlayerBaubles(event.entityPlayer, file1, this.getPlayerFile("baubback", event.playerDirectory, event.entityPlayer.func_70005_c_()));
      EventHandlerNetwork.syncBaubles(event.entityPlayer);
   }

   public File getPlayerFile(String suffix, File playerDirectory, String playername) {
      if ("dat".equals(suffix)) {
         throw new IllegalArgumentException("The suffix 'dat' is reserved");
      } else {
         return new File(playerDirectory, playername + "." + suffix);
      }
   }

   public static File getLegacyFileFromPlayer(EntityPlayer player) {
      try {
         File playersDirectory = new File(player.field_70170_p.func_72860_G().func_75765_b(), "players");
         return new File(playersDirectory, player.func_70005_c_() + ".baub");
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   @SubscribeEvent
   public void playerSave(SaveToFile event) {
      PlayerHandler.savePlayerBaubles(event.entityPlayer, this.getPlayerFile("baub", event.playerDirectory, event.entityPlayer.func_70005_c_()), this.getPlayerFile("baubback", event.playerDirectory, event.entityPlayer.func_70005_c_()));
   }
}

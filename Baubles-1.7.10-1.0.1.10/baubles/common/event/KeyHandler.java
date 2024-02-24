package baubles.common.event;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

public class KeyHandler {
   public KeyBinding key = new KeyBinding("Baubles Inventory", 48, "key.categories.inventory");

   public KeyHandler() {
      ClientRegistry.registerKeyBinding(this.key);
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void playerTick(PlayerTickEvent event) {
      if (event.side != Side.SERVER) {
         if (event.phase == Phase.START && this.key.func_151470_d() && FMLClientHandler.instance().getClient().field_71415_G) {
            PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory(event.player));
         }

      }
   }
}

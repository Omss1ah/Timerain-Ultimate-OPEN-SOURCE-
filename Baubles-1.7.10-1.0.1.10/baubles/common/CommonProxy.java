package baubles.common;

import baubles.common.container.ContainerPlayerExpanded;
import baubles.common.event.KeyHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
   public KeyHandler keyHandler;

   public void registerHandlers() {
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      switch(ID) {
      case 0:
         return new ContainerPlayerExpanded(player.field_71071_by, !world.field_72995_K, player);
      default:
         return null;
      }
   }

   public World getClientWorld() {
      return null;
   }

   public void registerKeyBindings() {
   }
}

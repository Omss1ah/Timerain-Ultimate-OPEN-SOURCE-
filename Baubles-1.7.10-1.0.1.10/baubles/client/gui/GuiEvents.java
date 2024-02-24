package baubles.client.gui;

import baubles.common.network.PacketHandler;
import baubles.common.network.PacketOpenBaublesInventory;
import baubles.common.network.PacketOpenNormalInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Method;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;

public class GuiEvents {
   static Method isNEIHidden;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void guiPostInit(Post event) {
      if (event.gui instanceof GuiInventory || event.gui instanceof GuiPlayerExpanded) {
         int xSize = 176;
         int ySize = 166;
         int guiLeft = (event.gui.field_146294_l - xSize) / 2;
         int guiTop = (event.gui.field_146295_m - ySize) / 2;
         if (!event.gui.field_146297_k.field_71439_g.func_70651_bq().isEmpty() && this.isNeiHidden()) {
            guiLeft = 160 + (event.gui.field_146294_l - xSize - 200) / 2;
         }

         event.buttonList.add(new GuiBaublesButton(55, guiLeft + 66, guiTop + 9, 10, 10, I18n.func_135052_a(event.gui instanceof GuiInventory ? "button.baubles" : "button.normal", new Object[0])));
      }

   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void guiPostAction(net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Post event) {
      if (event.gui instanceof GuiInventory && event.button.field_146127_k == 55) {
         PacketHandler.INSTANCE.sendToServer(new PacketOpenBaublesInventory(event.gui.field_146297_k.field_71439_g));
      }

      if (event.gui instanceof GuiPlayerExpanded && event.button.field_146127_k == 55) {
         event.gui.field_146297_k.func_147108_a(new GuiInventory(event.gui.field_146297_k.field_71439_g));
         PacketHandler.INSTANCE.sendToServer(new PacketOpenNormalInventory(event.gui.field_146297_k.field_71439_g));
      }

   }

   boolean isNeiHidden() {
      boolean hidden = true;

      try {
         if (isNEIHidden == null) {
            Class fake = Class.forName("codechicken.nei.NEIClientConfig");
            isNEIHidden = fake.getMethod("isHidden");
         }

         hidden = (Boolean)isNEIHidden.invoke((Object)null);
      } catch (Exception var3) {
      }

      return hidden;
   }
}

package baubles.common.network;

import baubles.common.Baubles;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketOpenBaublesInventory implements IMessage, IMessageHandler<PacketOpenBaublesInventory, IMessage> {
   public PacketOpenBaublesInventory() {
   }

   public PacketOpenBaublesInventory(EntityPlayer player) {
   }

   public void toBytes(ByteBuf buffer) {
   }

   public void fromBytes(ByteBuf buffer) {
   }

   public IMessage onMessage(PacketOpenBaublesInventory message, MessageContext ctx) {
      ctx.getServerHandler().field_147369_b.openGui(Baubles.instance, 0, ctx.getServerHandler().field_147369_b.field_70170_p, (int)ctx.getServerHandler().field_147369_b.field_70165_t, (int)ctx.getServerHandler().field_147369_b.field_70163_u, (int)ctx.getServerHandler().field_147369_b.field_70161_v);
      return null;
   }
}

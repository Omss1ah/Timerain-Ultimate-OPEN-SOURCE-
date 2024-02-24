package baubles.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class PacketOpenNormalInventory implements IMessage, IMessageHandler<PacketOpenNormalInventory, IMessage> {
   public PacketOpenNormalInventory() {
   }

   public PacketOpenNormalInventory(EntityPlayer player) {
   }

   public void toBytes(ByteBuf buffer) {
   }

   public void fromBytes(ByteBuf buffer) {
   }

   public IMessage onMessage(PacketOpenNormalInventory message, MessageContext ctx) {
      ctx.getServerHandler().field_147369_b.field_71070_bA.func_75134_a(ctx.getServerHandler().field_147369_b);
      ctx.getServerHandler().field_147369_b.field_71070_bA = ctx.getServerHandler().field_147369_b.field_71069_bz;
      return null;
   }
}

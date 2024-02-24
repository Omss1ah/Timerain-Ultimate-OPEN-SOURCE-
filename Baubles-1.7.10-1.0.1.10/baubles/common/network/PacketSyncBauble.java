package baubles.common.network;

import baubles.common.Baubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;

public class PacketSyncBauble implements IMessage, IMessageHandler<PacketSyncBauble, IMessage> {
   int slot;
   int playerId;
   ItemStack bauble = null;

   public PacketSyncBauble() {
   }

   public PacketSyncBauble(EntityPlayer player, int slot) {
      this.slot = slot;
      this.bauble = PlayerHandler.getPlayerBaubles(player).func_70301_a(slot);
      this.playerId = player.func_145782_y();
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.slot);
      buffer.writeInt(this.playerId);
      PacketBuffer pb = new PacketBuffer(buffer);

      try {
         pb.func_150788_a(this.bauble);
      } catch (IOException var4) {
      }

   }

   public void fromBytes(ByteBuf buffer) {
      this.slot = buffer.readByte();
      this.playerId = buffer.readInt();
      PacketBuffer pb = new PacketBuffer(buffer);

      try {
         this.bauble = pb.func_150791_c();
      } catch (IOException var4) {
      }

   }

   public IMessage onMessage(PacketSyncBauble message, MessageContext ctx) {
      World world = Baubles.proxy.getClientWorld();
      if (world == null) {
         return null;
      } else {
         Entity p = world.func_73045_a(message.playerId);
         if (p != null && p instanceof EntityPlayer) {
            PlayerHandler.getPlayerBaubles((EntityPlayer)p).stackList[message.slot] = message.bauble;
         }

         return null;
      }
   }
}

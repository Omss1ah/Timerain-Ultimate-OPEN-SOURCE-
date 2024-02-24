package baubles.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
   public static final SimpleNetworkWrapper INSTANCE;

   public static void init() {
      INSTANCE.registerMessage(PacketOpenBaublesInventory.class, PacketOpenBaublesInventory.class, 0, Side.SERVER);
      INSTANCE.registerMessage(PacketOpenNormalInventory.class, PacketOpenNormalInventory.class, 1, Side.SERVER);
      INSTANCE.registerMessage(PacketSyncBauble.class, PacketSyncBauble.class, 2, Side.CLIENT);
   }

   static {
      INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("Baubles".toLowerCase());
   }
}
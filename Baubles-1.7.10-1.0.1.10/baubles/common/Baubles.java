package baubles.common;

import baubles.common.event.EventHandlerEntity;
import baubles.common.event.EventHandlerNetwork;
import baubles.common.network.PacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import java.io.File;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
   modid = "Baubles",
   name = "Baubles",
   version = "1.0.1.10",
   dependencies = "required-after:Forge@[10.13.2,);"
)
public class Baubles {
   public static final String MODID = "Baubles";
   public static final String MODNAME = "Baubles";
   public static final String VERSION = "1.0.1.10";
   @SidedProxy(
      clientSide = "baubles.client.ClientProxy",
      serverSide = "baubles.common.CommonProxy"
   )
   public static CommonProxy proxy;
   @Instance("Baubles")
   public static Baubles instance;
   public EventHandlerEntity entityEventHandler;
   public EventHandlerNetwork entityEventNetwork;
   public File modDir;
   public static final Logger log = LogManager.getLogger("Baubles");
   public static final int GUI = 0;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      event.getModMetadata().version = "1.0.1.10";
      this.modDir = event.getModConfigurationDirectory();

      try {
         Config.initialize(event.getSuggestedConfigurationFile());
      } catch (Exception var6) {
         log.error("BAUBLES has a problem loading it's configuration");
      } finally {
         if (Config.config != null) {
            Config.save();
         }

      }

      PacketHandler.init();
      this.entityEventHandler = new EventHandlerEntity();
      this.entityEventNetwork = new EventHandlerNetwork();
      MinecraftForge.EVENT_BUS.register(this.entityEventHandler);
      FMLCommonHandler.instance().bus().register(this.entityEventNetwork);
      proxy.registerHandlers();
      Config.save();
   }

   @EventHandler
   public void init(FMLInitializationEvent evt) {
      NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
      proxy.registerKeyBindings();
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent evt) {
      Config.initRecipe();
   }
}

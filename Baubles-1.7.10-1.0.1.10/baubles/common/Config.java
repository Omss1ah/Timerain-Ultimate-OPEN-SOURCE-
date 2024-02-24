package baubles.common;

import baubles.common.items.ItemRing;
import cpw.mods.fml.common.registry.GameRegistry;
import java.io.File;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

public class Config {
   public static Configuration config;
   public static Item itemRing;

   public static void initialize(File file) {
      config = new Configuration(file);
      config.load();
      itemRing = (new ItemRing()).func_77655_b("Ring");
      GameRegistry.registerItem(itemRing, "Ring", "Baubles");
      config.save();
   }

   public static void save() {
      config.save();
   }

   public static void initRecipe() {
   }
}

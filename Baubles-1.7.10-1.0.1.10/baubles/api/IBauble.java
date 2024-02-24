package baubles.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IBauble {
   BaubleType getBaubleType(ItemStack var1);

   void onWornTick(ItemStack var1, EntityLivingBase var2);

   void onEquipped(ItemStack var1, EntityLivingBase var2);

   void onUnequipped(ItemStack var1, EntityLivingBase var2);

   boolean canEquip(ItemStack var1, EntityLivingBase var2);

   boolean canUnequip(ItemStack var1, EntityLivingBase var2);
}

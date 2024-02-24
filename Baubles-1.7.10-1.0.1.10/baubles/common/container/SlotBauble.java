package baubles.common.container;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBauble extends Slot {
   BaubleType type;

   public SlotBauble(IInventory par2IInventory, BaubleType type, int par3, int par4, int par5) {
      super(par2IInventory, par3, par4, par5);
      this.type = type;
   }

   public boolean func_75214_a(ItemStack stack) {
      return stack != null && stack.func_77973_b() != null && stack.func_77973_b() instanceof IBauble && ((IBauble)stack.func_77973_b()).getBaubleType(stack) == this.type && ((IBauble)stack.func_77973_b()).canEquip(stack, (EntityLivingBase)((InventoryBaubles)this.field_75224_c).player.get());
   }

   public boolean func_82869_a(EntityPlayer player) {
      return this.func_75211_c() != null && ((IBauble)this.func_75211_c().func_77973_b()).canUnequip(this.func_75211_c(), player);
   }

   public int func_75219_a() {
      return 1;
   }
}

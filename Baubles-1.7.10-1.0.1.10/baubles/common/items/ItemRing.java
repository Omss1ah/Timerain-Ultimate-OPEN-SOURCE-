package baubles.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemRing extends Item implements IBauble {
   public IIcon icon;

   public ItemRing() {
      this.func_77625_d(1);
      this.func_77627_a(true);
      this.func_77656_e(0);
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IIconRegister ir) {
      this.icon = ir.func_94245_a("baubles:ring");
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_77617_a(int meta) {
      return this.icon;
   }

   @SideOnly(Side.CLIENT)
   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
      par3List.add(new ItemStack(this, 1, 0));
   }

   public BaubleType getBaubleType(ItemStack itemstack) {
      return BaubleType.RING;
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (!par2World.field_72995_K) {
         InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(par3EntityPlayer);

         for(int i = 0; i < baubles.func_70302_i_(); ++i) {
            if (baubles.func_70301_a(i) == null && baubles.func_94041_b(i, par1ItemStack)) {
               baubles.func_70299_a(i, par1ItemStack.func_77946_l());
               if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
                  par3EntityPlayer.field_71071_by.func_70299_a(par3EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
               }

               this.onEquipped(par1ItemStack, par3EntityPlayer);
               break;
            }
         }
      }

      return par1ItemStack;
   }

   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
      if (itemstack.func_77960_j() == 0 && !player.func_70644_a(Potion.field_76422_e)) {
         player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, 40, 0, true));
      }

   }

   public boolean hasEffect(ItemStack par1ItemStack, int a) {
      return true;
   }

   public EnumRarity func_77613_e(ItemStack par1ItemStack) {
      return EnumRarity.rare;
   }

   public String func_77667_c(ItemStack par1ItemStack) {
      return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
   }

   public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
      if (!player.field_70170_p.field_72995_K) {
         player.field_70170_p.func_72956_a(player, "random.orb", 0.1F, 1.3F);
      }

   }

   public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
   }

   public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
      return true;
   }

   public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
      return true;
   }
}

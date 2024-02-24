package baubles.common.container;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.lib.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerPlayerExpanded extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
   public IInventory craftResult = new InventoryCraftResult();
   public InventoryBaubles baubles;
   public boolean isLocalWorld;
   private final EntityPlayer thePlayer;

   public ContainerPlayerExpanded(InventoryPlayer playerInv, boolean par2, EntityPlayer player) {
      this.isLocalWorld = par2;
      this.thePlayer = player;
      this.baubles = new InventoryBaubles(player);
      this.baubles.setEventHandler(this);
      if (!player.field_70170_p.field_72995_K) {
         this.baubles.stackList = PlayerHandler.getPlayerBaubles(player).stackList;
      }

      this.func_75146_a(new SlotCrafting(playerInv.field_70458_d, this.craftMatrix, this.craftResult, 0, 144, 36));

      final int i;
      int j;
      for(i = 0; i < 2; ++i) {
         for(j = 0; j < 2; ++j) {
            this.func_75146_a(new Slot(this.craftMatrix, j + i * 2, 106 + j * 18, 26 + i * 18));
         }
      }

      for(i = 0; i < 4; ++i) {
         this.func_75146_a(new Slot(playerInv, playerInv.func_70302_i_() - 1 - i, 8, 8 + i * 18) {
            public int func_75219_a() {
               return 1;
            }

            public boolean func_75214_a(ItemStack par1ItemStack) {
               return par1ItemStack == null ? false : par1ItemStack.func_77973_b().isValidArmor(par1ItemStack, i, ContainerPlayerExpanded.this.thePlayer);
            }
         });
      }

      this.func_75146_a(new SlotBauble(this.baubles, BaubleType.AMULET, 0, 80, 8));
      this.func_75146_a(new SlotBauble(this.baubles, BaubleType.RING, 1, 80, 26));
      this.func_75146_a(new SlotBauble(this.baubles, BaubleType.RING, 2, 80, 44));
      this.func_75146_a(new SlotBauble(this.baubles, BaubleType.BELT, 3, 80, 62));

      for(i = 0; i < 3; ++i) {
         for(j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(playerInv, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(i = 0; i < 9; ++i) {
         this.func_75146_a(new Slot(playerInv, i, 8 + i * 18, 142));
      }

      this.func_75130_a(this.craftMatrix);
   }

   public void func_75130_a(IInventory par1IInventory) {
      this.craftResult.func_70299_a(0, CraftingManager.func_77594_a().func_82787_a(this.craftMatrix, this.thePlayer.field_70170_p));
   }

   public void func_75134_a(EntityPlayer player) {
      super.func_75134_a(player);

      for(int i = 0; i < 4; ++i) {
         ItemStack itemstack = this.craftMatrix.func_70304_b(i);
         if (itemstack != null) {
            player.func_71019_a(itemstack, false);
         }
      }

      this.craftResult.func_70299_a(0, (ItemStack)null);
      if (!player.field_70170_p.field_72995_K) {
         PlayerHandler.setPlayerBaubles(player, this.baubles);
      }

   }

   public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
      return true;
   }

   public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(par2);
      if (slot != null && slot.func_75216_d()) {
         ItemStack itemstack1 = slot.func_75211_c();
         itemstack = itemstack1.func_77946_l();
         if (par2 == 0) {
            if (!this.func_75135_a(itemstack1, 13, 49, true)) {
               return null;
            }

            slot.func_75220_a(itemstack1, itemstack);
         } else if (par2 >= 1 && par2 < 5) {
            if (!this.func_75135_a(itemstack1, 13, 49, false)) {
               return null;
            }
         } else if (par2 >= 5 && par2 < 9) {
            if (!this.func_75135_a(itemstack1, 13, 49, false)) {
               return null;
            }
         } else if (itemstack.func_77973_b() instanceof ItemArmor && !((Slot)this.field_75151_b.get(5 + ((ItemArmor)itemstack.func_77973_b()).field_77881_a)).func_75216_d()) {
            int j = 5 + ((ItemArmor)itemstack.func_77973_b()).field_77881_a;
            if (!this.func_75135_a(itemstack1, j, j + 1, false)) {
               return null;
            }
         } else {
            byte j;
            if (itemstack.func_77973_b() instanceof IBauble && ((IBauble)itemstack.func_77973_b()).getBaubleType(itemstack) == BaubleType.AMULET && ((IBauble)itemstack.func_77973_b()).canEquip(itemstack, this.thePlayer) && !((Slot)this.field_75151_b.get(9)).func_75216_d()) {
               j = 9;
               if (!this.func_75135_a(itemstack1, j, j + 1, false)) {
                  return null;
               }
            } else if (par2 > 11 && itemstack.func_77973_b() instanceof IBauble && ((IBauble)itemstack.func_77973_b()).getBaubleType(itemstack) == BaubleType.RING && ((IBauble)itemstack.func_77973_b()).canEquip(itemstack, this.thePlayer) && !((Slot)this.field_75151_b.get(10)).func_75216_d()) {
               j = 10;
               if (!this.func_75135_a(itemstack1, j, j + 1, false)) {
                  return null;
               }
            } else if (par2 > 11 && itemstack.func_77973_b() instanceof IBauble && ((IBauble)itemstack.func_77973_b()).getBaubleType(itemstack) == BaubleType.RING && ((IBauble)itemstack.func_77973_b()).canEquip(itemstack, this.thePlayer) && !((Slot)this.field_75151_b.get(11)).func_75216_d()) {
               j = 11;
               if (!this.func_75135_a(itemstack1, j, j + 1, false)) {
                  return null;
               }
            } else if (itemstack.func_77973_b() instanceof IBauble && ((IBauble)itemstack.func_77973_b()).getBaubleType(itemstack) == BaubleType.BELT && ((IBauble)itemstack.func_77973_b()).canEquip(itemstack, this.thePlayer) && !((Slot)this.field_75151_b.get(12)).func_75216_d()) {
               j = 12;
               if (!this.func_75135_a(itemstack1, j, j + 1, false)) {
                  return null;
               }
            } else if (par2 >= 13 && par2 < 40) {
               if (!this.func_75135_a(itemstack1, 40, 49, false)) {
                  return null;
               }
            } else if (par2 >= 40 && par2 < 49) {
               if (!this.func_75135_a(itemstack1, 13, 40, false)) {
                  return null;
               }
            } else if (!this.mergeItemStack(itemstack1, 13, 49, false, slot)) {
               return null;
            }
         }

         if (itemstack1.field_77994_a == 0) {
            slot.func_75215_d((ItemStack)null);
         } else {
            slot.func_75218_e();
         }

         if (itemstack1.field_77994_a == itemstack.field_77994_a) {
            return null;
         }

         slot.func_82870_a(par1EntityPlayer, itemstack1);
      }

      return itemstack;
   }

   private void unequipBauble(ItemStack stack) {
   }

   public void func_75131_a(ItemStack[] p_75131_1_) {
      this.baubles.blockEvents = true;
      super.func_75131_a(p_75131_1_);
   }

   protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4, Slot ss) {
      boolean flag1 = false;
      int k = par2;
      if (par4) {
         k = par3 - 1;
      }

      Slot slot;
      ItemStack itemstack1;
      if (par1ItemStack.func_77985_e()) {
         while(par1ItemStack.field_77994_a > 0 && (!par4 && k < par3 || par4 && k >= par2)) {
            slot = (Slot)this.field_75151_b.get(k);
            itemstack1 = slot.func_75211_c();
            if (itemstack1 != null && itemstack1.func_77973_b() == par1ItemStack.func_77973_b() && (!par1ItemStack.func_77981_g() || par1ItemStack.func_77960_j() == itemstack1.func_77960_j()) && ItemStack.func_77970_a(par1ItemStack, itemstack1)) {
               int l = itemstack1.field_77994_a + par1ItemStack.field_77994_a;
               if (l <= par1ItemStack.func_77976_d()) {
                  if (ss instanceof SlotBauble) {
                     this.unequipBauble(par1ItemStack);
                  }

                  par1ItemStack.field_77994_a = 0;
                  itemstack1.field_77994_a = l;
                  slot.func_75218_e();
                  flag1 = true;
               } else if (itemstack1.field_77994_a < par1ItemStack.func_77976_d()) {
                  if (ss instanceof SlotBauble) {
                     this.unequipBauble(par1ItemStack);
                  }

                  par1ItemStack.field_77994_a -= par1ItemStack.func_77976_d() - itemstack1.field_77994_a;
                  itemstack1.field_77994_a = par1ItemStack.func_77976_d();
                  slot.func_75218_e();
                  flag1 = true;
               }
            }

            if (par4) {
               --k;
            } else {
               ++k;
            }
         }
      }

      if (par1ItemStack.field_77994_a > 0) {
         if (par4) {
            k = par3 - 1;
         } else {
            k = par2;
         }

         while(!par4 && k < par3 || par4 && k >= par2) {
            slot = (Slot)this.field_75151_b.get(k);
            itemstack1 = slot.func_75211_c();
            if (itemstack1 == null) {
               if (ss instanceof SlotBauble) {
                  this.unequipBauble(par1ItemStack);
               }

               slot.func_75215_d(par1ItemStack.func_77946_l());
               slot.func_75218_e();
               par1ItemStack.field_77994_a = 0;
               flag1 = true;
               break;
            }

            if (par4) {
               --k;
            } else {
               ++k;
            }
         }
      }

      return flag1;
   }

   public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
      return par2Slot.field_75224_c != this.craftResult && super.func_94530_a(par1ItemStack, par2Slot);
   }
}

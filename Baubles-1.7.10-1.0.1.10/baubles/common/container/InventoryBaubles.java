package baubles.common.container;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.Baubles;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;

public class InventoryBaubles implements IInventory {
   public ItemStack[] stackList = new ItemStack[4];
   private Container eventHandler;
   public WeakReference<EntityPlayer> player;
   public boolean blockEvents = false;

   public InventoryBaubles(EntityPlayer player) {
      this.player = new WeakReference(player);
   }

   public Container getEventHandler() {
      return this.eventHandler;
   }

   public void setEventHandler(Container eventHandler) {
      this.eventHandler = eventHandler;
   }

   public int func_70302_i_() {
      return this.stackList.length;
   }

   public ItemStack func_70301_a(int par1) {
      return par1 >= this.func_70302_i_() ? null : this.stackList[par1];
   }

   public String func_145825_b() {
      return "";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public ItemStack func_70304_b(int par1) {
      if (this.stackList[par1] != null) {
         ItemStack itemstack = this.stackList[par1];
         this.stackList[par1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public ItemStack func_70298_a(int par1, int par2) {
      if (this.stackList[par1] != null) {
         ItemStack itemstack;
         if (this.stackList[par1].field_77994_a <= par2) {
            itemstack = this.stackList[par1];
            if (itemstack != null && itemstack.func_77973_b() instanceof IBauble) {
               ((IBauble)itemstack.func_77973_b()).onUnequipped(itemstack, (EntityLivingBase)this.player.get());
            }

            this.stackList[par1] = null;
            if (this.eventHandler != null) {
               this.eventHandler.func_75130_a(this);
            }

            this.syncSlotToClients(par1);
            return itemstack;
         } else {
            itemstack = this.stackList[par1].func_77979_a(par2);
            if (itemstack != null && itemstack.func_77973_b() instanceof IBauble) {
               ((IBauble)itemstack.func_77973_b()).onUnequipped(itemstack, (EntityLivingBase)this.player.get());
            }

            if (this.stackList[par1].field_77994_a == 0) {
               this.stackList[par1] = null;
            }

            if (this.eventHandler != null) {
               this.eventHandler.func_75130_a(this);
            }

            this.syncSlotToClients(par1);
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public void func_70299_a(int par1, ItemStack stack) {
      if (!this.blockEvents && this.stackList[par1] != null) {
         ((IBauble)this.stackList[par1].func_77973_b()).onUnequipped(this.stackList[par1], (EntityLivingBase)this.player.get());
      }

      this.stackList[par1] = stack;
      if (!this.blockEvents && stack != null && stack.func_77973_b() instanceof IBauble) {
         ((IBauble)stack.func_77973_b()).onEquipped(stack, (EntityLivingBase)this.player.get());
      }

      if (this.eventHandler != null) {
         this.eventHandler.func_75130_a(this);
      }

      this.syncSlotToClients(par1);
   }

   public int func_70297_j_() {
      return 1;
   }

   public void func_70296_d() {
      try {
         ((EntityPlayer)this.player.get()).field_71071_by.func_70296_d();
      } catch (Exception var2) {
      }

   }

   public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
      return true;
   }

   public void func_70295_k_() {
   }

   public void func_70305_f() {
   }

   public boolean func_94041_b(int i, ItemStack stack) {
      if (stack != null && stack.func_77973_b() instanceof IBauble && ((IBauble)stack.func_77973_b()).canEquip(stack, (EntityLivingBase)this.player.get())) {
         if (i == 0 && ((IBauble)stack.func_77973_b()).getBaubleType(stack) == BaubleType.AMULET) {
            return true;
         } else if ((i == 1 || i == 2) && ((IBauble)stack.func_77973_b()).getBaubleType(stack) == BaubleType.RING) {
            return true;
         } else {
            return i == 3 && ((IBauble)stack.func_77973_b()).getBaubleType(stack) == BaubleType.BELT;
         }
      } else {
         return false;
      }
   }

   public void saveNBT(EntityPlayer player) {
      NBTTagCompound tags = player.getEntityData();
      this.saveNBT(tags);
   }

   public void saveNBT(NBTTagCompound tags) {
      NBTTagList tagList = new NBTTagList();

      for(int i = 0; i < this.stackList.length; ++i) {
         if (this.stackList[i] != null) {
            NBTTagCompound invSlot = new NBTTagCompound();
            invSlot.func_74774_a("Slot", (byte)i);
            this.stackList[i].func_77955_b(invSlot);
            tagList.func_74742_a(invSlot);
         }
      }

      tags.func_74782_a("Baubles.Inventory", tagList);
   }

   public void readNBT(EntityPlayer player) {
      NBTTagCompound tags = player.getEntityData();
      this.readNBT(tags);
   }

   public void readNBT(NBTTagCompound tags) {
      NBTTagList tagList = tags.func_150295_c("Baubles.Inventory", 10);

      for(int i = 0; i < tagList.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = tagList.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot") & 255;
         ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
         if (itemstack != null) {
            this.stackList[j] = itemstack;
         }
      }

   }

   public void dropItems(ArrayList<EntityItem> drops) {
      for(int i = 0; i < 4; ++i) {
         if (this.stackList[i] != null) {
            EntityItem ei = new EntityItem(((EntityPlayer)this.player.get()).field_70170_p, ((EntityPlayer)this.player.get()).field_70165_t, ((EntityPlayer)this.player.get()).field_70163_u + (double)((EntityPlayer)this.player.get()).eyeHeight, ((EntityPlayer)this.player.get()).field_70161_v, this.stackList[i].func_77946_l());
            ei.field_145804_b = 40;
            float f1 = ((EntityPlayer)this.player.get()).field_70170_p.field_73012_v.nextFloat() * 0.5F;
            float f2 = ((EntityPlayer)this.player.get()).field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
            ei.field_70159_w = (double)(-MathHelper.func_76126_a(f2) * f1);
            ei.field_70179_y = (double)(MathHelper.func_76134_b(f2) * f1);
            ei.field_70181_x = 0.20000000298023224D;
            drops.add(ei);
            this.stackList[i] = null;
            this.syncSlotToClients(i);
         }
      }

   }

   public void dropItemsAt(ArrayList<EntityItem> drops, Entity e) {
      for(int i = 0; i < 4; ++i) {
         if (this.stackList[i] != null) {
            EntityItem ei = new EntityItem(e.field_70170_p, e.field_70165_t, e.field_70163_u + (double)e.func_70047_e(), e.field_70161_v, this.stackList[i].func_77946_l());
            ei.field_145804_b = 40;
            float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5F;
            float f2 = e.field_70170_p.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
            ei.field_70159_w = (double)(-MathHelper.func_76126_a(f2) * f1);
            ei.field_70179_y = (double)(MathHelper.func_76134_b(f2) * f1);
            ei.field_70181_x = 0.20000000298023224D;
            drops.add(ei);
            this.stackList[i] = null;
            this.syncSlotToClients(i);
         }
      }

   }

   public void syncSlotToClients(int slot) {
      try {
         if (Baubles.proxy.getClientWorld() == null) {
            PacketHandler.INSTANCE.sendToAll(new PacketSyncBauble((EntityPlayer)this.player.get(), slot));
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }
}

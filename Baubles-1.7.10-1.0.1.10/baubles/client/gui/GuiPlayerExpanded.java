package baubles.client.gui;

import baubles.common.Baubles;
import baubles.common.container.ContainerPlayerExpanded;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPlayerExpanded extends InventoryEffectRenderer {
   public static final ResourceLocation background = new ResourceLocation("baubles", "textures/gui/expanded_inventory.png");
   private float xSizeFloat;
   private float ySizeFloat;

   public GuiPlayerExpanded(EntityPlayer player) {
      super(new ContainerPlayerExpanded(player.field_71071_by, !player.field_70170_p.field_72995_K, player));
      this.field_146291_p = true;
   }

   public void func_73876_c() {
      try {
         ((ContainerPlayerExpanded)this.field_147002_h).baubles.blockEvents = false;
      } catch (Exception var2) {
      }

   }

   public void func_73866_w_() {
      this.field_146292_n.clear();
      super.func_73866_w_();
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.crafting", new Object[0]), 106, 16, 4210752);
   }

   public void func_73863_a(int par1, int par2, float par3) {
      super.func_73863_a(par1, par2, par3);
      this.xSizeFloat = (float)par1;
      this.ySizeFloat = (float)par2;
   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_146297_k.func_110434_K().func_110577_a(background);
      int k = this.field_147003_i;
      int l = this.field_147009_r;
      this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);

      for(int i1 = 0; i1 < this.field_147002_h.field_75151_b.size(); ++i1) {
         Slot slot = (Slot)this.field_147002_h.field_75151_b.get(i1);
         if (slot.func_75216_d() && slot.func_75219_a() == 1) {
            this.func_73729_b(k + slot.field_75223_e, l + slot.field_75221_f, 200, 0, 16, 16);
         }
      }

      drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat, this.field_146297_k.field_71439_g);
   }

   public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase playerdrawn) {
      GL11.glEnable(2903);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y, 50.0F);
      GL11.glScalef((float)(-scale), (float)scale, (float)scale);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      float f2 = playerdrawn.field_70761_aq;
      float f3 = playerdrawn.field_70177_z;
      float f4 = playerdrawn.field_70125_A;
      float f5 = playerdrawn.field_70758_at;
      float f6 = playerdrawn.field_70759_as;
      GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.func_74519_b();
      GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      playerdrawn.field_70761_aq = (float)Math.atan((double)(yaw / 40.0F)) * 20.0F;
      playerdrawn.field_70177_z = (float)Math.atan((double)(yaw / 40.0F)) * 40.0F;
      playerdrawn.field_70125_A = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
      playerdrawn.field_70759_as = playerdrawn.field_70177_z;
      playerdrawn.field_70758_at = playerdrawn.field_70177_z;
      GL11.glTranslatef(0.0F, playerdrawn.field_70129_M, 0.0F);
      RenderManager.field_78727_a.field_78735_i = 180.0F;
      RenderManager.field_78727_a.func_147940_a(playerdrawn, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
      playerdrawn.field_70761_aq = f2;
      playerdrawn.field_70177_z = f3;
      playerdrawn.field_70125_A = f4;
      playerdrawn.field_70758_at = f5;
      playerdrawn.field_70759_as = f6;
      GL11.glPopMatrix();
      RenderHelper.func_74518_a();
      GL11.glDisable(32826);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
      GL11.glDisable(3553);
      OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
   }

   protected void func_146284_a(GuiButton button) {
      if (button.field_146127_k == 0) {
         this.field_146297_k.func_147108_a(new GuiAchievements(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

      if (button.field_146127_k == 1) {
         this.field_146297_k.func_147108_a(new GuiStats(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

   }

   protected void func_73869_a(char par1, int par2) {
      if (par2 == Baubles.proxy.keyHandler.key.func_151463_i()) {
         this.field_146297_k.field_71439_g.func_71053_j();
      } else {
         super.func_73869_a(par1, par2);
      }

   }
}

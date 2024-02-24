package baubles.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class GuiBaublesButton extends GuiButton {
   public GuiBaublesButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_) {
      super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
   }

   public void func_146112_a(Minecraft mc, int xx, int yy) {
      if (this.field_146125_m) {
         FontRenderer fontrenderer = mc.field_71466_p;
         mc.func_110434_K().func_110577_a(GuiPlayerExpanded.background);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = xx >= this.field_146128_h && yy >= this.field_146129_i && xx < this.field_146128_h + this.field_146120_f && yy < this.field_146129_i + this.field_146121_g;
         int k = this.func_146114_a(this.field_146123_n);
         GL11.glEnable(3042);
         OpenGlHelper.func_148821_a(770, 771, 1, 0);
         GL11.glBlendFunc(770, 771);
         if (k == 1) {
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 200, 48, 10, 10);
         } else {
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 210, 48, 10, 10);
            this.func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + 5, this.field_146129_i + this.field_146121_g, 16777215);
         }

         this.func_146119_b(mc, xx, yy);
      }

   }
}

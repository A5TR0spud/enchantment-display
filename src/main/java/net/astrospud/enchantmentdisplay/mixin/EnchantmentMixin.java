package net.astrospud.enchantmentdisplay.mixin;

import net.astrospud.enchantmentdisplay.Utils;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(at = @At("HEAD"), method = "getName", cancellable = true)
    public void getName(int level, CallbackInfoReturnable<Text> cir) {
        Enchantment enchant = (Enchantment)(Object) this;
        String equipment = enchant.type.name().toLowerCase();

        if (Utils.isIn(ConventionalEnchantmentTags.ENTITY_DEFENSE_ENHANCEMENT, enchant)) {
            equipment = "defense";
        }
        if (Utils.isIn(ConventionalEnchantmentTags.ENTITY_MOVEMENT_ENHANCEMENT, enchant)) {
            equipment = "movement";
        }
        if (Utils.isIn(ConventionalEnchantmentTags.INCREASES_BLOCK_DROPS, enchant)) {
            equipment = "fortune";
        }
        if (Utils.isIn(ConventionalEnchantmentTags.INCREASES_ENTITY_DROPS, enchant)) {
            equipment = "looting";
        }
        if (Utils.isIn(ConventionalEnchantmentTags.WEAPON_DAMAGE_ENHANCEMENT, enchant)) {
            equipment = "damage";
        }

        MutableText mutableText = Text.translatable("enchantment.enchantmentdisplay." + equipment).formatted(Formatting.WHITE);

        MutableText enchantText = Text.translatable(enchant.getTranslationKey());
        if (enchant.isCursed()) {
            enchantText.formatted(Formatting.RED);
        } else {
            enchantText.formatted(Formatting.GRAY);
        }

        mutableText.append(" ").append(enchantText);

        if (level > 1 || enchant.getMaxLevel() > 1) {
            int maxLevel = enchant.getMaxLevel();
            int minLevel = enchant.getMinLevel();

            Color redHue = new Color(16733525);
            Color greenHue = new Color(5635925);
            float[] redHSV = Color.RGBtoHSB(redHue.getRed(), redHue.getGreen(), redHue.getBlue(), null);
            float[] greenHSV = Color.RGBtoHSB(greenHue.getRed(), greenHue.getGreen(), greenHue.getBlue(), null);
            float finalColorHue = (float) Utils.MapValue(minLevel, maxLevel, redHSV[0], greenHSV[0], level);
            float finalColorSat = (float) Utils.MapValue(minLevel, maxLevel, redHSV[1], greenHSV[1], level);
            float finalColorVal = (float) Utils.MapValue(minLevel, maxLevel, redHSV[2], greenHSV[2], level);
            int finalColor = MathHelper.hsvToRgb(finalColorHue, finalColorSat, finalColorVal);

            MutableText levelText = Text.translatable("enchantment.level." + level).setStyle(Style.EMPTY.withColor(finalColor));
            MutableText maxText = Text.translatable("enchantment.level." + enchant.getMaxLevel()).setStyle(Style.EMPTY.withColor(finalColor));
            MutableText slash = Text.literal("/").setStyle(Style.EMPTY.withColor(finalColor));
            mutableText
                    .append(" ")
                    .append(levelText)
                    .append(slash)
                    .append(maxText);
        }

        cir.setReturnValue(mutableText);
    }
}

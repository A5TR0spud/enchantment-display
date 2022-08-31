package net.astrospud.enchantmentdisplay;

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEnchantmentTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class Utils {
    public static double MapValue(double ogMin, double ogMax, double gotoMin, double gotoMax, double valueToMap)
    {
        return MathHelper.clamp(gotoMin + (gotoMax - gotoMin) * ((valueToMap-ogMin)/(ogMax-ogMin)), gotoMin, gotoMax);
    }

    public static boolean isIn(TagKey<Enchantment> tag, Enchantment enchantment){
        if (enchantment != null) {
            if (Registry.ENCHANTMENT.getKey(enchantment).isPresent()) {
                RegistryKey<Enchantment> i = Registry.ENCHANTMENT.getKey(enchantment).get();
                if (Registry.ENCHANTMENT.getEntry(i).isPresent()) {
                    RegistryEntry<Enchantment> entry = Registry.ENCHANTMENT.getEntry(i).get();
                    return entry.isIn(tag);
                }
            }
        }
        return false;
    }
}
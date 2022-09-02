package net.astrospud.enchanted_icons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;

import java.util.Optional;

public class Utils {
    public static double MapValue(double ogMin, double ogMax, double gotoMin, double gotoMax, double valueToMap)
    {
        return MathHelper.clamp(gotoMin + (gotoMax - gotoMin) * ((valueToMap-ogMin)/(ogMax-ogMin)), gotoMin, gotoMax);
    }

    public static boolean isIn(TagKey<Enchantment> tag, Enchantment enchantment){
        RegistryEntry<Enchantment> entry = getEnchantmentRegistryKey(enchantment);
        EnchantmentDisplay.LOGGER.info("tag: " + tag);
        if (entry != null) {
            return entry.isIn(tag);
        }
        return false;
    }

    public static RegistryEntry<Enchantment> getEnchantmentRegistryKey(Enchantment enchantment){
        RegistryKey<Enchantment> key;
        Optional<RegistryKey<Enchantment>> optional = Registry.ENCHANTMENT.getKey(enchantment);
        if(optional.isPresent()) {
            key = optional.get();
        }
        else {
            return null;
        }
        Optional<RegistryEntry<Enchantment>> optional2 = Registry.ENCHANTMENT.getEntry(key);
        EnchantmentDisplay.LOGGER.info("enchantment entry: " + optional2.get());
        return optional2.orElse(null);
    }
}
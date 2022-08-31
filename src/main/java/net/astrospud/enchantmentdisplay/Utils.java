package net.astrospud.enchantmentdisplay;

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
        EnchantmentDisplay.LOGGER.info("isIn has been called!");
        RegistryEntry<Enchantment> entry = getEnchantmentRegistryKey(enchantment);
        return entry.isIn(tag);
    }

    public static RegistryEntry<Enchantment> getEnchantmentRegistryKey(Enchantment enchantment){
        EnchantmentDisplay.LOGGER.info("getEnchantmentRegistryKey has been called!");
        RegistryKey<Enchantment> key;
        EnchantmentDisplay.LOGGER.info("key value init");
        Optional<RegistryKey<Enchantment>> optional = Registry.ENCHANTMENT.getKey(enchantment);
        EnchantmentDisplay.LOGGER.info("got key");
        if(optional.isPresent()) {
            EnchantmentDisplay.LOGGER.info("is present");
            key = optional.get();
        }
        else {
            EnchantmentDisplay.LOGGER.info("else");
            return null;
        }
        EnchantmentDisplay.LOGGER.info("if statement over");
        Optional<RegistryEntry<Enchantment>> optional2 = Registry.ENCHANTMENT.getEntry(key);
        EnchantmentDisplay.LOGGER.info("got entry");
        return optional2.orElse(null);
    }
}
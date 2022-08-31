package net.astrospud.enchantmentdisplay;

import net.minecraft.tag.TagKey;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class Utils {
    public static double MapValue(double ogMin, double ogMax, double gotoMin, double gotoMax, double valueToMap)
    {
        return MathHelper.clamp(gotoMin + (gotoMax - gotoMin) * ((valueToMap-ogMin)/(ogMax-ogMin)), gotoMin, gotoMax);
    }

    public static <T> boolean isIn(TagKey<T> tagKey, T entry) {
        return isIn(null, tagKey, entry);
    }

    @SuppressWarnings("unchecked")
    public static <T> boolean isIn(@Nullable DynamicRegistryManager registryManager, TagKey<T> tagKey, T entry) {
        Optional<? extends Registry<?>> maybeRegistry;
        Objects.requireNonNull(tagKey);
        Objects.requireNonNull(entry);

        if (registryManager != null) {
            maybeRegistry = registryManager.getOptional(tagKey.registry());
        } else {
            maybeRegistry = Registry.REGISTRIES.getOrEmpty(tagKey.registry().getValue());
        }

        if (maybeRegistry.isPresent()) {
            if (tagKey.isOf(maybeRegistry.get().getKey())) {
                Registry<T> registry = (Registry<T>) maybeRegistry.get();

                Optional<RegistryKey<T>> maybeKey = registry.getKey(entry);

                // Check synced tag
                if (maybeKey.isPresent()) {
                    return registry.entryOf(maybeKey.get()).isIn(tagKey);
                }
            }
        }

        return false;
    }
}

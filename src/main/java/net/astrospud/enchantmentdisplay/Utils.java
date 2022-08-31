package net.astrospud.enchantmentdisplay;

import net.minecraft.util.math.MathHelper;

public class Utils {
    public static double MapValue(double ogMin, double ogMax, double gotoMin, double gotoMax, double valueToMap)
    {
        return MathHelper.clamp(gotoMin + (gotoMax - gotoMin) * ((valueToMap-ogMin)/(ogMax-ogMin)), gotoMin, gotoMax);
    }
}

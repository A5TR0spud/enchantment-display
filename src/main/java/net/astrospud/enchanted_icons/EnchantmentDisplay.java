package net.astrospud.enchanted_icons;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnchantmentDisplay implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("enchanted_display"); //enchanted_display
	public static final String MOD_ID = "enchanted_icons";

	@Override
	public void onInitializeClient() {
		LOGGER.info("Hello Fabric world!");
	}
}

package cn.davidma.mendinggems.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public enum GemTier {
	
	WEAK(TextFormatting.RED),
	NORMAL(TextFormatting.LIGHT_PURPLE),
	STRONG(TextFormatting.GREEN),
	ULTIMATE(TextFormatting.AQUA);
	
	private TextFormatting color;
	
	private GemTier(TextFormatting color) {
		this.color = color;
	}
	
	public TextFormatting getColor() {
		return this.color;
	}
	
	public int getCooldown() {
		return ConfigMendingGems.COOLDOWN[this.ordinal()];
	}
	
	public static int getCooldownFromStack(ItemStack stack) {
		return getTierFromMeta(stack.getMetadata()).getCooldown();
	}
	
	public static TextFormatting getColorFromStack(ItemStack stack) {
		return getTierFromMeta(stack.getMetadata()).getColor();
	}
	
	private static GemTier getTierFromMeta(int meta) {
		if (meta < 0 || meta >= values().length) meta = 0;
		return values()[meta];
	}
}

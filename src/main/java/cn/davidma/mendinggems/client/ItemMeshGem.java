package cn.davidma.mendinggems.client;

import cn.davidma.mendinggems.MendingGems;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ItemMeshGem implements ItemMeshDefinition {
	
	public static final ModelResourceLocation LESSER = new ModelResourceLocation(MendingGems.MOD_ID + ":lesser");
	public static final ModelResourceLocation GREATER = new ModelResourceLocation(MendingGems.MOD_ID + ":greater");
	
	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		return stack.getMetadata() >= 2 ? GREATER : LESSER;
	}
}

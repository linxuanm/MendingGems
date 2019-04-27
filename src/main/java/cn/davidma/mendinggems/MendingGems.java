package cn.davidma.mendinggems;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.mendinggems.client.ItemMeshGem;
import cn.davidma.mendinggems.item.ItemGem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = MendingGems.MOD_ID, name = MendingGems.MOD_NAME, version = MendingGems.VERSION)
public class MendingGems {
	
	public static final String MOD_ID = "mendinggems";
	public static final String MOD_NAME = "Mending Gem";
	public static final String VERSION = "1.0.0";
	
	@Instance
	public static MendingGems instance;
	
	public static Item gem;
	
	@EventBusSubscriber
	public static class RegistryHandler {
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(gem = new ItemGem());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModelBakery.registerItemVariants(gem, ItemMeshGem.LESSER);
			ModelBakery.registerItemVariants(gem, ItemMeshGem.GREATER);
			ModelLoader.setCustomMeshDefinition(gem, new ItemMeshGem());
		}
	}
}

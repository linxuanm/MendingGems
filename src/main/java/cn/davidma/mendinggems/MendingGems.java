package cn.davidma.mendinggems;

import java.util.ArrayList;
import java.util.List;

import cn.davidma.mendinggems.client.ItemMeshGem;
import cn.davidma.mendinggems.common.item.ItemGem;
import cn.davidma.mendinggems.common.misc.IProxy;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = MendingGems.MOD_ID, name = MendingGems.MOD_NAME, version = MendingGems.VERSION)
public class MendingGems {
	
	public static final String MOD_ID = "mendinggems";
	public static final String MOD_NAME = "Mending Gem";
	public static final String VERSION = "1.0.0";
	
	@Instance
	public static MendingGems instance;
	
	@SidedProxy(
			clientSide = "cn.davidma.mendinggems.client.ClientProxy",
			serverSide = "cn.davidma.mendinggems.common.ServerProxy"
	)
	public static IProxy proxy;
	
	public static Item gem;
	
	@EventBusSubscriber
	public static class RegistryHandler {
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(gem = new ItemGem());
		}
		
		@SubscribeEvent
                @SideOnly(Side.CLIENT)
		public static void registerModels(ModelRegistryEvent event) {
			ModelBakery.registerItemVariants(gem, ItemMeshGem.LESSER);
			ModelBakery.registerItemVariants(gem, ItemMeshGem.GREATER);
			ModelLoader.setCustomMeshDefinition(gem, new ItemMeshGem());
		}
	}
}

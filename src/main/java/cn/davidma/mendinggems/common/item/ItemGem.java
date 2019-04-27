package cn.davidma.mendinggems.common.item;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cn.davidma.mendinggems.MendingGems;
import cn.davidma.mendinggems.common.misc.GemTier;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

@Optional.Interface(iface = "baubles.api.IBauble", modid = "baubles")
public class ItemGem extends Item implements IBauble {
	
	public ItemGem() {
		this.setRegistryName(new ResourceLocation(MendingGems.MOD_ID, "mending_gem"));
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(CreativeTabs.MISC);
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (!world.isRemote && entity instanceof EntityPlayer) {
			if (world.getTotalWorldTime() % GemTier.getCooldownFromStack(stack) == 0) {
				repair((EntityPlayer) entity);
			}
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = stack.getMetadata();
		if (meta < 0 || meta >= GemTier.values().length) meta = 0;
		String prefix = MendingGems.proxy.localize("tier.mendinggems.tier_" + meta);
		return GemTier.getColorFromStack(stack) + (prefix + super.getItemStackDisplayName(stack));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			for (int i = 0; i < GemTier.values().length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		int cooldown = GemTier.getCooldownFromStack(stack);
		tooltip.add(TextFormatting.GRAY + I18n.format("tooltip.mendinggems.mending_gem", cooldown / 20D));
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() % 2 == 1;
	}
	
	private static void repair(EntityPlayer player) {
		IItemHandler inv = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		ItemStack mainHand = player.inventory.getCurrentItem();
		
		for (int i = 0; i < inv.getSlots(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem().isRepairable()) {
				if (!player.isSwingInProgress || stack != mainHand) {
					if (stack.isItemDamaged()) {
						stack.setItemDamage(stack.getItemDamage() - 1);
						return;
					}
				}
			}
		}
	}

	@Override
	@Optional.Method(modid = "baubles")
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.TRINKET;
	}
	
	@Override
	@Optional.Method(modid = "baubles")
	public void onWornTick(ItemStack stack, EntityLivingBase entity) {
		World world = entity.world;
		if (!world.isRemote && entity instanceof EntityPlayer) {
			if (world.getTotalWorldTime() % GemTier.getCooldownFromStack(stack) == 0) {
				repair((EntityPlayer) entity);
			}
		}
	}
}

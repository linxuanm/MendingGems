package cn.davidma.mendinggems.common.misc;

import cn.davidma.mendinggems.MendingGems;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;

@Config(modid = MendingGems.MOD_ID)
public class ConfigMendingGems {
	
	@Comment({
			"The cooldown, in ticks, of each tier of Mending Gems.",
			"Set the cooldown in the order of Weak, Normal, Strong, Ultimate."
	})
	public static int[] COOLDOWN = {200, 150, 100, 2};
}

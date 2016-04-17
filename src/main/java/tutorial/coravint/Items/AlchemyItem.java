package tutorial.coravint.Items;

import net.minecraft.item.Item;

public class AlchemyItem extends Item {
	int level;
	
	void setLevel(int nLvl)
	{
		level = nLvl;
	}
	
	int getLevel()
	{
		return level;
	}
}

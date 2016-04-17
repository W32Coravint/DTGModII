package tutorial.coravint.Items;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems{
	static DsrtBow deseretBow;
	
	public static void init(){
		initializeItem();
		registerItem();
	}
	public static void initializeItem(){
		deseretBow = new DsrtBow();
	}
	
	public static void registerItem(){
		System.out.println("Registering Alchemies");
		GameRegistry.registerItem(deseretBow, "dsrtBow");
	}
}
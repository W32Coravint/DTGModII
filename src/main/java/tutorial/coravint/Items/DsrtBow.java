package tutorial.coravint.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class DsrtBow extends ItemBow {
	private IIcon[] iconArray;
	
	public DsrtBow(){
		super();
		setMaxStackSize(1);
		setUnlocalizedName("dsrtbow");
		setTextureName("dtgmodii:dsrtbow");
	}
	
    public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {
        int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

        ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F);

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityDsrtArrow entitydeseretarrow = new EntityDsrtArrow(p_77615_2_, p_77615_3_, f * 2.0F);

            if (f == 1.0F)
            {
                entitydeseretarrow.setIsCritical(true);
            }

            p_77615_1_.damageItem(1, p_77615_3_);
            p_77615_2_.playSoundAtEntity(p_77615_3_, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (!p_77615_2_.isRemote)
            {
                p_77615_2_.spawnEntityInWorld(entitydeseretarrow);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
            if (usingItem == null) { return itemIcon; }
            int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;
            if (ticksInUse > 12) {
                return iconArray[2];
            } else if (ticksInUse > 8) {
                return iconArray[1];
            } else if (ticksInUse > 0) {
                return iconArray[0];
            } else {
                return itemIcon;
            }
        }
    
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 128000;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon("dtgmodii:DsrtBow_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = p_94581_1_.registerIcon("dtgmodii:DsrtBow_" + i);
        }
    }

}
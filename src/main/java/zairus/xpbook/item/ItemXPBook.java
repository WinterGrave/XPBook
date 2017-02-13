package zairus.xpbook.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zairus.xpbook.XPBook;

public class ItemXPBook extends Item
{
	public static final int TOTAL_CAPACITY = 1395;
	
	public ItemXPBook()
	{
		this.setMaxStackSize(1);
		this.setCreativeTab(XPBook.mainTab);
	}
	
	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return TOTAL_CAPACITY;
	}
	
	@Override
	public int getDamage(ItemStack stack)
	{
		return super.getDamage(stack);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		boolean hasEffect = false;
		
		if (stack.getItemDamage() < TOTAL_CAPACITY)
			hasEffect = true;
		
		return hasEffect;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		ActionResult<ItemStack> result = new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
		
		if (world.isRemote)
			return result;
		
		ItemStack book = player.getHeldItem(hand);
		int damage = book.getItemDamage();
		
		int curLevel = player.experienceLevel;
		
		int xpTotal = 0;
		for (int i = 0; i < curLevel; ++i)
			xpTotal += this.calculateLevelValue(i);
		
		xpTotal += player.xpBarCap() * player.experience;
		
		if (player.isSneaking())
		{
			if (damage == 0)
				return result;
			
			int prevStored = TOTAL_CAPACITY - damage;
			int capacity = damage;
			int xpAdded = xpTotal > capacity ? capacity : xpTotal;
			
			player.removeExperienceLevel(curLevel);
			player.experience = 0;
			
			player.addExperience(xpTotal - xpAdded);
			
			book.setItemDamage(TOTAL_CAPACITY - xpAdded - prevStored);
		}
		else
		{
			int xpAdded = TOTAL_CAPACITY - damage;
			
			player.removeExperienceLevel(curLevel);
			player.experience = 0;
			
			player.addExperience(xpAdded + xpTotal);
			book.setItemDamage(TOTAL_CAPACITY);
		}
		
		return result;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		ItemStack stack = new ItemStack(item);
		stack.setItemDamage(TOTAL_CAPACITY);
		subItems.add(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Sneak-Right-Click to store XP");
		tooltip.add("Right-Click to retrieve XP");
		tooltip.add("Capacity: " + TOTAL_CAPACITY);
		tooltip.add("Contains: " + (TOTAL_CAPACITY - stack.getItemDamage()));
	}
	
	private int calculateLevelValue(int level)
	{
		return level >= 30 ? 112 + (level - 30) * 9 : (level >= 15 ? 37 + (level -15) * 5 : 7 + level * 2);
	}
}

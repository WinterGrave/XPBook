package zairus.xpbook.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zairus.xpbook.XPBConstants;

public class XPBItem extends Item
{
	public static final String ITEM_XPBOOK_ID = "xp_book";
	
	public XPBItem()
	{
		super();
	}
	
	public XPBItem(String id)
	{
		this();
		this.setRegistryName(XPBConstants.MODID, id);
		this.setUnlocalizedName(id);
	}
	
	public XPBItem(String id, CreativeTabs creativeTab)
	{
		this(id);
		this.setCreativeTab(creativeTab);
	}
	
	@Override
	public XPBItem setCreativeTab(CreativeTabs creativeTab)
	{
		super.setCreativeTab(creativeTab);
		
		return this;
	}
}

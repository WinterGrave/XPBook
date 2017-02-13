package zairus.xpbook.item;

import net.minecraft.item.Item;
import zairus.xpbook.XPBConstants;
import zairus.xpbook.XPBook;

public class XPBItems
{
	public static Item XP_BOOK;
	
	static
	{
		XP_BOOK = new ItemXPBook().setRegistryName(XPBConstants.MODID, "xp_book").setUnlocalizedName("xp_book");
	}
	
	public static final void register()
	{
		XPBook.proxy.registerItem(XP_BOOK, "xp_book", 0, true);
	}
}

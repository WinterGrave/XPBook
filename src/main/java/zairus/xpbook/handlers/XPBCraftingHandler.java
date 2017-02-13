package zairus.xpbook.handlers;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import zairus.xpbook.item.ItemXPBook;
import zairus.xpbook.item.XPBItems;

public class XPBCraftingHandler
{
	public static void addRecipes()
	{
		ItemStack book = new ItemStack(XPBItems.XP_BOOK);
		book.setItemDamage(ItemXPBook.TOTAL_CAPACITY);
		
		GameRegistry.addShapedRecipe(
				book
				, new Object[] {
						" p "
						,"pbp"
						," p "
						,'p'
						,Items.ENDER_PEARL
						,'b'
						,Items.BOOK
				});
	}
}

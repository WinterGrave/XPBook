package zairus.xpbook.item;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import zairus.xpbook.XPBConstants;
import zairus.xpbook.XPBook;

@ObjectHolder(XPBConstants.MODID)
public class XPBItems
{
	@ObjectHolder(XPBItem.ITEM_XPBOOK_ID)
	public static Item XP_BOOK;
	
	static
	{
		XP_BOOK = new ItemXPBook(XPBItem.ITEM_XPBOOK_ID, XPBook.mainTab);
	}
	
	public static void initialize()
	{
		;
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		final Item[] items = {
				XP_BOOK
		};
		
		for (final Item item : items)
		{
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(XPBConstants.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}
	}
	
	@Mod.EventBusSubscriber(modid = XPBConstants.MODID)
	public static class ItemRegistry
	{
		public static final Set<Item> ITEMS = new HashSet<Item>();
		
		@SubscribeEvent
		public static void newRegistry(final RegistryEvent.NewRegistry event)
		{
			;
		}
		
		@SubscribeEvent
		public static void register(final RegistryEvent.Register<Item> event)
		{
			final Item[] items = {
					XP_BOOK
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : items)
			{
				registry.register(item);
				ITEMS.add(item);
			}
			
			initialize();
		}
	}
}

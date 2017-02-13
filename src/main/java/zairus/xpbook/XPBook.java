package zairus.xpbook;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zairus.xpbook.handlers.XPBCraftingHandler;
import zairus.xpbook.item.XPBItems;
import zairus.xpbook.proxy.CommonProxy;

@Mod(modid = XPBConstants.MODID, name = XPBConstants.MODNAME, version = XPBConstants.VERSION)
public class XPBook
{
	@Mod.Instance(XPBConstants.MODID)
	public static XPBook instance;
	
	@SidedProxy(clientSide = XPBConstants.CLIENT_PROXY, serverSide = XPBConstants.COMMON_PROXY)
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	public static CreativeTabs mainTab = new CreativeTabs("xpbookMainTab") {
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem()
		{
			return new ItemStack(XPBItems.XP_BOOK);
		}
	};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		XPBConfig.init(event.getSuggestedConfigurationFile());
		
		XPBook.proxy.preInit(event);
	}
	
	@Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
		XPBook.proxy.init(event);
		
		XPBItems.register();
		
		XPBook.proxy.initBuiltinShapes();
		
		XPBCraftingHandler.addRecipes();
    }
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		XPBook.proxy.postInit(event);
	}
}

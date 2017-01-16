package xyz.baudelaplace;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.baudelaplace.items.BauItems;
import xyz.baudelaplace.proxy.CommonProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class Bau.
 */
@Mod(modid = Bau.MODID, name = Bau.NAME , version = Bau.VERSION)
public class Bau {
	
	/** The Constant MODID. */
	public static final String MODID = "baudelaplace";
	
	/** The Constant NAME. */
	public static final String NAME = "Bau de Laplace";
	
	/** The Constant VERSION. */
	public static final String VERSION = "0.0.1";
	
	/** The instance. */
	@Mod.Instance(MODID)
	public static Bau instance;
	
	/** The proxy. */
	@SidedProxy(serverSide = "xyz.baudelaplace.proxy.ServerProxy", clientSide = "xyz.baudelaplace.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	/** The Constant logger. */
	public static final Logger logger = LogManager.getLogger(Bau.MODID);

	/**
	 * Pre init.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e) {
		// Register and reference all custom item models
		BauItems.init();
		
		proxy.handlePreInit();
	}
	
	/**
	 * Inits the.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler
	public static void init(FMLInitializationEvent e) {
		proxy.handleInit();
	}
	
}

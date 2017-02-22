package xyz.baudelaplace.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.baudelaplace.Bau;
import xyz.baudelaplace.items.templates.CustomItem;

/**
 * The Class BauItems.
 */
public class BauItems {

	/** The some item. */
	@SuppressWarnings("unused")
	private static CustomItem someItem;

	/** The laplace 47. */
	@SuppressWarnings("unused")
	private static Laplace47 laplace47;

	/**
	 * Register.
	 *
	 * @param <T>
	 *            Forcing all mod items to derive from CustomItem class.
	 * @param item
	 *            The item to be registered
	 * @return Registered Item
	 */
	private static <T extends CustomItem> T register(T item) {
		GameRegistry.register(item);
		Bau.proxy.registerItemRenderer(item);
		return item;
	}

	/**
	 * Inits the.
	 */
	public static void registerAll() {
		someItem = register(new CustomItem("someItem", CreativeTabs.REDSTONE));
		laplace47 = register(new Laplace47("laplace_47", CreativeTabs.COMBAT));
	}

}

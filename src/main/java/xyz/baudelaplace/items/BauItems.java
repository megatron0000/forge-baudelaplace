package xyz.baudelaplace.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	 *            the generic type
	 * @param item
	 *            the item
	 * @return the t
	 */
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);
	
		if (item instanceof CustomItem) {
			((CustomItem) item).registerItemModel();
		}
	
		return item;
	}

	/**
	 * Inits the.
	 */
	public static void init() {
		someItem = register(new CustomItem("someItem", CreativeTabs.REDSTONE));
		laplace47 = register(
				new Laplace47("laplace_47", CreativeTabs.COMBAT));
	}

}

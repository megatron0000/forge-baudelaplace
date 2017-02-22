package xyz.baudelaplace.proxy;

import net.minecraft.item.Item;

/**
 * The Class CommonProxy.
 */
public abstract class CommonProxy {

	/**
	 * Register item renderer.
	 *
	 * @param item
	 *            the item
	 */
	public abstract void registerItemRenderer(Item item);

	/**
	 * Register entity renderer.
	 */
	public abstract void registerAllEntityRenderers();

}

package xyz.baudelaplace.proxy;

import net.minecraft.item.Item;

/**
 * The Class ServerProxy.
 */
public class ServerProxy extends CommonProxy {

	public void registerItemRenderer(Item item) {
		// Do nothing. We are on the server
	}

	public void registerAllEntityRenderers() {
		// Do nothing. We are on the server
	}
}

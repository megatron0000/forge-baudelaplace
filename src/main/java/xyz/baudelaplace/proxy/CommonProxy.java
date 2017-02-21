package xyz.baudelaplace.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xyz.baudelaplace.Bau;
import xyz.baudelaplace.entities.projectiles.laserbullet.EntityLaserBullet;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonProxy.
 */
public class CommonProxy {

	/** The entity index. */
	protected int entityIndex = 0;

	/**
	 * Register item renderer.
	 *
	 * @param item
	 *            the item
	 */
	public void registerItemRenderer(Item item) {
		Bau.logger.warn("Common Proxy did nothing when registerItemRenderer was called");
		// Do nothing, as this method is unique to the "Combined Client"
	}

	/**
	 * Handle pre init.
	 */
	public void handlePreInit() {
		EntityRegistry.registerModEntity(EntityLaserBullet.class, "laser_bullet", ++this.entityIndex, Bau.instance, 64,
				20 /* update frequency */, true);
	}

	/**
	 * Handle init.
	 */
	public void handleInit() {
	}

}

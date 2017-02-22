package xyz.baudelaplace.entities;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import xyz.baudelaplace.Bau;
import xyz.baudelaplace.entities.projectiles.laserbullet.EntityLaserBullet;

/**
 * Hard codes all instances of all entites created by this mod.
 */
public class BauEntities {

	protected static int entityIndex = 0;

	public static void registerAll() {
		EntityRegistry.registerModEntity(EntityLaserBullet.class, "laser_bullet", ++BauEntities.entityIndex,
				Bau.instance, 64, 20 /* update frequency */, true);
		Bau.proxy.registerAllEntityRenderers();
	}
}

package xyz.baudelaplace.entities.projectiles.laserbullet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import xyz.baudelaplace.Bau;

/**
 * The Class RenderLaserBullet.
 *
 * @param <T>
 *            the generic type
 */
public class RenderLaserBullet<T extends EntityLaserBullet> extends Render<T> {

	/** The model laser bullet. */
	protected ModelLaserBullet modelLaserBullet = new ModelLaserBullet();

	/* (non-Javadoc)
	 * @see net.minecraft.client.renderer.entity.Render#getEntityTexture(net.minecraft.entity.Entity)
	 */
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return new ResourceLocation(Bau.MODID, "textures/entity/laser_bullet.png");
	}

	/**
	 * Instantiates a new render laser bullet.
	 *
	 * @param renderManager
	 *            the render manager
	 */
	public RenderLaserBullet(RenderManager renderManager) {
		super(renderManager);
	}
	
	/* (non-Javadoc)
	 * @see net.minecraft.client.renderer.entity.Render#doRender(net.minecraft.entity.Entity, double, double, double, float, float)
	 */
	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x,(float) y,(float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
		this.modelLaserBullet.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

}

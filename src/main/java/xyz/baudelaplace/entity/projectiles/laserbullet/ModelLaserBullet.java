package xyz.baudelaplace.entity.projectiles.laserbullet;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ModelLaserBullet.
 */
public class ModelLaserBullet extends ModelBase {
	
	/** The body. */
	public ModelRenderer body;

	/**
	 * Instantiates a new model laser bullet.
	 */
	public ModelLaserBullet() {
		this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-16.0F, -1.5F, -1.5F, 16, 3, 3, 0.0F);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.model.ModelBase#render(net.minecraft.entity.Entity, float, float, float, float, float, float)
	 */
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.body.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts.
	 * Não sei se vou usar
	 *
	 * @param modelRenderer
	 *            the model renderer
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

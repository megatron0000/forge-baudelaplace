package xyz.baudelaplace.entities.living;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// TODO: Auto-generated Javadoc
/**
 * ModelTurtle - Vitor Created using Tabula 5.1.0
 */
public class ModelTurtle extends ModelBase {
	
	/** The body. */
	public ModelRenderer body;
	
	/** The leg 4. */
	public ModelRenderer leg4;
	
	/** The leg 3. */
	public ModelRenderer leg3;
	
	/** The leg 2. */
	public ModelRenderer leg2;
	
	/** The leg 1. */
	public ModelRenderer leg1;
	
	/** The head. */
	public ModelRenderer head;

	/**
	 * Instantiates a new model turtle.
	 */
	public ModelTurtle() {
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.head = new ModelRenderer(this, 45, 28);
		this.head.setRotationPoint(0.0F, 19.0F, -8.0F);
		this.head.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);

		this.leg1 = new ModelRenderer(this, 28, 0);
		this.leg1.setRotationPoint(-4.0F, 20.0F, -6.0F);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);

		this.leg2 = new ModelRenderer(this, 44, 8);
		this.leg2.setRotationPoint(-4.0F, 20.0F, 7.0F);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);

		this.leg3 = new ModelRenderer(this, 28, 8);
		this.leg3.setRotationPoint(4.0F, 20.0F, -6.0F);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);

		this.leg4 = new ModelRenderer(this, 44, 0);
		this.leg4.setRotationPoint(4.0F, 20.0F, 7.0F);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 4, 4, 0.0F);

		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 17.0F, 2.0F);
		this.body.addBox(-6.0F, -10.0F, -3.0F, 12, 18, 2, 0.0F);

		this.body.setTextureOffset(28, 16).addBox(-5.0F, -9.0F, -1.0F, 10, 16, 1, 0.0F);
		this.body.setTextureOffset(0, 33).addBox(-4.0F, -8.0F, 0.0F, 8, 14, 1, 0.0F);
		this.body.setTextureOffset(0, 20).addBox(-3.0F, -7.0F, 1.0F, 6, 12, 1, 0.0F);

	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.model.ModelBase#render(net.minecraft.entity.Entity, float, float, float, float, float, float)
	 */
	@Override
	public void render(Entity entity, float time, float limbSwingDistance, float f, float headYRot, float headXRot,
			float yTrans) {

		this.setRotationAngles(time, limbSwingDistance, f, headYRot, headXRot, yTrans, entity);

		if (this.isChild) {
			float div = 2.0F;
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / div, 1.0F / div, 1.0F / div);
			GlStateManager.translate(0.0F, 24.0F * yTrans, 0.0F);

			this.head.render(yTrans);
			this.body.render(yTrans);
			this.leg1.render(yTrans);
			this.leg2.render(yTrans);
			this.leg3.render(yTrans);
			this.leg4.render(yTrans);

			GlStateManager.popMatrix();

		} else {

			this.head.render(yTrans);
			this.body.render(yTrans);
			this.leg1.render(yTrans);
			this.leg2.render(yTrans);
			this.leg3.render(yTrans);
			this.leg4.render(yTrans);

		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.model.ModelBase#setRotationAngles(float, float, float, float, float, float, net.minecraft.entity.Entity)
	 */
	public void setRotationAngles(float time, float limbSwingDistance, float ageInTicks, float headYRot, float headXRot,
			float scaleFactor, Entity entity) {

		this.head.rotateAngleX = headXRot / (180F / (float) Math.PI);
		this.head.rotateAngleY = headYRot / (180F / (float) Math.PI);

		float phase = MathHelper.cos(time * 0.662F);
		float invertedPhase = -phase;
		float multiplier = 1.4F * limbSwingDistance;
		
		this.leg1.rotateAngleX = phase * multiplier;
		this.leg2.rotateAngleX = invertedPhase * multiplier;
		this.leg3.rotateAngleX = invertedPhase * multiplier;
		this.leg4.rotateAngleX = phase * multiplier;

	}

}

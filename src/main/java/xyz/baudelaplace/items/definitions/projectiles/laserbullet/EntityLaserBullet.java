package xyz.baudelaplace.items.definitions.projectiles.laserbullet;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// TODO: Auto-generated Javadoc
/**
 * Ammunition for laplace_47.
 */
public class EntityLaserBullet extends EntityArrow {

	/**
	 * Instantiates ammunition.
	 *
	 * @param worldIn
	 *            World in which to instantiate
	 * @param throwerIn
	 *            Which {@link EntityLivingBase} is throwing the ammo
	 */
	public EntityLaserBullet(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
		this.setNoGravity(true);
	}

	/**
	 * Instantiates a new entity laser bullet.
	 *
	 * @param worldIn
	 *            the world in
	 */
	public EntityLaserBullet(World worldIn) {
		super(worldIn);
	}

	/**
	 * Instantiates a new entity laser bullet.
	 *
	 * @param worldIn
	 *            the world in
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public EntityLaserBullet(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	/**
	 * On update.
	 */
	@Override
	public void onUpdate() {
		// onUpdate de EntityArrow, que (por sua vez) chama onUpdate de
		// Entity (a qual atualiza posições e ângulos de giro). Ainda não sei
		// implementar essa física, então a super manipula tudo para mim
		super.onUpdate();
		// Not affected by gravity, so could fly endlessly. Not anymore, since
		// we will restrict max lifetime to 5 seconds
		if (this.ticksExisted > 20 * 5)
			this.setDead();
	}

	/* (non-Javadoc)
	 * @see net.minecraft.entity.projectile.EntityArrow#getArrowStack()
	 */
	@Override
	protected ItemStack getArrowStack() {
		ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
		return itemstack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.entity.projectile.EntityArrow#onHit(net.minecraft.util.math
	 * .RayTraceResult)
	 */
	protected void onHit(RayTraceResult raytraceResult) {
		super.onHit(raytraceResult);
		if (!this.worldObj.isRemote) {
			this.worldObj.createExplosion(null, raytraceResult.hitVec.xCoord, raytraceResult.hitVec.yCoord,
					raytraceResult.hitVec.zCoord, 2 /* 3 blocks in radius */,
					true /* does damage */
			);
			this.setDead();
		}
	}

}

package xyz.baudelaplace.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.baudelaplace.entity.projectiles.laserbullet.EntityLaserBullet;
import xyz.baudelaplace.items.templates.CustomMeshItem;
import xyz.baudelaplace.items.templates.MetadataItemState;

/**
 * The Class Laplace47.
 */
public class Laplace47 extends CustomMeshItem<Laplace47.Laplace47State> {

	/**
	 * The Enum Laplace47State.
	 */
	public enum Laplace47State implements MetadataItemState {

		/** The active. */
		ACTIVE("active", 1),
		/** The inactive. */
		INACTIVE("inactive", 0);

		/** The metadata. */
		private int metadata;

		/** The state name. */
		private String stateName;

		/**
		 * Instantiates a new laplace 47 state.
		 *
		 * @param stateName
		 *            the state name
		 * @param metadata
		 *            the metadata
		 */
		private Laplace47State(String stateName, int metadata) {
			this.stateName = stateName;
			this.metadata = metadata;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * xyz.baudelaplace.items.definitions.MetadataItemType#getMetadata()
		 */
		public int getMetadata() {
			return this.metadata;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see xyz.baudelaplace.items.definitions.MetadataItemType#getName()
		 */
		public String getName() {
			return this.stateName;
		}

	}

	/**
	 * Instantiates a new laplace 47.
	 *
	 * @param name
	 *            the name
	 * @param tab
	 *            the tab
	 * @param EnumTypes
	 *            the enum types
	 */
	public Laplace47(String name, CreativeTabs tab) {
		super(name, tab, Laplace47State.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#getItemUseAction(net.minecraft.item.ItemStack)
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		// Escolher um desses valores garante execução de animação predefinida
		// (não sei onde é definida, porém)
		return EnumAction.BOW;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#getMaxItemUseDuration(net.minecraft.item.
	 * ItemStack)
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		// Valor em "ticks"
		return 20 * 60 * 60;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer,
	 * net.minecraft.util.EnumHand)
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		// Útil para algo ?
		player.setActiveHand(EnumHand.MAIN_HAND);
		// Entre FAIL, SUCCESS e PASS, acho que o terceiro delega ao código
		// Vanilla
		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#onPlayerStoppedUsing(net.minecraft.item.
	 * ItemStack, net.minecraft.world.World,
	 * net.minecraft.entity.EntityLivingBase, int)
	 */
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
		EntityPlayer entityPlayer = (EntityPlayer) entity;
		int chargeTime = this.getMaxItemUseDuration(stack) - timeLeft;
		chargeTime = ForgeEventFactory.onArrowLoose(stack /* BOW */, world, entityPlayer, chargeTime,
				true /* has Ammo */);
		// Force 1 second of charging (at least)
		if (chargeTime <= 20)
			return;

		stack.setItemDamage(0);
		if (!world.isRemote) {
			// float velocityFromCharge = new Object() {
			// public float getVelocity(int charge) {
			// float f = (float) charge / 20.0F;
			// f = (f * f + f * 2.0F) / 3.0F;
			//
			// if (f > 1.0F) {
			// f = 1.0F;
			// }
			//
			// return 3 * f;
			// };
			// }.getVelocity(chargeTime);
			EntityLaserBullet laserBullet = new EntityLaserBullet(world, entity);
			laserBullet.setAim(entityPlayer, entityPlayer.rotationPitch, entityPlayer.rotationYaw, 0.0F,
					3 /* max velocity */, 0.0F);
			world.spawnEntityInWorld(laserBullet);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#onUpdate(net.minecraft.item.ItemStack,
	 * net.minecraft.world.World, net.minecraft.entity.Entity, int, boolean)
	 * 
	 */
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		/**
		 * getItemUseCount behaves in 2 different ways:
		 * 
		 * - If item is not being "right clicked", it is 0
		 * 
		 * - If item is being "right clicked", it starts at
		 * getMaxItemUseDuration() and decreases
		 */
		int timeInUse = 0;
		if (((EntityLivingBase) entityIn).getItemInUseCount() != 0)
			timeInUse = stack.getMaxItemUseDuration() - ((EntityLivingBase) entityIn).getItemInUseCount();

		int metadata = stack.getItemDamage();
		if (isSelected && timeInUse > 20 && metadata == 0)
			stack.setItemDamage(1);
		else if (!isSelected && metadata != 0)
			stack.setItemDamage(0);
	}

}

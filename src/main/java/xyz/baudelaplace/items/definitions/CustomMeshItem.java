package xyz.baudelaplace.items.definitions;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import xyz.baudelaplace.items.definitions.projectiles.laserbullet.EntityLaserBullet;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomMeshItem.
 *
 * @param <E>
 *            the element type
 */
public class CustomMeshItem<E extends Enum<E> & MetadataItemType> extends MetadataItem<E> {

	/**
	 * Instantiates a new custom mesh item.
	 *
	 * @param name
	 *            the name
	 * @param tab
	 *            the tab
	 * @param EnumTypes
	 *            the enum types
	 */
	public CustomMeshItem(String name, CreativeTabs tab, Class<E> EnumTypes) {
		super(name, tab, EnumTypes);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getItemUseAction(net.minecraft.item.ItemStack)
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		// Escolher um desses valores garante execução de animação predefinida
		// (não sei onde é definida, porém
		return EnumAction.BOW;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getMaxItemUseDuration(net.minecraft.item.ItemStack)
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		// Valor em "ticks"
		return 20 * 60 * 60;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#onItemRightClick(net.minecraft.item.ItemStack, net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, net.minecraft.util.EnumHand)
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		// Útil para algo ?
		player.setActiveHand(EnumHand.MAIN_HAND);
		// Entre FAIL, SUCCESS e PASS, acho que o terceiro delega ao código
		// Vanilla
		return new ActionResult<>(EnumActionResult.PASS, stack);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#onPlayerStoppedUsing(net.minecraft.item.ItemStack, net.minecraft.world.World, net.minecraft.entity.EntityLivingBase, int)
	 */
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
		EntityPlayer entityPlayer = (EntityPlayer) entity;
		int chargeTime = this.getMaxItemUseDuration(stack) - timeLeft;
		chargeTime = ForgeEventFactory.onArrowLoose(stack /* BOW */, world, entityPlayer, chargeTime,
				true /* has Ammo */);
		// Force 1 second of charging (at least)
		if (chargeTime < 20)
			return;

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

}

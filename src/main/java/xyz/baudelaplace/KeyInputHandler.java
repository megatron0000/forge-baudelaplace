package xyz.baudelaplace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyInputHandler.
 */
public class KeyInputHandler {

	/**
	 * On key input.
	 *
	 * @param e
	 *            the e
	 */
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent e) {
		if (KeyBinds.changeItemMeta.isPressed()) {
			EntityPlayerSP p = Minecraft.getMinecraft().thePlayer;
			ItemStack inHand = p.inventory.getCurrentItem();
			if (inHand != null)
				inHand.setItemDamage((inHand.getMetadata() + 1) % 2);
		}
	}
}

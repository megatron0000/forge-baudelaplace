package xyz.baudelaplace;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyBinds.
 */
public class KeyBinds {
	
	/** The change item meta. */
	public static KeyBinding changeItemMeta;
	
	/**
	 * Inits the.
	 */
	public static void init() {
		changeItemMeta = new KeyBinding("changeMetadataKey", Keyboard.KEY_V, "key.categories.BauDeLaplace");
		ClientRegistry.registerKeyBinding(changeItemMeta);
	}
}

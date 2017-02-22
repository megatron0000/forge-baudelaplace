package xyz.baudelaplace.items.templates;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

/**
 * The Class BauMeshDefinition.
 *
 * @param <E>
 *            the element type
 */
public class BauMeshDefinition<E extends Enum<E> & MetadataItemState> implements ItemMeshDefinition {
	
	/** The custom mesh item. */
	private final CustomMeshItem<E> customMeshItem;

	/**
	 * Instantiates a new bau mesh definition.
	 *
	 * @param customMeshItem
	 *            the custom mesh item
	 */
	public BauMeshDefinition(CustomMeshItem<E> customMeshItem) {
		this.customMeshItem = customMeshItem;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.client.renderer.ItemMeshDefinition#getModelLocation(net.minecraft.item.ItemStack)
	 */
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		return getModelLocation(stack.getMetadata());
	}

	/**
	 * Gets the model location.
	 *
	 * @param metadata
	 *            the metadata
	 * @return the model location
	 */
	public ModelResourceLocation getModelLocation(int metadata) {
		int validMetadata = metadata >= this.customMeshItem.getTypes().length ? 0 : metadata;

		for (int i = 0; i < this.customMeshItem.getTypes().length; i++) {
			if (((MetadataItemState) this.customMeshItem.getTypes()[i]).getMetadata() == validMetadata) {
				return new ModelResourceLocation(this.customMeshItem.getRegistryName() + "_"
						+ ((MetadataItemState) this.customMeshItem.getTypes()[i]).getName(), "inventory");
			}
		}
		// Este return nunca será usado...
		return null;
	}
}
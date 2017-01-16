package xyz.baudelaplace.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.baudelaplace.items.definitions.CustomItem;
import xyz.baudelaplace.items.definitions.CustomMeshItem;
import xyz.baudelaplace.items.definitions.MetadataItemType;

// TODO: Auto-generated Javadoc
/**
 * The Class BauItems.
 */
public class BauItems {

	/** The some item. */
	@SuppressWarnings("unused")
	private static CustomItem someItem;

	/** The laplace 47. */
	@SuppressWarnings("unused")
	private static CustomMeshItem<Laplace_47_State> laplace_47;

	/**
	 * The Enum Laplace_47_State.
	 */
	public enum Laplace_47_State implements MetadataItemType {
		
		/** The inactive. */
		INACTIVE("inactive", 0), 
 /** The active. */
 ACTIVE("active", 1);

		/** The state name. */
		private String stateName;
		
		/** The metadata. */
		private int metadata;

		/**
		 * Instantiates a new laplace 47 state.
		 *
		 * @param stateName
		 *            the state name
		 * @param metadata
		 *            the metadata
		 */
		private Laplace_47_State(String stateName, int metadata) {
			this.stateName = stateName;
			this.metadata = metadata;
		}

		/* (non-Javadoc)
		 * @see xyz.baudelaplace.items.definitions.MetadataItemType#getName()
		 */
		public String getName() {
			return this.stateName;
		}

		/* (non-Javadoc)
		 * @see xyz.baudelaplace.items.definitions.MetadataItemType#getMetadata()
		 */
		public int getMetadata() {
			return this.metadata;
		}

	}

	
	/**
	 * Inits the.
	 */
	public static void init() {
		someItem = register(new CustomItem("someItem", CreativeTabs.REDSTONE));
		laplace_47 = register(
				new CustomMeshItem<Laplace_47_State>("laplace_47", CreativeTabs.REDSTONE, Laplace_47_State.class));
	}

	/**
	 * Register.
	 *
	 * @param <T>
	 *            the generic type
	 * @param item
	 *            the item
	 * @return the t
	 */
	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof CustomItem) {
			((CustomItem) item).registerItemModel();
		}

		return item;
	}
}

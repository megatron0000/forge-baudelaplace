package xyz.baudelaplace.items.templates;

import net.minecraft.creativetab.CreativeTabs;

/**
 * The Class CustomMeshItem.
 *
 * @param <E>
 *            the element type
 */
public class CustomMeshItem<E extends Enum<E> & MetadataItemState> extends MetadataItem<E> {

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
}

package xyz.baudelaplace.items.templates;

import net.minecraft.creativetab.CreativeTabs;

// TODO: Auto-generated Javadoc
/**
 * Itens com diferentes texturas para diferentes metadata.
 *
 * @param <E>
 *            Algum Enum que liste variantes do item (i.e, metadatas
 *            diferentes). Observe que deve implementar
 *            {@link MetadataItemState}.
 */
public class MetadataItem<E extends Enum<E> & MetadataItemState> extends CustomItem {

	/** The types. */
	private E[] types;

	/**
	 * Retorna as variantes do item.
	 *
	 * @return As variantes
	 */
	public E[] getTypes() {
		return types;
	}

	/**
	 * Cria novo item (como em {@link CustomItem}) e registra que ele "possui
	 * subtipos".
	 *
	 * @param name
	 *            Nome do item a ser criado
	 * @param tab
	 *            Tab do creative
	 * @param EnumTypes
	 *            Classe (Enum) que lista os subtipos do item
	 */
	public MetadataItem(String name, CreativeTabs tab, Class<E> EnumTypes) {
		super(name, tab);
		setHasSubtypes(true);
		setMaxDamage(0);
		this.types = EnumTypes.getEnumConstants();
	}

}

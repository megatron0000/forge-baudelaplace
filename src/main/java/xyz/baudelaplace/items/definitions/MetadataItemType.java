package xyz.baudelaplace.items.definitions;

// TODO: Auto-generated Javadoc
/**
 * Elementos que deve ter um "subtipo" de um item com metadata (i.e, nome e
 * valor de metadata)
 */
public interface MetadataItemType {

	/**
	 * Nome deste subtipo (por exemplo: "laplace_47_inactive").
	 *
	 * @return Nome
	 */
	public String getName();

	/**
	 * Metadata desta variante do item (exemplo: "laplace_47_inactive" pode ter
	 * metadata 0, enquanto "laplace_47_active" pode ter metadata 1).
	 *
	 * @return Integer que é a metadata
	 */
	public int getMetadata();
}

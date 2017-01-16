package xyz.baudelaplace.items.templates;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.baudelaplace.Bau;

/**
 * Classe base para todos os ítens customizados.
 */
public class CustomItem extends Item {

	/**
	 * Cria item novo a partir de um nome e de uma "tab" onde colocá-lo.
	 *
	 * @param name
	 *            Nome do item a ser criado
	 * @param tab
	 *            Tab do inventário onde este item será posto
	 */
	public CustomItem(String registryName, CreativeTabs tab) {
		super();
		setCreativeTab(tab);
		setUnlocalizedName(registryName);
		setRegistryName(registryName);
	}

	/**
	 * Registra o modelo deste item (somente no lado do cliente !).
	 */
	public void registerItemModel() {
		Bau.proxy.registerItemRenderer(this);
	}
}

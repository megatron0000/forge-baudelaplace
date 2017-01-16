package xyz.baudelaplace.proxy;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.baudelaplace.KeyBinds;
import xyz.baudelaplace.KeyInputHandler;
import xyz.baudelaplace.items.definitions.BauMeshDefinition;
import xyz.baudelaplace.items.definitions.CustomMeshItem;
import xyz.baudelaplace.items.definitions.MetadataItem;
import xyz.baudelaplace.items.definitions.MetadataItemType;
import xyz.baudelaplace.items.definitions.projectiles.laserbullet.EntityLaserBullet;
import xyz.baudelaplace.items.definitions.projectiles.laserbullet.RenderLaserBullet;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientProxy.
 */
public class ClientProxy extends CommonProxy {

	/* (non-Javadoc)
	 * @see xyz.baudelaplace.proxy.CommonProxy#handlePreInit()
	 */
	@Override
	public void handlePreInit() {
		super.handlePreInit();
		KeyBinds.init();
		MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBullet.class,
				new IRenderFactory<EntityLaserBullet>() {
					public Render<EntityLaserBullet> createRenderFor(RenderManager renderManager) {
						return new RenderLaserBullet<EntityLaserBullet>(renderManager);
					}
				});
	}

	/* (non-Javadoc)
	 * @see xyz.baudelaplace.proxy.CommonProxy#handleInit()
	 */
	@Override
	public void handleInit() {
	}

	/**
	 * Registra o renderizador do item (somente no lado do cliente !).
	 *
	 * @param item
	 *            Item a ser registrado
	 */
	@Override
	public void registerItemRenderer(Item item) {

		if (item instanceof CustomMeshItem<?>) {
			CustomMeshItem<?> itemAsCustomMesh = (CustomMeshItem<?>) item;
			@SuppressWarnings({ "rawtypes", "unchecked" })
			BauMeshDefinition<?> mesh = new BauMeshDefinition((CustomMeshItem<?>) item);
			// Lembre que CustomMeshItem<?> implementa ItemMeshDefinition
			ModelLoader.setCustomMeshDefinition(item, mesh);
			for (Enum<? extends MetadataItemType> type : itemAsCustomMesh.getTypes()) {
				int metadata = ((MetadataItemType) type).getMetadata();
				ModelBakery.registerItemVariants(item, mesh.getModelLocation(metadata));
			}
		} else if (item instanceof MetadataItem)
			for (Enum<? extends MetadataItemType> type : ((MetadataItem<?>) item).getTypes()) {
				String variantName = ((MetadataItemType) type).getName();
				int metadata = ((MetadataItemType) type).getMetadata();
				// (baudelaplace:item_registry_name , variantName)
				// Nome de registro já é precedido de "baudelaplace:"
				ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName() + "_" + variantName,
						"inventory");
				// (item, metadata, ModelResourceLocation)
				ModelLoader.setCustomModelResourceLocation(item, metadata, model);
			}
		else {
			// (baudelaplace:item_registry_name , variantName)
			// Nome de registro já é precedido de "baudelaplace:"
			ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
			// (item, metadata, ModelResourceLocation)
			ModelLoader.setCustomModelResourceLocation(item, 0, model);
		}

	}

}

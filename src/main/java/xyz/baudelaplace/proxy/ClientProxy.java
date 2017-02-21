package xyz.baudelaplace.proxy;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.baudelaplace.entities.projectiles.laserbullet.EntityLaserBullet;
import xyz.baudelaplace.entities.projectiles.laserbullet.RenderLaserBullet;
import xyz.baudelaplace.items.templates.BauMeshDefinition;
import xyz.baudelaplace.items.templates.CustomMeshItem;
import xyz.baudelaplace.items.templates.MetadataItem;
import xyz.baudelaplace.items.templates.MetadataItemState;

/**
 * The Class ClientProxy.
 */
public class ClientProxy extends CommonProxy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see xyz.baudelaplace.proxy.CommonProxy#handlePreInit()
	 */
	@Override
	public void handlePreInit() {
		super.handlePreInit();
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBullet.class,
				new IRenderFactory<EntityLaserBullet>() {
					public Render<EntityLaserBullet> createRenderFor(RenderManager renderManager) {
						return new RenderLaserBullet<EntityLaserBullet>(renderManager);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
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

		if (item instanceof CustomMeshItem) {
			CustomMeshItem<?> itemAsCustomMesh = (CustomMeshItem<?>) item;
			BauMeshDefinition<?> mesh = new BauMeshDefinition<>((CustomMeshItem<?>) item);
			// Lembre que CustomMeshItem<?> implementa ItemMeshDefinition
			ModelLoader.setCustomMeshDefinition(item, mesh);
			for (Enum<? extends MetadataItemState> type : itemAsCustomMesh.getTypes()) {
				int metadata = ((MetadataItemState) type).getMetadata();
				ModelBakery.registerItemVariants(item, mesh.getModelLocation(metadata));
			}
		} else if (item instanceof MetadataItem)
			for (Enum<? extends MetadataItemState> type : ((MetadataItem<?>) item).getTypes()) {
				String variantName = ((MetadataItemState) type).getName();
				int metadata = ((MetadataItemState) type).getMetadata();

				ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName() + "_" + variantName,
						"inventory");
				ModelLoader.setCustomModelResourceLocation(item, metadata, model);
			}
		else {
			ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
			ModelLoader.setCustomModelResourceLocation(item, 0, model);
		}

	}

}

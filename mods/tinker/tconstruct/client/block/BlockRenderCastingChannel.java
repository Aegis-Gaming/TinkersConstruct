package mods.tinker.tconstruct.client.block;

import mods.tinker.tconstruct.blocks.logic.CastingChannelLogic;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * @author BluSunrize
 */

public class BlockRenderCastingChannel implements ISimpleBlockRenderingHandler
{
	public static int renderID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		//Floor
		renderer.setRenderBounds(0.3125D, 0.375D, 0.3125D, 0.6875D, 0.5D, 0.6875D);
		this.renderStandardBlock(block, metadata, renderer);
		//Channel Z-
		renderer.setRenderBounds(0.3125D, 0.375D, 0D, 0.6875D, 0.5D, 0.3125D);
		this.renderStandardBlock(block, metadata, renderer);
		renderer.setRenderBounds(0.3125D, 0.5D, 0D, 0.375D, 0.625D, 0.3125D);
		this.renderStandardBlock(block, metadata, renderer);
		renderer.setRenderBounds(0.625D, 0.5D, 0D, 0.6875D, 0.625D, 0.3125D);
		this.renderStandardBlock(block, metadata, renderer);
		//Channel Z+
		renderer.setRenderBounds(0.3125D, 0.375D, 0.6875D, 0.6875D, 0.5D, 1D);
		this.renderStandardBlock(block, metadata, renderer);
		renderer.setRenderBounds(0.3125D, 0.5D, 0.6875D, 0.375D, 0.625D, 1D);
		this.renderStandardBlock(block, metadata, renderer);
		renderer.setRenderBounds(0.625D, 0.5D, 0.6875D, 0.6875D, 0.625D, 1D);
		this.renderStandardBlock(block, metadata, renderer);
		//Wall X-
		renderer.setRenderBounds(0.3125D, 0.375D, 0.3125D, 0.375D, 0.625D, 0.6875D);
		this.renderStandardBlock(block, metadata, renderer);
		//Wall X+
		renderer.setRenderBounds(0.625D, 0.375D, 0.3125D, 0.6875D, 0.625D, 0.6875D);
		this.renderStandardBlock(block, metadata, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		if (modelID == renderID)
		{
			CastingChannelLogic tile = (CastingChannelLogic)world.getBlockTileEntity(x, y, z);

			if(!tile.hasTankConnected(ForgeDirection.DOWN))//CentrePiece, floor is removed if tank below is found
			{
				renderer.setRenderBounds(0.3125D, 0.375D, 0.3125D, 0.6875D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else//"Guiding Borders" when tank below is found
			{
				renderer.setRenderBounds(0.375D, 0.125D, 0.3125D, 0.625D, 0.5D, 0.375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.375D, 0.125D, 0.625D, 0.625D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.3125D, 0.125D, 0.3125D, 0.375D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625D, 0.125D, 0.3125D, 0.6875D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			/*renderer.setRenderBounds(0.3125D, 0.375D, 0.3125D, 0.375D, 0.625D, 0.375D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(0.3125D, 0.375D, 0.625D, 0.375D, 0.625D, 0.6875D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(0.625D, 0.375D, 0.3125D, 0.6875D, 0.625D, 0.375D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setRenderBounds(0.625D, 0.375D, 0.625D, 0.6875D, 0.625D, 0.6875D);
			renderer.renderStandardBlock(block, x, y, z);*/
			if(tile.hasTankConnected(ForgeDirection.NORTH))//Channel to Z-
			{
				renderer.setRenderBounds(0.3125D, 0.375D, 0D, 0.6875D, 0.5D, 0.3125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.3125D, 0.5D, 0D, 0.375D, 0.625D, 0.3125D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625D, 0.5D, 0D, 0.6875D, 0.625D, 0.3125D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else//Wall to Z-
			{
				renderer.setRenderBounds(0.375D, 0.5D, 0.3125D, 0.625D, 0.625D, 0.375D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			
			if(tile.hasTankConnected(ForgeDirection.SOUTH))//Channel to Z+
			{
				renderer.setRenderBounds(0.3125D, 0.375D, 0.6875D, 0.6875D, 0.5D, 1D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.3125D, 0.5D, 0.6875D, 0.375D, 0.625D, 1D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625D, 0.5D, 0.6875D, 0.6875D, 0.625D, 1D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else//Wall to Z+
			{
				renderer.setRenderBounds(0.375D, 0.5D, 0.625D, 0.625D, 0.625D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			
			if(tile.hasTankConnected(ForgeDirection.WEST))//Channel to X-
			{
				renderer.setRenderBounds(0D, 0.375D, 0.3125D, 0.3125D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0D, 0.5D, 0.3125D, 0.375D, 0.625D, 0.375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0D, 0.5D, 0.625D, 0.375D, 0.625D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else//Wall to X-
			{
				renderer.setRenderBounds(0.3125D, 0.5D, 0.3125D, 0.375D, 0.625D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			
			if(tile.hasTankConnected(ForgeDirection.EAST))//Channel to X+
			{
				renderer.setRenderBounds(0.6875D, 0.375D, 0.3125D, 1D, 0.5D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625D, 0.5D, 0.3125D, 1D, 0.625D, 0.375D);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setRenderBounds(0.625D, 0.5D, 0.625D, 1D, 0.625D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}
			else//Wall to X+
			{
				renderer.setRenderBounds(0.625D, 0.5D, 0.3125D, 0.6875D, 0.625D, 0.6875D);
				renderer.renderStandardBlock(block, x, y, z);
			}


			if (tile.liquid != null)
			{
				float height = tile.getLiquidAmount() / (tile.getCapacity() * 1.05F);				
				float j = tile.liquid.amount / tile.fillMax;
				double startY = tile.tankBelow();
                //if(tile.nearbyChannel(ForgeDirection.DOWN))startY = -0.5D;
                //else if(tile.hasTankConnected(ForgeDirection.DOWN))startY = 0D;
                float liquidAmount = tile.liquid.amount / 288f * 0.125f;
				renderer.setRenderBounds(0.375D, startY, 0.375D, 0.625D, 0.5 + liquidAmount, 0.625D); //Center
				renderLiquidPart(world, x, y, z, block, renderer, tile);
				//System.out.println(tile.recentlyFilledDelay);
				
				if(tile.hasTankConnected(ForgeDirection.NORTH))
				{
					if(tile.lastProvider==ForgeDirection.NORTH)
					{
						renderer.setRenderBounds(0.375D, 0.5D, 0D, 0.625D, 0.5 + liquidAmount, 0.375D);
						renderLiquidPart(world, x, y, z, block, renderer, tile);
					}
					else
					{
						if(tile.recentlyFilledDelay == 0)
						{
							renderer.setRenderBounds(0.375D, 0.5D, 0D, 0.625D, 0.5 + liquidAmount, 0.375D);
							renderLiquidPart(world, x, y, z, block, renderer, tile);
						}
					}
				}
				if(tile.hasTankConnected(ForgeDirection.SOUTH))
				{
					if(tile.lastProvider==ForgeDirection.SOUTH)
					{
						renderer.setRenderBounds(0.375D, 0.5D, 0.625D, 0.625D, 0.5 + liquidAmount, 1D);
						renderLiquidPart(world, x, y, z, block, renderer, tile);
					}
					else
					{
						if(tile.recentlyFilledDelay == 0)
						{
							renderer.setRenderBounds(0.375D, 0.5D, 0.625D, 0.625D, 0.5 + liquidAmount, 1D);
							renderLiquidPart(world, x, y, z, block, renderer, tile);
						}
					}
				}
				if(tile.hasTankConnected(ForgeDirection.WEST))
				{
					if(tile.lastProvider==ForgeDirection.WEST)
					{
						renderer.setRenderBounds(0D, 0.5D, 0.375D, 0.375D, 0.5 + liquidAmount, 0.625D);
						renderLiquidPart(world, x, y, z, block, renderer, tile);
					}
					else
					{
						if(tile.recentlyFilledDelay == 0)
						{
							renderer.setRenderBounds(0D, 0.5D, 0.375D, 0.375D, 0.5 + liquidAmount, 0.625D);
							renderLiquidPart(world, x, y, z, block, renderer, tile);
						}
					}
				}
				if(tile.hasTankConnected(ForgeDirection.EAST))
				{
					if(tile.lastProvider==ForgeDirection.EAST)
					{
						renderer.setRenderBounds(0.625D, 0.5D, 0.375D, 1D, 0.5 + liquidAmount, 0.625D);
						renderLiquidPart(world, x, y, z, block, renderer, tile);
					}
					else
					{
						if(tile.recentlyFilledDelay == 0)
						{
							renderer.setRenderBounds(0.625D, 0.5D, 0.375D, 1D, 0.5 + liquidAmount, 0.625D);
							renderLiquidPart(world, x, y, z, block, renderer, tile);
						}
					}

				}
			}


		}
		return true;
	}

	private void renderLiquidPart(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, CastingChannelLogic tile)
	{
		if (tile.liquid.itemID < 4096)
		{
			Block liquidBlock = Block.blocksList[tile.liquid.itemID];
			if (liquidBlock != null)
			{
				int color = block.colorMultiplier(world, x, y, z);
				float red = (color >> 16 & 0xFF) / 255.0F;
				float green = (color >> 8 & 0xFF) / 255.0F;
				float blue = (color & 0xFF) / 255.0F;
                BlockSkinRenderHelper.renderMetadataBlock(liquidBlock, tile.liquid.itemMeta, x, y, z, renderer, world);
			}

		}
		else
		{
			Item liquidItem = Item.itemsList[tile.liquid.itemID];
			if (liquidItem != null)
			{
				Block block2 = Block.stone;
				int var5 = block2.colorMultiplier(world, x, y, z);
				float var6 = (var5 >> 16 & 0xFF) / 255.0F;
				float var7 = (var5 >> 8 & 0xFF) / 255.0F;
				float var8 = (var5 & 0xFF) / 255.0F;
				RenderLiquid.renderFakeBlockWithColorMultiplier(liquidItem.getIconFromDamage(tile.liquid.itemMeta), x, y, z, var6, var7, var8, renderer, world);
			}
		}
	}
	
	private void renderStandardBlock(Block block, int meta, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
	    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, -1.0F, 0.0F);
	    renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 1.0F, 0.0F);
	    renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 0.0F, -1.0F);
	    renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(0.0F, 0.0F, 1.0F);
	    renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(-1.0F, 0.0F, 0.0F);
	    renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
	    tessellator.draw();
	    tessellator.startDrawingQuads();
	    tessellator.setNormal(1.0F, 0.0F, 0.0F);
	    renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
	    tessellator.draw();
	    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}
}

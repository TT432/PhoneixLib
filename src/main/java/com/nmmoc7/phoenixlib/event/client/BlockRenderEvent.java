package com.nmmoc7.phoenixlib.event.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.eventbus.api.Event;

import java.util.Random;

/**
 * 会在渲染方块的时候被调用
 * 取消的话，这个方块本来的渲染就不会被渲染了
 */
public class BlockRenderEvent extends Event {
    public BlockState blockState;
    public BlockPos posIn;
    public IBlockDisplayReader lightReaderIn;
    public MatrixStack matrixStackIn;
    public IVertexBuilder vertexBuilderIn;
    public boolean checkSides;
    public Random rand;
    public IModelData modelData;

    public BlockRenderEvent(
            BlockState blockState,
            BlockPos posIn,
            IBlockDisplayReader lightReaderIn,
            MatrixStack matrixStackIn,
            IVertexBuilder vertexBuilderIn,
            boolean checkSides,
            Random rand,
            IModelData modelData
    ) {
        this.blockState = blockState;
        this.posIn = posIn;
        this.lightReaderIn = lightReaderIn;
        this.matrixStackIn = matrixStackIn;
        this.vertexBuilderIn = vertexBuilderIn;
        this.checkSides = checkSides;
        this.rand = rand;
        this.modelData = modelData;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}

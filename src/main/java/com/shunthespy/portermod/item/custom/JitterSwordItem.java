package com.shunthespy.portermod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class JitterSwordItem extends Item {
    public JitterSwordItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
    //BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
    //BlockPos lookPos = ray.getBlockPos();
    //player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
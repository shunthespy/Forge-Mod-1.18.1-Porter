package com.shunthespy.portermod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class JitterSwordItem extends Item {
    public JitterSwordItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        BlockHitResult ray = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos();


        int teleDist = 10;
        // double length if crouched
        if(pPlayer.isCrouching()){
            teleDist *= 2;
        }

        //  pPlayer.moveRelative(teleDist, (lookPos.getX(), lookPos.getY(), lookPos.getZ());

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    /*@Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return super.onEntitySwing(stack, entity);
    }*/

    @NotNull //want to eliminate swing hitbox, might not need to tho if not a sword
    @Override
    public AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return super.getSweepHitBox(stack, player, target);
    }
}

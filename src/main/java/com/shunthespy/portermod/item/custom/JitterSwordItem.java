package com.shunthespy.portermod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class JitterSwordItem extends Item {
    public JitterSwordItem(Properties pProperties) { super(pProperties); }

    private static BlockHitResult getPlayerRayResult(Level pLevel, Player pPlayer, double dist) { //grabbed from getPlayerPOVHitResult
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();
        Vec3 vec3 = pPlayer.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = dist;
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, pPlayer));
    }

    public Vec3 teleSword(Player pPlayer, double dist) {
        // new vec with dist
        Vec3 distanceVec = new Vec3(dist, dist, dist);
        // Getting eye location
        Vec3 eyeLocation = pPlayer.getEyePosition();
        // Get dir the eyes are looking at
        Vec3 direction = pPlayer.getLookAngle();
        // Normalize (sets length to 1) then scale with dist and add that to the eye location
        Vec3 scaledDir = eyeLocation.add(direction.normalize().multiply(distanceVec));
        // return new position
        if(pPlayer.getLevel().getBlockState(new BlockPos(scaledDir)).getBlock() == Blocks.AIR) { return scaledDir; }
        BlockHitResult ray = getPlayerRayResult(pPlayer.getLevel(), pPlayer, dist);
        return ray.getLocation(); //if there is no air block at the end of the 10/20 blocks, use rayTrace to find safe
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        double teleDist = 10.0;
        // double length if crouched
        if(pPlayer.isCrouching()){
            teleDist *= 2;
        }
        pPlayer.setPos(teleSword(pPlayer, teleDist));

        pPlayer.level.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0f, 1.0f);

        pPlayer.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.hurt(DamageSource.playerAttack((Player) pAttacker), 8);
        return false;
    }
}

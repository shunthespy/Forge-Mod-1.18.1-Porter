package com.shunthespy.portermod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PowerBoneItem extends Item {
    public PowerBoneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        AABB boundingBox = pPlayer.getBoundingBox(); //just making it a variable to simplify
        boundingBox = boundingBox.inflate(30.0D); //make a bigger radius to check

        List<Wolf> wolves =  pLevel.getEntitiesOfClass(Wolf.class, boundingBox); //finds wolves in radius

        if(wolves.size() > 0){
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 3)); //add strength
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2)); //add speed
            pPlayer.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 2)); //add jump
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1)); //add resist
            pPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 3)); //add absorption
            pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200)); //add glow
        }

        for(int i = 0; i < wolves.size(); i++){
            wolves.get(i).kill(); //kill each wolf on wolves list
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}

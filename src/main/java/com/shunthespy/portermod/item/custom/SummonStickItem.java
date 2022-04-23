package com.shunthespy.portermod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/* TODO: Make NBT data come with item being in inv. that way there is no need to click to see ur mob + xp level
need to make texture vary with nbt
need to test macros for different mobs
add limiting nbt that tracks how many you've spawned
*  */

public class SummonStickItem extends Item {
    public SummonStickItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        int summonVal = 0;
        ItemStack summonStick = entity.getItemInHand(entity.getUsedItemHand());
        CompoundTag summonNBT = new CompoundTag();
        if(summonStick.hasTag()){
            summonVal = (summonStick.getTag().getInt("summonInt"));
            summonNBT.remove("summonInt");
        } else {
            summonNBT.putInt("summonInt",0);
            summonStick.setTag(summonNBT);
        }
        if(entity.isCrouching()){
            summonNBT.putInt("summonInt", incrementSummon(summonVal, false));
            summonStick.setTag(summonNBT);
        } else {
            summonNBT.putInt("summonInt", incrementSummon(summonVal, true));
            summonStick.setTag(summonNBT);
        }

        if(entity.getType() == EntityType.PLAYER) {
            int foo = (summonStick.getTag().getInt("summonInt"));
            entity.sendMessage(new TextComponent("You are now spawning " + getSummon(foo).toString()), entity.getUUID());
        }

        return super.onEntitySwing(stack, entity);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) { //could use this for passives btw
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected); //add nbt
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack summonStick = pPlayer.getItemInHand(pPlayer.getUsedItemHand());
        int summonVal = 0;
        if(summonStick.hasTag()){
            summonVal = (summonStick.getTag().getInt("summonInt"));
        }

        //add entity is now add fresh entity, mc modding moment
        Entity ent = getSummon(summonVal).create(pLevel); //create a new ent in the level
        pLevel.addFreshEntity(ent); //adds to actual world
        ent.setPos(pPlayer.getEyePosition()); //sets to player eye pos, will add directional +5

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public EntityType getSummon(int summon){
        switch(summon){
            case 0:
                return EntityType.STRAY;
            case 1:
                return EntityType.CAVE_SPIDER;
            case 2:
                return EntityType.WITHER_SKELETON;
            case 3:
                return EntityType.ZOMBIFIED_PIGLIN;
            case 4:
                return EntityType.WITCH;
            case 5:
                return EntityType.ENDERMAN;
            case 6:
                return EntityType.VINDICATOR;
            case 7:
                return EntityType.CREEPER;
            case 8:
                return EntityType.HUSK;
            default: return EntityType.AREA_EFFECT_CLOUD; //accidental fucky int catcher;
        }
    }

    private int incrementSummon(int summon, boolean Up){
        if(Up){
            summon++;
            if(summon > 8){
                summon = 0;
            }
        } else {
            summon--;
            if(summon < 0){
                summon = 8;
            }
        }
        return summon;
    }
}

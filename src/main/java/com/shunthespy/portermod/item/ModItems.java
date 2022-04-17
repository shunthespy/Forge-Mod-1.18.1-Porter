package com.shunthespy.portermod.item;

import com.shunthespy.portermod.PorterMod;
import com.shunthespy.portermod.item.custom.PowerBoneItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PorterMod.MOD_ID);

    public static final RegistryObject<Item> POWERBONE = ITEMS.register("powerbone",
            () -> new PowerBoneItem(new Item.Properties().tab(ModCreativeModeTab.PORTER_TAB)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}

package com.shunthespy.portermod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab PORTER_TAB = new CreativeModeTab("portertab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.POWERBONE.get());
        }
    };
}

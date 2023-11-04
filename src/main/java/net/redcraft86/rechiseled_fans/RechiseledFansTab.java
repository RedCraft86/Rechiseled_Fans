package net.redcraft86.rechiseled_fans;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class RechiseledFansTab
{
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RechiseledFans.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RECHISELED_FANS =
            TABS.register("rechiseled_fans_tab",
                    () -> CreativeModeTab.builder().icon(() -> new ItemStack(FanBlockRegistry.FAN_SINGLE.get()))
                            .title(Component.translatable("creativetab.rechiseled_fans"))
                            .displayItems((Parameters, Outputs) ->
                            {
                                for (RegistryObject<Block> Block : FanBlockRegistry.BLOCKS.getEntries())
                                {
                                    if (Block.isPresent()) { Outputs.accept(Block.get()); }
                                }
                            })
                            .build());
}
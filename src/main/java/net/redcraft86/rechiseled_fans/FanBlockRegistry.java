package net.redcraft86.rechiseled_fans;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FanBlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RechiseledFans.MOD_ID);

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RechiseledFans.MOD_ID);

    public static final RegistryObject<Block> FAN_SINGLE = RegisterBlock("fan_single", FanBlockBase::new);
    public static final RegistryObject<Block> FAN_SINGLE_MALFUNCTION = RegisterBlock("fan_single_malfunction", FanBlockBase::new);
    public static final RegistryObject<Block> FAN_QUAD = RegisterBlock("fan_quad", FanBlockBase::new);
    public static final RegistryObject<Block> FAN_QUAD_MALFUNCTION = RegisterBlock("fan_quad_malfunction", FanBlockBase::new);

    private static <T extends Block> RegistryObject<T> RegisterBlock(String Name, Supplier<T> Block)
    {
        RegistryObject<T> RetVal = BLOCKS.register(Name, Block);
        ITEMS.register(Name, () -> new BlockItem(RetVal.get(), new Item.Properties()));
        return RetVal;
    }
}

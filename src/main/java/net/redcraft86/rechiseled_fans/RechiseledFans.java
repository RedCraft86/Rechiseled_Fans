package net.redcraft86.rechiseled_fans;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RechiseledFans.MOD_ID)
public class RechiseledFans
{
    public static final String MOD_ID = "rechiseled_fans";

    public RechiseledFans()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RechiseledFansTab.TABS.register(modEventBus);
        FanBlockRegistry.ITEMS.register(modEventBus);
        FanBlockRegistry.BLOCKS.register(modEventBus);
        FanBlockSounds.SOUND_EVENTS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}

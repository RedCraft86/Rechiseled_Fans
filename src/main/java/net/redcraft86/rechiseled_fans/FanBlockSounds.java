package net.redcraft86.rechiseled_fans;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

public class FanBlockSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RechiseledFans.MOD_ID);

    public static final RegistryObject<SoundEvent> FAN_BLOCK_EVENT =
            SOUND_EVENTS.register("fan_block", () -> SoundEvent.createVariableRangeEvent(
                    new ResourceLocation(RechiseledFans.MOD_ID, "fan_block")));

    public static final ForgeSoundType FAN_BLOCKS = new ForgeSoundType(1.0f, 1.0f,
            FanBlockSounds.FAN_BLOCK_EVENT, FanBlockSounds.FAN_BLOCK_EVENT, FanBlockSounds.FAN_BLOCK_EVENT,
            FanBlockSounds.FAN_BLOCK_EVENT, FanBlockSounds.FAN_BLOCK_EVENT);

}

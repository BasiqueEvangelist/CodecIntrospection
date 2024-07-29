package me.basiqueevangelist.codecintrospection.mixin.minecraft;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntryListCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RegistryEntryListCodec.class)
public interface RegistryEntryListCodecAccessor {
    @Accessor
    RegistryKey<? extends Registry<?>> getRegistry();
}

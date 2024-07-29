package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryElementCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RegistryElementCodec.class)
public interface RegistryElementCodecAccessor {
    @Accessor
    RegistryKey<? extends Registry<?>> getRegistryRef();
}
